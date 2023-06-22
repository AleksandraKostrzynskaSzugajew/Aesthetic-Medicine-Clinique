package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.employee.Specialty;


public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {



}
