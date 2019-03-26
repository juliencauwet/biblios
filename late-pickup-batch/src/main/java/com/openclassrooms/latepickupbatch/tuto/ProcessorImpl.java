package com.openclassrooms.latepickupbatch.tuto;

import com.openclassrooms.biblioback.ws.test.Borrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;

public class ProcessorImpl implements ItemProcessor<Borrowing, SimpleMailMessage> {

    private static final Logger log = LoggerFactory.getLogger(ProcessorImpl .class);

    @Override
    public SimpleMailMessage process(Borrowing borrowing) throws Exception {
        SimpleMailMessage email = new SimpleMailMessage();
        String content = "Bonjour M./ Mme " + borrowing.getAppUser().getName() + ".\n" +
                "Vous avez emprunté le livre de " + borrowing.getBook().getAuthorFirstName() + " " + borrowing.getBook().getAuthorName() +
                ", qui s'intitule " + borrowing.getBook().getTitle() + ".\n" +
                "La date de retour limite de ce livre est le " + borrowing.getDueReturnDate() +
                "Ceci est juste un rappel afin que vous puissiez nous retourner le livre dans les délais.\nCordialement, \n" +
                "L'équipe de Biblioweb.";

        email.setTo(borrowing.getAppUser().getEmail());
        email.setFrom("julien.app.test@gmail.com");
        email.setText(content);
        email.setSubject("Alerte échéance emprunt de " + borrowing.getBook().getTitle());

        log.info("Un email vient d'être édité pour " + borrowing.getAppUser().getFirstName() + " " + borrowing.getAppUser().getName());
        log.info("Message:");
        log.info(content + " ");

        return email;
    }
}
