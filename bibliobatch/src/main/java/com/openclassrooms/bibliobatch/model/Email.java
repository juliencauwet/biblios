package com.openclassrooms.bibliobatch.model;

import com.openclassrooms.biblioback.ws.test.Borrowing;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class Email {

    private String senderEmail;
    private String recipientEmail;
    private Borrowing borrowing;

    public Email(String senderEmail, String recipientEmail, Borrowing borrowing) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.borrowing = borrowing;
    }

    public Email() {

    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Borrowing borrowing) {
        this.borrowing = borrowing;
    }

    @Override
    public String toString() {
        return "Bonjour M./ Mme " + borrowing.getAppUser().getName() + ".\n" +
                "Vous avez emprunté le livre de " + borrowing.getBook().getAuthorFirstName() + " " + borrowing.getBook().getAuthorName() + '\'' +
                ", qui s'intitule " + borrowing.getBook().getTitle() + "." +'\'' +
                "Le livre aurait dû être restitué avant le " + borrowing.getDueReturnDate() + " mais il ne nous a pas été retourné à ce jour." + '\'' +
                "Nous vous prions de bien vouloir le rapporter dès que possible.\n Cordialement, \n" +
                "L'équipe de Biblioweb.";
    }

    private String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dddd-MM-yyyy");
        String str="";
        return str;
    }
}
