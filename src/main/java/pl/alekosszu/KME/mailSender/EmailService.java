package pl.alekosszu.KME.mailSender;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendMail(String to, String subject, String body);


    //elaborated version
    // String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}
