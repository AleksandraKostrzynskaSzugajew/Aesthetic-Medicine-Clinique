package pl.alekosszu.KME.entity.employee;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import pl.alekosszu.KME.entity.treatments.Procedure;

import java.util.Collection;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String street;
    private String houseNumber;
    private String postcode;
    private String city;
    private String phoneNumber1;
    private String phoneNumber2;

    //na potrzeby formularza w js
    private String name;

    @OneToMany
    private Collection<Specialty> specialties;


    @ManyToMany
    @JoinTable(name = "employee_procedure")
//            joinColumns = @JoinColumn(name = "person_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Collection<Procedure> performedProcedures;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Schedule> schedule;


    public void addToSchedule(Schedule scheduleItem) {
        schedule.add(scheduleItem);
    }

}
