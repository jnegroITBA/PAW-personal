package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserJpaDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(String username, String password) {
        final User user = new User(username, password);
        // Como esta entidad no tiene un ID, se crea en la DB y se settea el ID.
        // Si fuese una entidad con ID ya establecido, va a hacer un Update porque entiende que ya existía.
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        final TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll(int page) {
        return null;
    }
}
