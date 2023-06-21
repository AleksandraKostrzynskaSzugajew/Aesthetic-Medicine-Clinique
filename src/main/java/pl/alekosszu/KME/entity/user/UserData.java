package pl.alekosszu.KME.entity.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class UserData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dob;
   // private String

}
