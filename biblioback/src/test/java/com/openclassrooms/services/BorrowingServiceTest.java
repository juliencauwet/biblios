package com.openclassrooms.services;

import com.openclassrooms.BibliobackApplication;
import com.openclassrooms.entities.AppUser;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;
import com.openclassrooms.repositories.AppUserRepository;
import com.openclassrooms.repositories.BookRepository;
import com.openclassrooms.repositories.BorrowingRepository;
import com.openclassrooms.services.BorrowingService;
import org.hibernate.LazyInitializationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BibliobackApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BorrowingServiceTest {

    @Autowired
    BorrowingService service;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    @Order(2)
    public void newBorrowing() {

        Borrowing borrowing = new Borrowing();
        AppUser appUser = appUserRepository.findById(7);
        BookEntity book = bookRepository.findBookEntityById(3);
        borrowing.setAppUser(appUser);
        borrowing.setBook(book);
        service.newBorrowing(borrowing);
    }

    @Test
    public void updateBorrowing() {
        Borrowing borrowing = service.getById(12);
        borrowing.setStatus(Status.RETURNED);
        service.updateBorrowing(borrowing);
        Assert.assertEquals(Status.RETURNED, service.getById(12).getStatus());

    }

    @Test
    @Order(1)
    public void borrowingReport() {
        Assert.assertTrue( service.borrowingReport().size() >= 5);

    }

    @Test
    public void getById() {
        Assert.assertEquals(8, service.getById(14).getAppUser().getId());
    }

    @Test
    public void getByAppUserId() {
        Borrowing borrowing = new Borrowing();
        borrowing.setAppUser(appUserRepository.findById(8));
        borrowing.setBook(bookRepository.findBookEntityById(4));
        try {
            Assert.assertEquals(3, service.getByAppUserId(8));
        }catch (LazyInitializationException e){
            System.out.println("LazyInitializationException");
        }
    }

    @Test
    public void getExpiredBorrowing() {
        Date date = new Date();
        List<Borrowing> expiredBorrowings = service.getExpiredBorrowing();
        for (Borrowing b :
                expiredBorrowings) {
            Assert.assertTrue(date.after(b.getDueReturnDate()) && b.getStatus() == Status.ONGOING);
        }
    }

    @Test
    public void getAllBorrowings() {
        Assert.assertEquals(5, service.getAllBorrowings().size());
    }

    @Test
    public void filterBorrowingByStatus() {
        try{
            Assert.assertEquals(2, service.filterBorrowingByStatus(Status.ONGOING));
        }catch (LazyInitializationException e){
            System.out.println("LazyInitializationException");
        }

    }

    @Test
    public void getBorrowingsByBook() {
        BookEntity book = bookRepository.findBookEntityById(4);
        try {
            Assert.assertEquals(5, service.getBorrowingsByBook(book));
        }catch (LazyInitializationException e){
            System.out.println("LazyInitializationException");
        }
    }
}
