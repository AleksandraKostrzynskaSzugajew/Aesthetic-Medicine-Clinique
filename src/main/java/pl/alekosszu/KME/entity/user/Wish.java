package pl.alekosszu.KME.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private User user;

    private Long procedureId;
    private Long employeeId;
    private LocalDate appointmentDay;
    private LocalTime appointmentTime;
}
