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

  @Query("SELECT s.date FROM Schedule s WHERE s.employeeId = :employeeId")
    //@Query("SELECT s FROM Schedule s WHERE s.employeeId = :employeeId")
    List<LocalDate> findScheduleDatesByEmployeeId(@Param("employeeId") Long employeeId);


    @Query("SELECT s.startTime FROM Schedule s WHERE s.employeeId = :employeeId " +
            "AND s.date = :date")
    List<LocalTime> findOccupiedTimes(@Param("employeeId") Long employeeId,
                                      @Param("date") LocalDate date);
}

