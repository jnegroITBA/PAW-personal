package ar.edu.itba.paw.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends UserInheritance{

    @Column(nullable = false)
    private long legajo;

    @Column(nullable = false)
    private float promedio;
}
