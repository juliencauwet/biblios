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

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private int waitingListOrder;

    public Borrowing() {
    }

    public Borrowing(BookEntity book, AppUser user, Date startDate, Date dueReturnDate){
        this.book =book;
        this.appUser = user;
        this.startDate = startDate;
        this.dueReturnDate =dueReturnDate;
    }

    public Borrowing(AppUser appUser, BookEntity book, Date startDate, Date dueReturnDate, Date returnDate, Status status) {
        this.appUser = appUser;
        this.book = book;
        this.startDate = startDate;
        this.returnDate= returnDate;
        this.dueReturnDate = dueReturnDate;
        this.status = status;
    }

    public Borrowing(BookEntity book, int waitingListOrder){
        this.book = book;
        this.waitingListOrder = waitingListOrder;
    }

    public Borrowing(BookEntity book){
        this.book = book;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public int getWaitingListOrder() {
        return waitingListOrder;
    }

    public void setWaitingListOrder(int waitingListOrder) {
        this.waitingListOrder = waitingListOrder;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", book=" + book +
                ", startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", dueReturnDate=" + dueReturnDate +
                ", isExtended=" + isExtended +
                ", status=" + status +
                ", waitingListOrder=" + waitingListOrder +
                '}';
    }
}
