package com.openclassrooms.services;

import com.openclassrooms.entities.Borrowing;
import org.springframework.stereotype.Service;

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
}
