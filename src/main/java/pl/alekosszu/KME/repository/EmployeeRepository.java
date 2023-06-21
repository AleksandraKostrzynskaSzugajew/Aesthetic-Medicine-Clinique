package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
