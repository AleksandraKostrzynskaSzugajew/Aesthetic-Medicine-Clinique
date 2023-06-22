package pl.alekosszu.KME.entity.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class EmployeeData {

    @Id
    @GeneratedValue
    private Long id;

    private String street;
    private String houseNumber;
    private String postcode;
    private String city;


}
