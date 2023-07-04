package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.alekosszu.KME.entity.employee.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByEmployeeId(Long employeeId);

    @Query("SELECT s FROM Schedule s WHERE s.employeeId = :employeeId")
    List<Schedule> findSchedulesByEmployeeId(@Param("employeeId") Long employeeId);


    @Query("SELECT s.startTime FROM Schedule s WHERE s.employeeId = :employeeId " +
            "AND s.date = :date")
    List<LocalTime> findOccupiedTimes(@Param("employeeId") Long employeeId,
                                      @Param("date") LocalDate date);



    @Query("DELETE FROM EmployeeSchedule es WHERE es.schedule.id = :scheduleId")
    void deleteEmployeeSchedulesByScheduleId(@Param("scheduleId") Long scheduleId);


    @Query("DELETE FROM Schedule s WHERE s.id = :scheduleId")
    void deleteScheduleById(@Param("scheduleId") Long scheduleId);
}

