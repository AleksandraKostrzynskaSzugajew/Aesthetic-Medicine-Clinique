package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.EmployeeData;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.repository.EmployeeDataRepository;
import pl.alekosszu.KME.repository.SpecialtyRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeDataService {

    private final EmployeeDataRepository employeeDataRepository;


    public void save(EmployeeData employeeData) {
        employeeDataRepository.save(employeeData);
    }

    public EmployeeData findById(Long id) {
        return employeeDataRepository.findById(id).get();
    }

    public void update(EmployeeData employeeData) {
        employeeDataRepository.save(employeeData);
    }

    public void deleteById(Long id) {
        employeeDataRepository.deleteById(id);
    }
}
