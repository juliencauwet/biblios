package com.openclassrooms.latepickupbatch.batchitems;

import org.springframework.batch.item.ItemWriter;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public class BorrowingsWriter implements ItemWriter<SimpleMailMessage> {

    @Override
    public void write(List<? extends SimpleMailMessage> messages) throws Exception {

        for (SimpleMailMessage message: messages
        ) {

            System.out.println(message.getSubject());

            //if(javaMailSender!= null) {
            //    javaMailSender.send(message);
            //    //log.info("Message envoyé grâce au Mailsender " + javaMailSender + "!");
            //}else {
            //    //log.info("MailSender non défini!");
            //}
        }

    }
}
