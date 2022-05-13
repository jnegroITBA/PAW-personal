package ar.edu.itba.paw.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_userid_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "users_userid_seq", name = "users_userid_seq")
    @Column(name = "userid")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Deprecated
    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    User() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }
}
