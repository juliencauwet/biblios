package com.openclassrooms.services;

import com.openclassrooms.entities.AppUser;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;
import com.openclassrooms.repositories.BorrowingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowingService implements IBorrowingService {

    @Autowired
    BorrowingRepository borrowingRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void newBorrowing(Borrowing borrowing) {
            borrowingRepository.save(borrowing);
    }

    @Override
    public void updateBorrowing(Borrowing borrowing) {
        borrowingRepository.save(borrowing);
    }

    @Override
    public List<Borrowing> borrowingReport() {
        List<Borrowing> borrowings = new ArrayList<>();
        borrowingRepository.findAll().forEach(borrowings::add);
        return borrowings;
    }

    @Override
    public Borrowing getById(int id) {
        return borrowingRepository.findById(id);
    }

    @Override
    public List<Borrowing> getByAppUserId(int id) {
        return borrowingRepository.findAllByAppUserId(id);
    }

    @Override
    public List<Borrowing> getExpiredBorrowing() {
        List<Borrowing> borrowings = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date date = c.getTime();
        System.out.println("Today: " + date);
        borrowingRepository.findAllByDueReturnDateBeforeAndReturnDateIsNull(date).forEach(borrowings::add);
        return borrowings;
    }

    @Override
    public List<Borrowing> getAllBorrowings() {
        List<Borrowing> borrowings = new ArrayList<>();
        borrowingRepository.findAll().forEach(borrowings::add);
        return borrowings;
    }

    //TODO: filter borrowings
    @Override
    public List<Borrowing> filterBorrowingByStatus(Status status) {
        return borrowingRepository.findBorrowingsByStatus(status);
    }

    @Override
    public void deleteBorrowingListById(List<Integer> borrowingIds) {
        for (int i: borrowingIds)
            borrowingRepository.deleteBorrowingById(i);
    }

    @Override
    public List<Borrowing> getBorrowingsByBook(BookEntity book) {
        return borrowingRepository.findBorrowingsByBook(book);
    }

    @Override
    public List<Borrowing> getBorrowingsByBookAndStatus(BookEntity book, Status status) {
        return borrowingRepository.findBorrowingsByBookAndStatus(book, status);
    }

    @Override
    public Boolean alreadyBorrowed(AppUser user, BookEntity book) {
        logger.info("alreadyBorrowed Method:" + (borrowingRepository.findBorrowingsByBookAndAppUser(book,user)).size());
        return borrowingRepository.findBorrowingsByBookAndAppUser(book,user).size() > 0;
    }

}
