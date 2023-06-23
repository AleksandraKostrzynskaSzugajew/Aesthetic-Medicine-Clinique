package pl.alekosszu.KME.entity.employee;


import jakarta.persistence.*;
import lombok.Data;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Phone;

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


    @OneToMany
    private Collection<Specialty> specialties;


    @ManyToMany
    private Collection<Procedure> performedProcedures;

    @OneToMany
    private Collection<Schedule> schedule;

    public void addToSchedule(Schedule scheduleItem){
        schedule.add(scheduleItem);
    }

}
