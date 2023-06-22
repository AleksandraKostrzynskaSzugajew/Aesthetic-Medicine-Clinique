package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.EmployeePhone;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.repository.EmployeePhoneRepository;
import pl.alekosszu.KME.repository.SpecialtyRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeePhoneService {

    private final EmployeePhoneRepository employeePhoneRepository;


    public void save(EmployeePhone employeePhone) {
        employeePhoneRepository.save(employeePhone);
    }

    public void deleteById(Long id) {
        employeePhoneRepository.deleteById(id);
    }
}
