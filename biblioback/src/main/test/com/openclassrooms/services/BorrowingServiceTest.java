package com.openclassrooms.services;

import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;
import com.openclassrooms.repositories.BorrowingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BorrowingServiceTest {


    BorrowingRepository borrowingRepository;

    @Test
    public void newBorrowing() {
    }

    @Test
    public void updateBorrowing() {
    }

    @Test
    public void borrowingReport() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getByAppUserId() {
    }

    @Test
    public void getExpiredBorrowing() {
    }

    @Test
    public void getAllBorrowings() {

    }

    @Test
    public void filterBorrowingByStatus() {
        Assert.assertEquals(new Borrowing(), borrowingRepository.findBorrowingsByStatus(Status.ONGOING));
    }

    @Test
    public void deleteBorrowingListById() {
    }

    @Test
    public void getBorrowingsByBook() {
    }
}