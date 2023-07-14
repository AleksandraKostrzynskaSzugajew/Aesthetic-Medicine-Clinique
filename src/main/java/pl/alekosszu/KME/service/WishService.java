package pl.alekosszu.KME.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.entity.user.Wish;
import pl.alekosszu.KME.mailSender.EmailServiceImpl;
import pl.alekosszu.KME.repository.WishRepository;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WishService {


    private final WishRepository wishRepository;
    private final ProcedureService procedureService;
    private final EmailServiceImpl emailServiceImpl;
    private final EmployeeService employeeService;


    public void save(Wish wish) {
        wishRepository.save(wish);
    }

    public Wish findById(Long id) {
        return wishRepository.findById(id).get();
    }

    public List<Wish> findAll() {
        return wishRepository.findAll();
    }

    public void update(Wish wish) {
        wishRepository.save(wish);
    }

    public void deleteById(Long id) {
        wishRepository.deleteById(id);
    }


    public Collection<Wish> findIfSimilarRequestExists(Appointment appointmentToBeDeleted) {
        Collection<Wish> allClientsWishes = findAll();
        Collection<Wish> matchingWishes = new ArrayList<>();

        Duration durationOfProcedureFromAppointment = Duration.between(appointmentToBeDeleted.getStartTime(), appointmentToBeDeleted.getEndTime());

        for (Wish w : allClientsWishes) {
            Procedure procedureFromWish = procedureService.findById(w.getProcedureId());
            Duration durationOfTheProcedureFromWish = Duration.ofMinutes(procedureFromWish.getDuration());

            if (appointmentToBeDeleted.getEmployeeId().equals(w.getEmployeeId())
                    && appointmentToBeDeleted.getDate().equals(w.getAppointmentDay())) {

                // Tworzenie listy odcinków czasowych dla odwoływanej procedury
                List<LocalTime> appointmentTimeRange = new ArrayList<>();
                LocalTime startTime = appointmentToBeDeleted.getStartTime();
                while (startTime.isBefore(appointmentToBeDeleted.getEndTime())) {
                    appointmentTimeRange.add(startTime);
                    startTime = startTime.plusMinutes(30); // Przesuwaj się co pół godziny
                }

                // Sprawdzanie, czy którykolwiek z odcinków czasowych znajduje się na liście życzeń
                for (LocalTime appointmentTime : appointmentTimeRange) {
                    if (appointmentTime.equals(w.getAppointmentTime())) {
                        matchingWishes.add(w);
                        break; // Przerywa tylko aktualną iterację, nie całą pętlę
                    }
                }

                // Dodatkowy warunek dla porównywania czasu trwania procedury
                if (durationOfProcedureFromAppointment.equals(durationOfTheProcedureFromWish)
                        || durationOfTheProcedureFromWish.compareTo(durationOfProcedureFromAppointment) <= 0) {
                    matchingWishes.add(w);
                }
            }
        }

        return matchingWishes;
    }




    public void sendAnEmailAboutTermAvailability(Wish wish) {

        Procedure procedure = procedureService.findById(wish.getProcedureId());
        Employee employee = employeeService.findById(wish.getEmployeeId());
        User user = wish.getUser();

        String subject = "Required term available now!";

        String body = "Witaj " + wish.getUser().getFirstName() + "! "
                + "<br><br>"
                + "According to your request we are happy to inform that the term you desired is now available!" +
                "Here are some details to refresh your memory:"
                + "<br>"
                + "<ul>"
                + "<li>Date: " + wish.getAppointmentDay() + "</li>"
                + "<li>Time: " + wish.getAppointmentTime() + "</li>"
                + "<li>Procedure: " + procedure.getName() + "</li>"
                + "<li>Desired start time: " + wish.getAppointmentTime() + " minutes</li>"
                + "<li>Employee: " + employee.getName() + "</li>"
                + "</ul>"
                + "<br>"
                + "Hurry up, until somebody else grabs your term!"
                + "<br>"
                + "<br><br>"
                + "Best regards"
                + "<br>"
                +" SzugajewEsthetic.";
        emailServiceImpl.sendMail(user.getEmail(), subject, body);
    }


}
