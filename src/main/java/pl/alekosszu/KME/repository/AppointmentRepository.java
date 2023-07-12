package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.user.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
