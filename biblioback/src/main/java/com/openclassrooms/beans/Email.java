package com.openclassrooms.beans;

import com.openclassrooms.entities.Borrowing;

import java.text.SimpleDateFormat;
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
                "Le livre que vous avez réservé est arrivé: " + borrowing.getBook().getTitle() + " de "+ borrowing.getBook().getAuthorFirstName() + " " + borrowing.getBook().getAuthorName() + '\'' +
                ", qui s'intitule " + borrowing.getBook().getTitle() + "." +'\'' +
                "Merci de bien vouloir venir le chercher rapidement." +
                "L'équipe de Biblioweb.";
    }

    private String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dddd-MM-yyyy");
        String str="";
        return str;
    }
}
