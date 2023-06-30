package pl.alekosszu.KME.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.repository.ProcedureRepository;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProcedureService {

    private final ProcedureRepository procedureRepository;


    public void save(Procedure procedure) {
        procedureRepository.save(procedure);
    }

    public Procedure findById(Long id) {
        return procedureRepository.findById(id).get();
    }

    public List<Procedure> findAll() {
        return procedureRepository.findAll();
    }

    public void update(Procedure procedure) {
        procedureRepository.save(procedure);
    }

    public void deleteById(Long id) {
        procedureRepository.deleteById(id);
    }

    public Collection<Employee> findEmployeesPerformingProcedureById(Long procedureId) {
        return procedureRepository.findEmployeesPerformingProcedureById(procedureId);
    }


}
