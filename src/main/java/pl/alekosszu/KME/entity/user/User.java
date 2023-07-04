package pl.alekosszu.KME.entity.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alekosszu.KME.entity.treatments.Procedure;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Pattern(regexp = "/^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$")
    private String email;


    private String password;

    @NotBlank
    @Size(min=2)
    private String firstName;
    @NotBlank
    @Size(min=2)
    private String lastName;
    @Past
    private LocalDate dob;

    @NotBlank
    @Size(min=2)
    private String street;
    @NotBlank
    private String houseNumber;
    @Pattern(regexp = "/^([0-9]{2})(-[0-9]{3})?$")
    private String postcode;
    @NotBlank
    @Size(min=3)
    private String city;
    @NotBlank
    @Size(min=9)
    private String phoneNumber1;
    private String phoneNumber2;

    @ManyToOne
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Procedure> wishList;

//    @ManyToMany
//    private Collection<Procedure> history;



}