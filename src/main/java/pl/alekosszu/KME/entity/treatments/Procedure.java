package pl.alekosszu.KME.entity.treatments;

import jakarta.persistence.*;
import lombok.*;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.treatments.Category;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "treatment")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int duration; // in minutes
    private double cost;

    @ManyToOne
    private Category category;

    @ManyToMany
    private Collection<Employee> employeesPerformingProcedure;


    public void addPersonPerformingProcedure(Employee employee){
        employeesPerformingProcedure.add(employee);
    }


}
