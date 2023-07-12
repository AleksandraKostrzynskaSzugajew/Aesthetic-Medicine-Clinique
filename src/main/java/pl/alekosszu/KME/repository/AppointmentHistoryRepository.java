package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.entity.user.AppointmentHistory;

import java.util.Collection;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {


    AppointmentHistory findByAppointmentId(Long appointmentId);
    Collection<AppointmentHistory> findAllByUserId(Long userId);
}
