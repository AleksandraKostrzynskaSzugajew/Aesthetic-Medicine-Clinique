package pl.alekosszu.KME.entity.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
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
