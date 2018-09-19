package com.openclassrooms.bibliobatch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleMailItemWriter implements ItemWriter<SimpleMailMessage> {

    private JavaMailSender javaMailSender;
    private static  final Logger log = LoggerFactory.getLogger(SimpleMailItemWriter.class);

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void write(List<? extends SimpleMailMessage> messages) throws Exception {

        for (SimpleMailMessage message: messages
             ) {
            if(javaMailSender!= null) {
                javaMailSender.send(message);
                log.info("Message envoyé grâce au Mailsender " + javaMailSender + "!");
            }else {
                log.info("MailSender non défini!");
            }
        }
    }
}
