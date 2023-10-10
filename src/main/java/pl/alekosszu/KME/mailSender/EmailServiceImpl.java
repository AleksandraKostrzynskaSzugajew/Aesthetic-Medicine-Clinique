package pl.alekosszu.KME.mailSender;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.entity.user.Wish;
import pl.alekosszu.KME.service.EmployeeService;
import pl.alekosszu.KME.service.ProcedureService;
import pl.alekosszu.KME.service.UserService;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;
    private final UserService userService;
    private final ProcedureService procedureService;
    private final EmployeeService employeeService;

    public EmailServiceImpl(JavaMailSender javaMailSender, UserService userService, ProcedureService procedureService, EmployeeService employeeService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
        this.procedureService = procedureService;
        this.employeeService = employeeService;
    }

    @Override
    public String sendMail(String to, String subject, String body) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            // mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            //this "true" is going to allow my email text to be a html not just plain text
            mimeMessageHelper.setText(body, true);

            //to attach file
//            for (int i = 0; i < file.length; i++) {
//                mimeMessageHelper.addAttachment(
//                        file[i].getOriginalFilename(),
//                        new ByteArrayResource(file[i].getBytes())
//                );
//            }

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendAppointmentConfirmationEmail(Long userId, Appointment appointment) {
        User user = userService.findById(userId);
        Procedure procedure = procedureService.findById(appointment.getProcedureId());
        Employee employee = employeeService.findById(appointment.getEmployeeId());

        String messageText = "Witaj " + user.getFirstName() + "! "
                + "<br><br>"
                + "Twoja wizyta w SzugajewEsthetic Klinika Medycyny Estetycznej została zarezerwowana. Oto szczegóły wizyty:"
                + "<br>"
                + "<ul>"
                + "<li>Data: " + appointment.getDate() + "</li>"
                + "<li>Godzina rozpoczęcia: " + appointment.getStartTime() + "</li>"
                + "<li>Wybrany zabieg: " + procedure.getName() + "</li>"
                + "<li>Przewidywany czas trwania zabiegu: " + procedure.getDuration() + " min</li>"
                + "<li>Pracownik przeprowadzający zabieg: " + employee.getName() + "</li>"
                + "</ul>"
                + "<br>"
                + "<br><br>"
                + "Do zobaczenia, zespół SzugajewEsthetic.";


        sendMail(user.getEmail(), "Potwierdzenie wizyty w SzugajewEsthetic", messageText);
    }


    @Async
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
        sendMail(user.getEmail(), subject, body);
    }
}
