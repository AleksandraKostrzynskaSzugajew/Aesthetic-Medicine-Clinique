package pl.alekosszu.KME.entity.employee;


import jakarta.persistence.*;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Phone;

import java.util.Collection;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne
    private EmployeeData employeeData;

    @OneToMany
    private Collection<Specialty> specialties;

    //private COS SCHEDULE;



    @OneToMany
    private Collection<Phone> phoneNumbers;

    @ManyToMany
    private Collection<Procedure> performedProcedures;

    @OneToMany
    private Collection<Schedule> schedule;

}
