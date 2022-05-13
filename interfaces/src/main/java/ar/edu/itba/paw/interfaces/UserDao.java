package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> getUserById(long id);
    Optional<User> findByUsername(String username);
    Optional<User> findById(long id);

    User create(String username, String password);

    List<User> getAll(int page);
}
