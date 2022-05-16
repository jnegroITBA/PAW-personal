package ar.edu.itba.paw.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends UserInheritance {

    @Column(nullable = false)
    private long sueldo;

    @Column(nullable = false)
    private int modulosAsignados;

    Professor(){}
}
