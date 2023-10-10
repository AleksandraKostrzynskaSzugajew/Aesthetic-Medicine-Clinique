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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProcedureService procedureService;

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


    public boolean addAppointmentToSchedule(Appointment appointment) {
        Procedure procedureToCountDuration = procedureService.findById(appointment.getProcedureId());
        Employee employee = employeeRepository.findById(appointment.getEmployeeId()).get();

        LocalTime empStartTime;
        LocalTime appointmentStartTime = appointment.getStartTime();
        LocalTime empEndTime;
        Duration durationOfProcedureInMinutes =Duration.ofMinutes(procedureToCountDuration.getDuration());
        int durationAsInt = procedureToCountDuration.getDuration();
        LocalTime appointmentEndTime = appointmentStartTime.plusMinutes(durationAsInt);
        Duration duration = Duration.between(appointmentStartTime, appointmentEndTime);
        long durationAsLong = duration.toMinutes();

        LocalTime maxEndTime;



        for (Schedule scheduleItem : employee.getSchedule()) {

            empStartTime = scheduleItem.getStartTime();
            empEndTime = scheduleItem.getEndTime();
            maxEndTime = empEndTime.minus(duration);

            if (scheduleItem.getDate().equals(appointment.getDate())) {

                if (appointmentStartTime.isAfter(empStartTime) && (appointmentEndTime.isBefore(maxEndTime)))
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

//    public List<Employee> findAllByPerformedProcedures(Long id){
//        return (List<Employee>) employeeRepository.findAllByPerformedProcedures(id);
//    }

    public Schedule FindScheduleByDate(Long employeeId, LocalDate date){
       return employeeRepository.findScheduleByDate(employeeId, date);
    };




}
