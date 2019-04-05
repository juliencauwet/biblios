package com.openclassrooms.beans;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderTest {

    @Mock
    JavaMailSender javaMailSender;

    @InjectMocks
    MailSender mailSender;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendEmail() {
        String from = "julien.app.test@gmail.com";
        String to = "julien.app.test@gmail.com";
        String subject = "test";
        String body = "test";

        mailSender.sendMail(from, to, subject, body);


    }
}
