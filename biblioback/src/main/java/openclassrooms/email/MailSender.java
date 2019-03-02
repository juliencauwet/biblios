package openclassrooms.email;

import openclassrooms.entities.Borrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSender extends SimpleMailMessage {

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(SimpleMailMessage message){
        javaMailSender.send(message);

    }
}
