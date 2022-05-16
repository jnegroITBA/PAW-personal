package ar.edu.itba.paw.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "user_inheritance")
public class UserInheritance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_inheritance_userid_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "users_inheritance_userid_seq", name = "users_inheritance_userid_seq")
    @Column(name = "userid")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    public UserInheritance(String username, String password) {
        this.username = username;
        this.password = password;
    }

    UserInheritance() {}

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
