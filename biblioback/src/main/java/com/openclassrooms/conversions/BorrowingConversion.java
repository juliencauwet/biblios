package com.openclassrooms.conversions;

import com.openclassrooms.biblioback.ws.test.Borrowing;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BorrowingConversion {

    //Constructeurs objets requis
    AppUserConversion appUserConversion = new AppUserConversion();
    BookConversion bookConversion = new BookConversion();

    /**
     *
     * @param bor
     * @return
     */
    public Borrowing toWS(com.openclassrooms.entities.Borrowing bor){

        Borrowing borrowingWS = new Borrowing();

        //transfert des paramètres convertis de l'emprunt Entity au ws
        borrowingWS.setId(bor.getId());
        borrowingWS.setAppUser(appUserConversion.appUserEntityToAppUser(bor.getAppUser()));
        borrowingWS.setBook(bookConversion.bookEntityToBook(bor.getBookEntity()));
        if (bor.getStartDate()!= null)
            borrowingWS.setStartDate(toXmlGregorianCalendar(dateToGregorianCalendar(bor.getStartDate())));
        if(bor.getReturnDate() != null)
            borrowingWS.setReturnDate(toXmlGregorianCalendar(dateToGregorianCalendar(bor.getReturnDate())));
        if(bor.getDueReturnDate() != null)
            borrowingWS.setDueReturnDate(toXmlGregorianCalendar(dateToGregorianCalendar(bor.getDueReturnDate())));

        return borrowingWS;
    }

    /**
     *
     * @param borWS
     * @return
     */
    public com.openclassrooms.entities.Borrowing toEntity(Borrowing borWS){
        com.openclassrooms.entities.Borrowing bor = new com.openclassrooms.entities.Borrowing();

        //transfert des paramètres convertis de l'emprunt ws à Entity
        bor.setId(borWS.getId());
        bor.setAppUser(appUserConversion.appUserToAppUserEntity(borWS.getAppUser()));
        bor.setBookEntity(bookConversion.bookToBookEntity(borWS.getBook()));
        if (borWS.getStartDate() != null)
            bor.setStartDate(gregToDate(borWS.getStartDate().toGregorianCalendar()));
        if (borWS.getReturnDate() != null)
            bor.setReturnDate(gregToDate(borWS.getReturnDate().toGregorianCalendar()));
        if (borWS.getDueReturnDate() != null)
            bor.setDueReturnDate(gregToDate(borWS.getDueReturnDate().toGregorianCalendar()));

        return bor;
    }

    /**
     *
     * @param cal
     * @return
     */
    public XMLGregorianCalendar toXmlGregorianCalendar(GregorianCalendar cal){
        try {
            DatatypeFactory dataTypeFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar calendar = dataTypeFactory.newXMLGregorianCalendar(cal);
            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param date
     * @return
     */
    public GregorianCalendar dateToGregorianCalendar(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     *
     * @param calendar
     * @return
     */
    public Date gregToDate(GregorianCalendar calendar){
        Date date = calendar.getTime();
        return date;
    }
}
