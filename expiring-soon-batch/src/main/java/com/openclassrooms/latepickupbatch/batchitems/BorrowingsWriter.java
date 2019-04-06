package com.openclassrooms.latepickupbatch.batchitems;

import org.springframework.batch.item.ItemWriter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

public class BorrowingsWriter implements ItemWriter<SimpleMailMessage> {

    private JavaMailSender javaMailSender;

    public BorrowingsWriter(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void write(List<? extends SimpleMailMessage> messages) throws Exception {

        for (SimpleMailMessage message: messages
        ) {

            System.out.println(message.getSubject());

            if(javaMailSender!= null) {
                javaMailSender.send(message);
                System.out.println("Message envoyé grâce au Mailsender " + javaMailSender + "!");
            }else {
                System.out.println( "MailSender non défini!");
            }
        }

    }
}
