package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.treatments.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
}
