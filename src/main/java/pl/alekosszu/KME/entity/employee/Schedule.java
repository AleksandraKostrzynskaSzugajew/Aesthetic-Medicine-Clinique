package pl.alekosszu.KME.entity.employee;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import pl.alekosszu.KME.entity.user.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Schedule {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    private LocalDate date;

    private Long employeeId;

    //the same as date, must be included for js script
    private String name;

    @OneToMany
    private List<Appointment> scheduledAppointments;

    public void addToScheduledAppointments(Appointment appointment){
        scheduledAppointments.add(appointment);

    }

}
