package com.openclassrooms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Email {

    @Id
    @GeneratedValue
    private int id;

    private int borrowingId;

    private LocalDate sentDate;

    private String subject;

    public Email() {
    }

    public Email(int borrowingId, LocalDate sentDate, String subject){
        this.borrowingId = borrowingId;
        this.sentDate = sentDate;
        this.subject = subject;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(int borrowingId) {
        this.borrowingId = borrowingId;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
