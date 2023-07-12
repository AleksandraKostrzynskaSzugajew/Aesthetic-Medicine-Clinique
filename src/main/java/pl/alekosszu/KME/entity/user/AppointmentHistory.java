package pl.alekosszu.KME.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class AppointmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long appointmentId;
    private Long procedureId;
    private Long scheduleId;
    private Long employeeId;

    private LocalDate date;

    private LocalTime startTime;
    private LocalTime endTime;

    private Long userId;

    private String status;
}
