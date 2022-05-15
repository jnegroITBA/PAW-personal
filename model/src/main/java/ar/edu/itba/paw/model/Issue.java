package ar.edu.itba.paw.model;

import javax.persistence.*;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issues_issueid_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "issues_issueid_seq", name = "issues_issueid_seq")
    @Column(name = "issueid")
    private Long id;

    @Column(nullable = false, length = 1024)
    private String despcription;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User reportedBy;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;


    // Hibernate default constructor
    Issue() {}

    public Issue(final User reportedBy, final String desciption, final Priority priority) {
        super();
        this.priority = priority;
        this.reportedBy = reportedBy;
        this.despcription = desciption;
        this.status = Status.NOT_STARTED;
        this.assignedTo = null;
    }


    public Long getId() {
        return id;
    }

    public String getDespcription() {
        return despcription;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(final User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }
}
