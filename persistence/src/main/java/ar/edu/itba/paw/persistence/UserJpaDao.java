package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserJpaDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(final String username, final String password) {
        final User user = new User(username, password);
        // Como esta entidad no tiene un ID, se crea en la DB y se settea el ID.
        // Si fuese una entidad con ID ya establecido, va a hacer un Update porque entiende que ya existía.
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        final TypedQuery<User> query = entityManager.createQuery("from User as u where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<User> getUserById(final long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public User merge(final User user) {
        // Para métodos de servicios transaccionales, si te pasan una instancia de una clase y vos la querés modificar, vas a estar modificando una copia.
        // Esto es porque JPA va a armar una sesión (transacción) de la entidad que le pasaste, y va a hacer lo que le hayas pedido,
        // pero al momento de devolverte la entidad, la sesión transaccional va a morir y por ende lo que devuelve no va a estar linkeado a la DB.
        // Luego, tenés que hacer un merge antes de modificar la entidad, para que JPA tome la entidad linkeada a la DB y modifique eso.
        return entityManager.merge(user);
    }

    public List<User> getAllIncorrecta(int page) {
        // Esto generalmente está mal, y falla, pues ahora mismo podrían devolverse más de un ROW por entidad, por lo que
        // pedir 10 rows no siempre son 10 entidades. Eso es porque si User tiene algo que se agarra en FetchType.EAGER,
        // luego por cada User también se hará otra consulta para hacer el fetch de lo que sea EAGER, resultando en menos usuarios obtenidos. 
        final TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        query.setFirstResult(page * 10);
        query.setMaxResults(10);
        return query.getResultList();
    }

    @Override
    public List<User> getAll(int page) {
        Query idQuery = entityManager.createNativeQuery("SELECT * FROM users LIMIT 5 OFFSET :offset", Long.class);
        idQuery.setParameter("offset", page * 5);
        @SuppressWarnings("unchecked")
        List<Long> userIds = (List<Long>) idQuery.getResultList().stream()
                .map(o -> ((Integer) o).longValue()).collect(Collectors.toList());

        final TypedQuery<User> query = entityManager.createQuery("from User as u where u.id in :ids", User.class);
        query.setParameter("ids", userIds);
        return query.getResultList();
    }
}
