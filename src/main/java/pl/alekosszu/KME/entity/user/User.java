package pl.alekosszu.KME.entity.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alekosszu.KME.entity.treatments.Procedure;

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

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private LocalDate dob;

    private String street;
    private String houseNumber;
    private String postcode;
    private String city;
    private String phoneNumber1;
    private String phoneNumber2;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Procedure> wishList;

//    @ManyToMany
//    private Collection<Procedure> history;

    public void addRoleToUser(Role role){
        roles.add(role);
    }


}