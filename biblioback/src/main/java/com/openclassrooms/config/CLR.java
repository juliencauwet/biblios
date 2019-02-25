package com.openclassrooms.config;

import com.openclassrooms.entities.AppRole;
import com.openclassrooms.entities.AppUser;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.repositories.AppRoleRepository;
import com.openclassrooms.services.AppUserService;
import com.openclassrooms.services.BookService;
import com.openclassrooms.services.BorrowingService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        AppUser u1 = new AppUser("Julien", "Cauwet", "juliencauwet@yahoo.fr", "12345", true);
        AppUser u2 = new AppUser("Juan", "Olivero", "jjolivero83@gmail.com", "abcde", false);
        AppUser u3 = new AppUser("Manu", "Favre", "emfavvic@gmail.com", "vwxyz", false);
        AppUser u4 = new AppUser("Laëtitia", "Cauwet", "laetis0609@yahoo.fr", "98765", false);
        AppUser u5 = new AppUser("Cesare", "De Padua", "cesaredepadua@gmail.com", "23456", false);

        appUserService.addUser(u1);
        appUserService.addUser(u2);
        appUserService.addUser(u3);
        appUserService.addUser(u4);
        appUserService.addUser(u5);

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

        Borrowing bor1 = new Borrowing(u1, b3, date1, date5, null);
        Borrowing bor2 = new Borrowing(u4, b6, date2, date6, null);
        Borrowing bor3 = new Borrowing(u2, b2, date3, date5, date6);
        Borrowing bor4 = new Borrowing(u5, b1, date4, date7, date8);
        Borrowing bor5 = new Borrowing(u3, b4, date1, date2, null);

        borrowingService.newBorrowing(bor1);
        borrowingService.newBorrowing(bor2);
        borrowingService.newBorrowing(bor3);
        borrowingService.newBorrowing(bor4);
        borrowingService.newBorrowing(bor5);

        List<Borrowing> borrowings = borrowingService.getExpiredBorrowing();

        AppRole admin = new AppRole(1,"ADMIN", new ArrayList<>());
        AppRole employe = new AppRole(2, "EMPLOYE", new ArrayList<>());
        AppRole utillisateur = new AppRole(3, "UTILISATEUR", new ArrayList<>());

        appRoleRepository.save(admin);
        appRoleRepository.save(employe);
        appRoleRepository.save(utillisateur);

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
