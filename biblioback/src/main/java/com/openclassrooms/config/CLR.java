package com.openclassrooms.config;

import com.openclassrooms.entities.*;
import com.openclassrooms.repositories.AppRoleRepository;
import com.openclassrooms.repositories.EmailRepository;
import com.openclassrooms.repositories.PropertiesRepository;
import com.openclassrooms.services.AppUserService;
import com.openclassrooms.services.BookService;
import com.openclassrooms.services.BorrowingService;
import org.apache.tomcat.jni.Local;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class CLR implements CommandLineRunner{

    @Autowired
    BookService bookService;
    @Autowired
    AppUserService appUserService;
    @Autowired
    BorrowingService borrowingService;
    @Autowired
    AppRoleRepository appRoleRepository;
    @Autowired
    PropertiesRepository propertiesRepository;
    @Autowired
    EmailRepository emailRepository;

    @Override
    public void run(String... args) throws Exception {
        BookEntity b1 = new BookEntity("La Peste", "Camus", "Albert", 4);
        BookEntity b2 = new BookEntity("L'Ecume des jours", "Vian", "Boris", 1);
        BookEntity b3 = new BookEntity("L'assomoir", "Zola", "Emile", 1);
        BookEntity b4 = new BookEntity("Les Confessions", "Rousseau", "Jean-Jacques", 2);
        BookEntity b5 = new BookEntity("Candide", "Voltaire", "", 7);
        BookEntity b6 = new BookEntity("Jean de Florette", "Pagnol", "Marcel", 1);

        bookService.addBook(b1);
        bookService.addBook(b2);
        bookService.addBook(b3);
        bookService.addBook(b4);
        bookService.addBook(b5);
        bookService.addBook(b6);

        AppRole admin = new AppRole(1,"ADMIN", new ArrayList<>());
        AppRole employe = new AppRole(2, "EMPLOYE", new ArrayList<>());
        AppRole utillisateur = new AppRole(3, "UTILISATEUR", new ArrayList<>());

        List<AppRole> userAdmin = Arrays.asList(admin, utillisateur);
        List<AppRole> userOnly = Arrays.asList(utillisateur);

        appRoleRepository.save(admin);
        appRoleRepository.save(employe);
        appRoleRepository.save(utillisateur);


        AppUser u1 = new AppUser("Julien", "Cauwet", "juliencauwet@yahoo.fr", "12345", true, userAdmin, true);
        AppUser u2 = new AppUser("Martine", "Gerussi", "gerussi.martine@orange.fr", "abcde", false, userOnly, true);
        AppUser u3 = new AppUser("Manu", "Favre", "emfavvic@gmail.com", "vwxyz",false, userOnly, true);
        AppUser u4 = new AppUser("Laëtitia", "Cauwet", "laetis0609@yahoo.fr", "98765",false, userOnly, false);
        AppUser u5 = new AppUser("Cesare", "De Padua", "cesaredepadua@gmail.com", "23456", false, userOnly, false);

        appUserService.addUser(u1);
        appUserService.addUser(u2);
        appUserService.addUser(u3);
        appUserService.addUser(u4);
        appUserService.addUser(u5);

        String strDate1 = "26/02/2019";
        String strDate2 = "27/02/2019";
        String strDate3 = "28/02/2019";
        String strDate4 = "21/03/2019";
        String strDate5 = "19/04/2019";
        String strDate6 = "27/08/2019";
        String strDate7 = "01/08/2019";
        String strDate8 = "29/08/2019";
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
        Borrowing bor3 = new Borrowing(u2, b2, date3, date4, date6, Status.RETURNED);
        Borrowing bor4 = new Borrowing(u5, b1, date2, date7, date8, Status.RETURNED);
        Borrowing bor5 = new Borrowing(u3, b4, date1, date4, null, Status.ONGOING);

        borrowingService.newBorrowing(bor1);
        borrowingService.newBorrowing(bor2);
        borrowingService.newBorrowing(bor3);
        borrowingService.newBorrowing(bor4);
        borrowingService.newBorrowing(bor5);

        List<Borrowing> borrowings = borrowingService.getExpiredBorrowing();

        BorrowingProperty property = new BorrowingProperty();
        property.setLibraryId(1);
        property.setBorrowingLength(10);
        property.setBorrowingExtensionLength(5);
        propertiesRepository.save(property);

        Email email1 = new Email(12, LocalDate.now(), "pickup");
        Email email2 = new Email(13, LocalDate.now().minusDays(5), "pickup");
        Email email3 = new Email(14, LocalDate.now().minusDays(1), "pickup");
        Email email4 = new Email(15, LocalDate.now().minusDays(3), "other");

        emailRepository.save(email1);
        emailRepository.save(email2);
        emailRepository.save(email3);
        emailRepository.save(email4);

        if (borrowings.size() == 0){
            System.out.println("Il n'y a pas d'emprunts retardés");
            }else{


            for (Borrowing borrowing : borrowings) {
                Date rd = borrowing.getReturnDate();
                Date drd = borrowing.getDueReturnDate();

                System.out.println("titre: " + borrowing.getBookEntity().getTitle());
                System.out.println(drd);
                System.out.println(rd);
                if (rd == null){
                    System.out.println("Pas de date retour");
                }else
                    System.out.println("en retard? " +drd.before(rd));
            }
        }


    }
}
