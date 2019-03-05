package com.openclassrooms.services;

import com.openclassrooms.entities.AppUser;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;

import java.util.Date;
import java.util.List;

public interface IBorrowingService {

    void newBorrowing(Borrowing borrowing);
    void updateBorrowing(Borrowing borrowing);
    List<Borrowing> borrowingReport();
    Borrowing getById(int id);
    List<Borrowing> getByAppUserId(int id);
    List<Borrowing> getExpiredBorrowing();
    List<Borrowing> getAllBorrowings();
    List<Borrowing> filterBorrowingByStatus(Status status);
    void deleteBorrowingListById(List<Integer> borrowingIds);
    List<Borrowing> getBorrowingsByBook(BookEntity book);
    List<Borrowing> getBorrowingsByBookAndStatus(BookEntity book, Status status);
    Boolean alreadyBorrowed(AppUser user, BookEntity book);
    Date nextReturnDate(BookEntity bookEntity);
    void sendEmailToNextBorrower(Borrowing borrowing);
}
