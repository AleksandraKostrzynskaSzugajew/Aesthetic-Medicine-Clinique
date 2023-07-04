package pl.alekosszu.KME.entity.employee;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import pl.alekosszu.KME.entity.treatments.Procedure;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String firstName;
    @NotBlank
    @Size(min = 2)
    private String lastName;
    @NotBlank
    @Size(min = 3)
    private String street;
    @NotNull
    private String houseNumber;
    @Pattern(regexp = "/^([0-9]{2})(-[0-9]{3})?$")
    private String postcode;
    @NotBlank
    @Size(min = 3)
    private String city;
    @NotBlank
    @Size(min = 9, max = 9)
    private String phoneNumber1;
    private String phoneNumber2;

    //na potrzeby formularza w js
    @NotBlank
    private String name;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Specialty> specialties;


    @ManyToMany
    @JoinTable(name = "employee_procedure")
    private Collection<Procedure> performedProcedures;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Collection<Schedule> schedule;


    public void addToSchedule(Schedule scheduleItem) {
        schedule.add(scheduleItem);
    }

}
