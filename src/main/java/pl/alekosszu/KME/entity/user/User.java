package pl.alekosszu.KME.entity.user;

import jakarta.persistence.*;
import lombok.*;
import pl.alekosszu.KME.entity.treatments.Procedure;


import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private UserData userData;

    @OneToMany
    private Collection<Phone> phoneList;

    @ManyToMany
    private Collection<Procedure> wishList;

    @ManyToMany
    private Collection<Procedure> history;





}
