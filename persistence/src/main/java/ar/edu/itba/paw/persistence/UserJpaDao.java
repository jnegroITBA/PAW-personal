package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

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
}
