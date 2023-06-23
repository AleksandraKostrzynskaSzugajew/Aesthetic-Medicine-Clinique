package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.employee.Schedule;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByEmployeeId(Long employeeId);



}
