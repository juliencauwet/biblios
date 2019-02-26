package com.openclassrooms.beans;

import com.openclassrooms.biblioback.ws.test.Book;
import com.openclassrooms.biblioback.ws.test.Borrowing;

import java.util.Date;


public class BorrowingState {
    private Book book;
    private Borrowing borrowing;
    private int available;
    private Date nextReturn;

    public BorrowingState(Book book, Borrowing borrowing, int available, Date nextReturn) {
        this.book = book;
        this.borrowing = borrowing;
        this.available = available;
        this.nextReturn = nextReturn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Borrowing borrowing) {
        this.borrowing = borrowing;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Date getNextReturn() {
        return nextReturn;
    }

    public void setNextReturn(Date nextReturn) {
        this.nextReturn = nextReturn;
    }
}
