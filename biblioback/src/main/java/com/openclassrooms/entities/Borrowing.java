package com.openclassrooms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Borrowing {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private AppUser appUser;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private BookEntity book;
    @NotNull
    private Date startDate;
    private Date returnDate;
    private Date dueReturnDate;
    private Boolean isExtended = false;

    public Borrowing() {
    }

    public Borrowing(AppUser appUser, BookEntity book, Date startDate, Date dueReturnDate, Date returnDate) {
        this.appUser = appUser;
        this.book = book;
        this.startDate = startDate;
        this.returnDate= returnDate;
        this.dueReturnDate = dueReturnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public BookEntity getBookEntity() {
        return book;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.book = bookEntity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDueReturnDate() {
        return dueReturnDate;
    }

    public void setDueReturnDate(Date dueReturnDate) {
        this.dueReturnDate = dueReturnDate;
    }

    public Boolean getExtended() {
        return isExtended;
    }

    public void setExtended(Boolean extended) {
        isExtended = extended;
    }

}
