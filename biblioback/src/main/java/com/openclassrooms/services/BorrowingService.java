package com.openclassrooms.services;

import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.repositories.BorrowingRepository;
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
}
