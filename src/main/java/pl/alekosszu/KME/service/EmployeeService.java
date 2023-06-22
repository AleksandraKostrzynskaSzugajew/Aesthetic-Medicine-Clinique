package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.repository.EmployeeRepository;


import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
