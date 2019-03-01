package com.openclassrooms.config;

import openclassrooms.entities.*;
import openclassrooms.repositories.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@TestPropertySource(locations = "classpath:application-test.properties")
public class Init {

    @Autowired
    BorrowingRepository repository;

    public static void getData() throws ParseException {



        //AppRole admin = new AppRole(1,"ADMIN", new ArrayList<>());
        //AppRole employe = new AppRole(2, "EMPLOYE", new ArrayList<>());
        //AppRole utillisateur = new AppRole(3, "UTILISATEUR", new ArrayList<>());
//
        //List<AppRole> userAdmin = Arrays.asList(admin, utillisateur);
        //List<AppRole> userOnly = Arrays.asList(utillisateur);
//
        //AppUser u1 = new AppUser("Julien", "Cauwet", "juliencauwet@yahoo.fr", "12345", true, userAdmin);
        //AppUser u2 = new AppUser("Juan", "Olivero", "jjolivero83@gmail.com", "abcde", false, userOnly);
        //AppUser u3 = new AppUser("Manu", "Favre", "emfavvic@gmail.com", "vwxyz",false, userOnly);
        //AppUser u4 = new AppUser("Laëtitia", "Cauwet", "laetis0609@yahoo.fr", "98765",false, userOnly);
        //AppUser u5 = new AppUser("Cesare", "De Padua", "cesaredepadua@gmail.com", "23456", false, userOnly);
//
//
        //String strDate1 = "26/05/2018";
        //String strDate2 = "27/06/2018";
        //String strDate3 = "28/07/2018";
        //String strDate4 = "29/08/2018";
        //String strDate5 = "26/07/2018";
        //String strDate6 = "27/08/2018";
        //String strDate7 = "01/08/2018";
        //String strDate8 = "29/08/2018";
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
        //Date date1 = sdf.parse(strDate1);
        //Date date2 = sdf.parse(strDate2);
        //Date date3 = sdf.parse(strDate3);
        //Date date4 = sdf.parse(strDate4);
        //Date date5 = sdf.parse(strDate5);
        //Date date6 = sdf.parse(strDate6);
        //Date date7 = sdf.parse(strDate7);
        //Date date8 = sdf.parse(strDate8);
//
        //Borrowing bor1 = new Borrowing(u1, b3, date1, date5, null, Status.ONGOING);
        //Borrowing bor2 = new Borrowing(u4, b6, date2, date6, null, Status.ONGOING);
        //Borrowing bor3 = new Borrowing(u2, b2, date3, date5, date6, Status.ONGOING);
        //Borrowing bor4 = new Borrowing(u5, b1, date4, date7, date8, Status.ONGOING);
        //Borrowing bor5 = new Borrowing(u3, b4, date1, date2, null, Status.ONGOING);
//

    }
}
