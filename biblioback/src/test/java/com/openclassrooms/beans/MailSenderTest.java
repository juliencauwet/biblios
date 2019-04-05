package com.openclassrooms.beans;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MailSenderTest {

    @Autowired
    MailSender sender;

    @Test
    public void sendEmail() {
        String from = "julien.app.test@gmail.com";
        String to = "julien.app.test@gmail.com";
        String subject = "test";
        String body = "test";

        sender.sendMail(from, to, subject, body);
    }
}
