package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.treatments.Procedure;

import java.util.Collection;
import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

//    @Query(value = "SELECT * FROM employees_performed_procedures where performed_procedures_id=?1", nativeQuery = true)
//    public List<Employee> findEmpsForProcedure(Long id);


    @Query("SELECT p.employeesPerformingProcedure FROM Procedure p WHERE p.id = :procedureId")
    Collection<Employee> findEmployeesPerformingProcedureById(@Param("procedureId") Long procedureId);

}
