package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.employee.EmployeeData;

public interface EmployeeDataRepository extends JpaRepository<EmployeeData, Long> {



}
