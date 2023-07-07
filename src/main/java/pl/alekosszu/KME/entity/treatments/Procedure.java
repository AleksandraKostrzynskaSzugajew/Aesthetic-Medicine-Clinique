package pl.alekosszu.KME.entity.treatments;

import jakarta.persistence.*;
import lombok.*;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.treatments.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min=2)
    private String name;

    @NotNull
    @Min(30)
    private int duration; // in minutes

    @NotNull
    @Min(30)
    private double cost;

    @NotNull
    @ManyToOne
    private Category category;

    @ToString.Exclude
    @ManyToMany//(mappedBy="treatment")
    private Collection<Employee> employeesPerformingProcedure;


    public void addPersonPerformingProcedure(Employee employee){
        employeesPerformingProcedure.add(employee);
    }


}
