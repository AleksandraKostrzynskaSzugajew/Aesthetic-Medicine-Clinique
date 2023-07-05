package pl.alekosszu.KME.entity.employee;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import pl.alekosszu.KME.entity.user.Appointment;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Future
    private LocalTime startTime;

    @Column(name = "end_time")
    @NotNull
    @Future
    private LocalTime endTime;

    @NotNull
    @Future
    private LocalDate date;

    @NotNull
    private Long employeeId;

    //the same as date, must be included for js script
    @NotBlank
    @Size(min = 5)
    private String name;

    @OneToMany
    private List<Appointment> scheduledAppointments;


    public void addToScheduledAppointments(Appointment appointment) {
        scheduledAppointments.add(appointment);

    }

    public void removeFromScheduledAppointments(Appointment appointment){
        scheduledAppointments.remove(appointment);
    }

}
