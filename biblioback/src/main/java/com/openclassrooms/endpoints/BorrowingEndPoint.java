package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.conversions.BookConversion;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.services.IAppUserService;
import com.openclassrooms.services.IBookService;
import com.openclassrooms.services.IBorrowingService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Endpoint
public class BorrowingEndPoint {
    private static final String NAMESPACE_URI = "http://test.ws.biblioback.openclassrooms.com";
    private static final Logger log = Logger.getLogger(BorrowingEndPoint.class);

    @Autowired
    private IBorrowingService borrowingService;

    @Autowired
    private IBookService bookService;
    @Autowired
    private IAppUserService appUserService;

    BorrowingConversion borrowingConversion = new BorrowingConversion();

    public BorrowingEndPoint(IBorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    /**
     * @param request
     * @return response: liste de tous les emprunts
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingGetAllRequest")
    @ResponsePayload
    public BorrowingGetAllResponse getAllBorrowings(@RequestPayload BorrowingGetAllRequest request) {
        BorrowingGetAllResponse response = new BorrowingGetAllResponse();

        List<Borrowing> borrowingList = new ArrayList<>();
        List<com.openclassrooms.entities.Borrowing> borrowings = borrowingService.borrowingReport();

        for (int i = 0; i < borrowings.size(); i++) {
            Borrowing b = borrowingConversion.toWS(borrowings.get(i));
            borrowingList.add(b);
        }
        response.getBorrowingGetAll().addAll(borrowingList);
        return response;
    }

    /**
     * enregistre un emprunt
     *
     * @param request
     * @return response: confirmation booléenne que l'emprunt a été enregistré
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingAddRequest")
    @ResponsePayload
    public BorrowingAddResponse addBorrowing(@RequestPayload BorrowingAddRequest request) {

        BorrowingAddResponse response = new BorrowingAddResponse();

        log.info("ajout d'un nouvel emprunt:" + request.getBookId());

        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing();

        com.openclassrooms.entities.AppUser appUser = appUserService.getAppUserById(request.getAppUserId());
        BookEntity book = bookService.getBookById(request.getBookId());
        if (book.getNumber() < 1)
            response.setConfirmation(false);
        else {
            borrowing.setAppUser(appUser);
            borrowing.setBookEntity(book);
            borrowing.setStartDate(request.getStartDate().toGregorianCalendar().getTime());
            borrowing.setDueReturnDate(request.getDueReturnDate().toGregorianCalendar().getTime());
            book.setNumber(book.getNumber() - 1);
            bookService.updateBook(book);
            borrowingService.newBorrowing(borrowing);
            response.setConfirmation(true);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingGetRequest")
    @ResponsePayload
    public BorrowingGetResponse getBorrowingById(@RequestPayload BorrowingGetRequest request){
        BorrowingGetResponse response = new BorrowingGetResponse();
        com.openclassrooms.entities.Borrowing borrowing = borrowingService.getById(request.getId());
        Borrowing b = borrowingConversion.toWS(borrowing);
        response.setBorrowing(b);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingGetCurrentRequest")
    @ResponsePayload
    public BorrowingGetCurrentResponse getBorrowings(@RequestPayload BorrowingGetCurrentRequest request){

        BorrowingGetCurrentResponse response = new BorrowingGetCurrentResponse();

        List<Borrowing> wsBors = new ArrayList<>();
        List<com.openclassrooms.entities.Borrowing> borrowings = borrowingService.getByAppUserId(request.getUserId());


        for(int i = 0; i < borrowings.size(); i++){
            Borrowing b = borrowingConversion.toWS(borrowings.get(i));
            wsBors.add(b);
        }
        response.getBorrowingGetCurrent().addAll(wsBors);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingReturnRequest")
    @ResponsePayload
    public BorrowingReturnResponse returnBook(@RequestPayload BorrowingReturnRequest request) {

        BorrowingReturnResponse response = new BorrowingReturnResponse();

        com.openclassrooms.entities.Borrowing borrowing = borrowingService.getById(request.getId());
        BookEntity bookEntity = borrowing.getBookEntity();

        //remet le livre dans le stock
        bookEntity.setNumber(bookEntity.getNumber() + 1);
        bookService.updateBook(bookEntity);

        //sauve la date de retour
        borrowing.setReturnDate(new Date());
        borrowingService.updateBorrowing(borrowing);

        response.setConfirmation(true);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingExtendRequest")
    @ResponsePayload
    public BorrowingExtendResponse extendBorrowing(@RequestPayload BorrowingExtendRequest request){
        BorrowingExtendResponse response = new BorrowingExtendResponse();
        com.openclassrooms.entities.Borrowing borrowing = borrowingService.getById(request.getBorrowingId());

        if(borrowing.getExtended())
            response.setCodeResp(2);
        else {
            borrowing.setExtended(true);
            borrowing.setDueReturnDate(request.getNewDueReturnDate().toGregorianCalendar().getTime());
            response.setCodeResp(1);
            borrowingService.updateBorrowing(borrowing);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingGetExpiredRequest")
    @ResponsePayload
    public BorrowingGetExpiredResponse getExpiredBorrowings(@RequestPayload BorrowingGetExpiredRequest request){

        BorrowingGetExpiredResponse response = new BorrowingGetExpiredResponse();

        List<Borrowing> wsBors = new ArrayList<>();
        List<com.openclassrooms.entities.Borrowing> borrowings = borrowingService.getExpiredBorrowing();


        for(int i = 0; i < borrowings.size(); i++){
            Borrowing b = borrowingConversion.toWS(borrowings.get(i));
            wsBors.add(b);
        }
        response.getBorrowingGetExpired().addAll(wsBors);
        return response;
    }

}

