package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.conversions.BookConversion;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.BorrowingProperty;
import com.openclassrooms.entities.Status;
import com.openclassrooms.repositories.PropertiesRepository;
import com.openclassrooms.services.IAppUserService;
import com.openclassrooms.services.IBookService;
import com.openclassrooms.services.IBorrowingService;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    PropertiesRepository propertiesRepository;

    @Autowired
    BorrowingConversion borrowingConversion;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("ajout d'un nouvel emprunt:" + request.getBookId());

        BorrowingAddResponse response = new BorrowingAddResponse();
        com.openclassrooms.entities.AppUser appUser = appUserService.getAppUserById(request.getAppUserId());
        BookEntity book = bookService.getBookById(request.getBookId());
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(book, appUser);

        logger.info("already borrowed ? " + borrowingService.alreadyBorrowed(appUser, book));
        if(borrowingService.alreadyBorrowed(appUser, book)) {
            borrowing.setStatus(Status.DENIED);
            response.setBorrowing(borrowingConversion.toWS(borrowing));
            return response;
        }
        //if no more book available
        if (book.getNumber() < 1) {
            logger.info("The book is not available: " + book.getNumber());

            //if waiting list greater than twice the total number of books, reservation not possible
            if (borrowingService.getBorrowingsByBookAndStatus(book, Status.WAITINGLIST).size() >= borrowingService.getBorrowingsByBookAndStatus(book, Status.ONGOING).size() * 2) {
                borrowing.setStatus(Status.NONE);
                response.setBorrowing(borrowingConversion.toWS(borrowing));
                logger.info("Waiting list full!");
                return response;

            } else {
                borrowing.setStatus(Status.WAITINGLIST);
                borrowing.setWaitingListOrder(waitingListPosition(borrowing));
                logger.info("The book is on the waiting list, position: " + borrowing.getWaitingListOrder());
            }

        } else {
            borrowing.setStatus(Status.ONGOING);
            book.setNumber(book.getNumber() - 1);
            logger.info("borrowing successful");
        }

        bookService.updateBook(book);
        borrowingService.newBorrowing(borrowing);

        response.setBorrowing(borrowingConversion.toWS(borrowing));
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

        if (borrowing.getStatus() == Status.WAITINGLIST) {
            response.setConfirmation(false);
            return response;
        }
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
        else if (borrowing.getDueReturnDate().before(new Date()))
            response.setCodeResp(3);
        else if (borrowing.getStatus() == Status.WAITINGLIST)
            response.setCodeResp(4);
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingsDeleteByIdRequest")
    @ResponsePayload
    @Transactional
    public BorrowingsDeleteByIdResponse deleteBorrowingListById(@RequestPayload BorrowingsDeleteByIdRequest request){
        logger.info("entering deleteBorrowingListById");
        BorrowingsDeleteByIdResponse response = new BorrowingsDeleteByIdResponse();

        borrowingService.deleteBorrowingListById(request.getBorrowingDeleteById());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowingUpdateRequest")
    @ResponsePayload
    @Transactional
    public BorrowingUpdateResponse updateBorrowing(@RequestPayload BorrowingUpdateRequest request){
        BorrowingUpdateResponse response = new BorrowingUpdateResponse();
        logger.info("dans update borrowing: " + request.getBorrowing());
        borrowingService.updateBorrowing(borrowingConversion.toEntity(request.getBorrowing()));
        logger.info("dans update borrowing 2");
        response.setBorrowing(request.getBorrowing());
        logger.info("dans update borrowing 3");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePropertiesRequest")
    @ResponsePayload
    public UpdatePropertiesResponse updateProperties(@RequestPayload UpdatePropertiesRequest request){
        UpdatePropertiesResponse response = new UpdatePropertiesResponse();
        BorrowingProperty property = propertiesRepository.getByLibraryId(request.getLibraryId());
        property.setBorrowingExtensionLength(request.getExtensionLength());
        property.setBorrowingLength(request.getBorrowingLength());
        propertiesRepository.save(property);
        response.setConfirmation(true);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBorrowingLengthRequest")
    @ResponsePayload
    public GetBorrowingLengthResponse getBorrowingLength(@RequestPayload GetBorrowingLengthRequest request){
        GetBorrowingLengthResponse response = new GetBorrowingLengthResponse();
        BorrowingProperty property = propertiesRepository.getByLibraryId(request.getLibraryId());
        response.setBorrowingLength(property.getBorrowingLength());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExtensionLengthRequest")
    @ResponsePayload
    public GetExtensionLengthResponse getExtensionLength(@RequestPayload GetExtensionLengthRequest request){
        GetExtensionLengthResponse response = new GetExtensionLengthResponse();
        BorrowingProperty property = propertiesRepository.getByLibraryId(request.getLibraryId());
        response.setExtensionLength(property.getBorrowingLength());
        return response;
    }

    protected int waitingListPosition(com.openclassrooms.entities.Borrowing borrowing){

        logger.info("waitingListPositonMethod");
        List<com.openclassrooms.entities.Borrowing> borrowings = borrowingService.getBorrowingsByBook(borrowing.getBook());
        ArrayList<Integer> positions = new ArrayList<>();

        for (com.openclassrooms.entities.Borrowing b: borrowings) {
            positions.add(b.getWaitingListOrder());
        }
        //looks for the maximum waiting list position and add 1
        return Collections.max(positions) + 1;
    }





}

