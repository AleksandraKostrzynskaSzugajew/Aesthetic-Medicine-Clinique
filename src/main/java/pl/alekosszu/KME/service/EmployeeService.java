package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.repository.EmployeeRepository;


import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ScheduleService scheduleService;

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

//    @Query("select * from employees_performed_procedures where performed_procedures_id = ?1")
//    public void findAllByProcedureId(Long id) { //moze szukac w zlej tabeli, nie tej laczonej, zapytanie w native sql?
//    }

    public boolean addAppointmentToSchedule(Appointment appointment) {

        Employee employee = employeeRepository.findById(appointment.getEmployeeId()).get();
      //  List<Schedule> workdaysForEmp = scheduleService.findByEmployeeId(appointment.getEmployeeId());

        LocalTime empStartTime;
        LocalTime appointmentStartTime = appointment.getStartTime();
        LocalTime empEndTime;
        LocalTime appointmentEndTime = appointment.getEndTime();
        Duration duration = Duration.between(appointmentStartTime, appointmentEndTime);
        long durationAsLong = duration.toMinutes();

        LocalTime maxEndTime;


        for (Schedule scheduleItem : employee.getSchedule()) {

            empStartTime = scheduleItem.getStartTime();
            empEndTime = scheduleItem.getEndTime();
            maxEndTime = empEndTime.minus(duration);

            if (scheduleItem.getDate().equals(appointment.getDate())) {

                if (appointmentStartTime.isAfter(empStartTime) && (appointmentEndTime.isBefore(maxEndTime)))
                //i jesli sie nie pokrywa z jakims innym
                {
                    scheduleItem.addToScheduledAppointments(appointment);
                    System.out.println("Successfully booked");
                    return true;
                }
            }
        }
        System.out.println("Choose another date or time");
        return false;
    }


}
