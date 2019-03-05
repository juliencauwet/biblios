package com.openclassrooms.conversions;

import com.openclassrooms.biblioback.ws.test.Borrowing;
import com.openclassrooms.entities.Status;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Component
public class BorrowingConversion {

    //Constructeurs objets requis
    @Autowired
    AppUserConversion appUserConversion;

    BookConversion bookConversion = new BookConversion();
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

        switch (bor.getStatus()){
            case WAITINGLIST: borrowingWS.setStatus(com.openclassrooms.biblioback.ws.test.Status.WAITINGLIST);
            break;
            case ONGOING: borrowingWS.setStatus(com.openclassrooms.biblioback.ws.test.Status.ONGOING);
            break;
            case NONE: borrowingWS.setStatus(com.openclassrooms.biblioback.ws.test.Status.NONE);
            break;
            case DENIED: borrowingWS.setStatus(com.openclassrooms.biblioback.ws.test.Status.DENIED);
                break;
            default: borrowingWS.setStatus(com.openclassrooms.biblioback.ws.test.Status.NONE);
        }
        logger.info("statut ws: " + borrowingWS.getStatus());

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

        switch (borWS.getStatus()){
            case WAITINGLIST: bor.setStatus(Status.WAITINGLIST);
                break;
            case ONGOING: bor.setStatus(Status.ONGOING);
                break;
            case NONE: bor.setStatus(Status.NONE);
                break;
            case DENIED: bor.setStatus(Status.DENIED);
                break;
            default: bor.setStatus(Status.NONE);
        }

        logger.info("statut: " + bor.getStatus());

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
