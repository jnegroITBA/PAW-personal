package ar.edu.itba.paw.model;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(orphanRemoval = false, mappedBy = "assignedTo")
    List<Issue> assignedIssues;

    @OneToMany(orphanRemoval = true, mappedBy = "reportedBy")
    List<Issue> reportedIssues;

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

    public List<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public List<Issue> getReportedIssues() {
        return reportedIssues;
    }

    public Issue reportNewIssue(final String description, final Priority priority) {
        final Issue issue = new Issue(this, description, priority);
        reportedIssues.add(issue);
        return issue;
    }
}
