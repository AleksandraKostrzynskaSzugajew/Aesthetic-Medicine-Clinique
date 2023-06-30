package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;

import java.time.LocalDate;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT s FROM Employee e JOIN e.schedule s WHERE e.id = :employeeId AND s.date = :date")
    Schedule findScheduleByDate(Long employeeId, LocalDate date);
}
