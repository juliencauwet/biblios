package com.openclassrooms.services;

import com.openclassrooms.BibliobackApplication;
import com.openclassrooms.entities.*;
import com.openclassrooms.repositories.BorrowingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = BibliobackApplication.class
//)
//@TestPropertySource(locations = "classpath:application-test.properties")
public class BorrowingServiceTest {

    @Autowired
    BorrowingService service;

    @Autowired
    AppUserService appUserService;

    @Autowired
    AppRoleService appRoleService;

    @Autowired
    BookService bookService;

    @Autowired
    BorrowingRepository borrowingRepository;

    BookEntity b1 = new BookEntity("La Peste", "Camus", "Albert", 4);
    BookEntity b2 = new BookEntity("L'Ecume des jours", "Vian", "Boris", 1);
    BookEntity b3 = new BookEntity("L'assomoir", "Zola", "Emile", 1);
    BookEntity b4 = new BookEntity("Les Confessions", "Rousseau", "Jean-Jacques", 2);
    BookEntity b5 = new BookEntity("Candide", "Voltaire", "", 7);
    BookEntity b6 = new BookEntity("Jean de Florette", "Pagnol", "Marcel", 1);

    AppRole admin = new AppRole(1,"ADMIN", new ArrayList<>());
    AppRole employe = new AppRole(2, "EMPLOYE", new ArrayList<>());
    AppRole utillisateur = new AppRole(3, "UTILISATEUR", new ArrayList<>());

    List<AppRole> userAdmin = Arrays.asList(admin, utillisateur);
    List<AppRole> userOnly = Arrays.asList(utillisateur);

    AppUser u1 = new AppUser("Julien", "Cauwet", "juliencauwet@yahoo.fr", "12345", true, userAdmin);
    AppUser u2 = new AppUser("Juan", "Olivero", "jjolivero83@gmail.com", "abcde", false, userOnly);
    AppUser u3 = new AppUser("Manu", "Favre", "emfavvic@gmail.com", "vwxyz",false, userOnly);
    AppUser u4 = new AppUser("Laëtitia", "Cauwet", "laetis0609@yahoo.fr", "98765",false, userOnly);
    AppUser u5 = new AppUser("Cesare", "De Padua", "cesaredepadua@gmail.com", "23456", false, userOnly);

    String strDate1 = "26/05/2018";
    String strDate2 = "27/06/2018";
    String strDate3 = "28/07/2018";
    String strDate4 = "29/08/2018";
    String strDate5 = "26/07/2018";
    String strDate6 = "27/08/2018";
    String strDate7 = "01/08/2018";
    String strDate8 = "29/08/2018";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Date date1 = sdf.parse(strDate1);
    Date date2 = sdf.parse(strDate2);
    Date date3 = sdf.parse(strDate3);
    Date date4 = sdf.parse(strDate4);
    Date date5 = sdf.parse(strDate5);
    Date date6 = sdf.parse(strDate6);
    Date date7 = sdf.parse(strDate7);
    Date date8 = sdf.parse(strDate8);

    Borrowing bor1 = new Borrowing(u1, b3, date1, date5, null, Status.ONGOING);
    Borrowing bor2 = new Borrowing(u4, b6, date2, date6, null, Status.ONGOING);
    Borrowing bor3 = new Borrowing(u2, b2, date3, date5, date6, Status.ONGOING);
    Borrowing bor4 = new Borrowing(u5, b1, date4, date7, date8, Status.ONGOING);
    Borrowing bor5 = new Borrowing(u3, b4, date1, date2, null, Status.ONGOING);

    public BorrowingServiceTest() throws ParseException {
    }


    @Before
    public void init(){

    }
/*
    @Test
    public void newBorrowing() {
        Borrowing borrowing = new Borrowing();
        borrowing.setStatus(Status.ONGOING);
        borrowing.setStartDate(new Date());
        borrowing.setId(1000);
        service.newBorrowing(borrowing);

        //Assert.assertEquals(service.getById(1000), bor1);
        Assert.assertTrue(service.getAllBorrowings().size() >= 1);
    }
*/
    @Test
    public void updateBorrowing() {


    }

    @Test
    public void borrowingReport() {
    }

    @Test
    public void getById() {
       // Borrowing borrowing = service.getById(1);
       // Assert.assertEquals(u1, borrowing.getAppUser());
    }

    @Test
    public void getByAppUserId() {
    }

    @Test
    public void getExpiredBorrowing() {
    }

    /*
    @Test
    public void getAllBorrowings() {
        List<Borrowing> expectedBorrowings = Arrays.asList(bor1, bor2);

        List<Borrowing> borrowings = service.getAllBorrowings();
        Assert.assertTrue(borrowings.size() > 1);
        Assert.assertEquals(expectedBorrowings, borrowings);
    }
    */
/*
    @Test
    public void filterBorrowingByStatus() {
        List<Borrowing> borrowings = service.filterBorrowingByStatus(Status.ONGOING);
        for (Borrowing b : borrowings)
            Assert.assertTrue(b.getStatus() == Status.ONGOING);
    }
*/
    @Test
    public void deleteBorrowingListById() {
    }
    /*
    @Test
    public  void sendEmailToNextBorrower(){
        service.sendEmailToNextBorrower(new Borrowing());
    }
    */
    @Test
    public void getBorrowingsByBook() {

        //BookEntity b1 = new BookEntity("La Peste", "Camus", "Albert", 4);
//
        //Borrowing borrowing = new Borrowing(b1);
        //List<Borrowing> borrowings = Arrays.asList(
        //        new Borrowing(b1, 1),
        //        new Borrowing(b1, 2),
        //        new Borrowing(b1, 3),
        //        new Borrowing(b1, 4)
        //);
//
        //when(borrowingRepository.findBorrowingsByBook(b1)).thenReturn(borrowings);
//
        //List<Borrowing> actualBorrowings = borrowingService.getBorrowingsByBook(b1);
//
        //Assert.assertEquals(borrowings, actualBorrowings);
    }
}