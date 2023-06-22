package pl.alekosszu.KME.entity.user;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alekosszu.KME.entity.treatments.Procedure;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToMany
    private Collection<Role> roles;

    @OneToOne
    private UserData userData;

    @OneToMany
    private Collection<Phone> phoneList;

    @ManyToMany
    private Collection<Procedure> wishList;

    @ManyToMany
    private Collection<Procedure> history;



}