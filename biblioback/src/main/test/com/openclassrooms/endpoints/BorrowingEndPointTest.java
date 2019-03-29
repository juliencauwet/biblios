package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.entities.AppUser;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;
import com.openclassrooms.repositories.PropertiesRepository;
import com.openclassrooms.services.AppUserService;
import com.openclassrooms.services.BookService;
import com.openclassrooms.services.BorrowingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BorrowingEndPointTest {
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;

    private String strDate1 = "26/05/2018";
    private String strDate2 = "27/06/2018";
    private String strDate3 = "28/07/2018";
    private String strDate4 = "28/07/2020";

    private AppUser appUserTest;
    private BookEntity bookTest;
    private Borrowing borrowingTest;
    private com.openclassrooms.biblioback.ws.test.Borrowing borrowingWSTest;

    @Mock
    BorrowingService borrowingService;

    @Mock
    BookService bookService;

    @Mock
    BorrowingConversion borrowingConversion;

    @Mock
    AppUserService appUserService;

    @Mock
    PropertiesRepository propertiesRepository;

    @InjectMocks
    BorrowingEndPoint endPoint;


    @Before
    public void setUp()throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    BookEntity b1 = new BookEntity("La Peste", "Camus", "Albert", 4);
    BookEntity b2 = new BookEntity("L'Ecume des jours", "Vian", "Boris", 1);
    BookEntity b3 = new BookEntity("L'assomoir", "Zola", "Emile", 1);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private BorrowingConversion conversion = new BorrowingConversion();

    @Before
    public void init() throws ParseException {
        date1 = sdf.parse(strDate1);
        date2 = sdf.parse(strDate2);
        date3 = sdf.parse(strDate3);
        date4 = sdf.parse(strDate4);
    }

    @Test
    public void getAllBorrowings() {
        endPoint.getAllBorrowings(new BorrowingGetAllRequest());
        verify(borrowingService).borrowingReport();
    }

    //@Test
    //public void addBorrowingIfAlreadyBorrowed() {
    //    BorrowingAddRequest request = new BorrowingAddRequest();
    //    BorrowingAddResponse expectedResponse = new BorrowingAddResponse();
    //    BookEntity book = new BookEntity();
    //    book.setNumber(0);
    //    request.setAppUserId(7);
    //    request.setBookId(2);

    //    when(appUserService.getAppUserById(7)).thenReturn(appUserTest);
    //    when(bookService.getBookById(2)).thenReturn(book);
    //    when(borrowingService.alreadyBorrowed(appUserTest, bookTest)).thenReturn(false);
    //    when(borrowingConversion.toWS(any())).thenReturn(borrowingWSTest);
    //    Assert.assertEquals(expectedResponse, endPoint.addBorrowing(request));
    //}

    @Test
    public void getBorrowingById() {
        BorrowingGetRequest request = new BorrowingGetRequest();
        com.openclassrooms.biblioback.ws.test.Borrowing b = new com.openclassrooms.biblioback.ws.test.Borrowing();
        Borrowing borrowing = new Borrowing();
        request.setId(1);
        endPoint.getBorrowingById(request);
        verify(borrowingService).getById(1);

    }

    /**
     * teste que la méthode renvoie le nombre correspondant d'emprunts
     */
    @Test
    public void getBorrowings() {
        BorrowingGetCurrentRequest request = new BorrowingGetCurrentRequest();
        BorrowingGetCurrentResponse response = new BorrowingGetCurrentResponse();
        request.setUserId(7);
        com.openclassrooms.biblioback.ws.test.Borrowing b = new com.openclassrooms.biblioback.ws.test.Borrowing();
        when(borrowingService.getByAppUserId(7)).thenReturn(
                Arrays.asList(
                        new Borrowing(),
                        new Borrowing())
                );
        when(borrowingConversion.toWS(any())).thenReturn(b);

        Assert.assertEquals(2, endPoint.getBorrowings(request).getBorrowingGetCurrent().size());
    }

    /**
     * teste que la réponse est fausse si le statut n'est pas  ONGOING
     */
    @Test
    public void returnBook_IfNotOnGoing() {

        BorrowingReturnRequest borrowingReturnRequest = new BorrowingReturnRequest();
        BorrowingReturnResponse borrowingReturnResponse = new BorrowingReturnResponse();
        Borrowing borrowing = new Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.RETURNED);
        borrowingReturnRequest.setId(1);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        Assert.assertFalse(endPoint.returnBook(borrowingReturnRequest).isConfirmation());
    }

    /**
     * teste que tout est correct si le statut est ONGOING
     */
    @Test
    public void returnBook_IfOnGoing() {

        BorrowingReturnRequest borrowingReturnRequest = new BorrowingReturnRequest();
        BorrowingReturnResponse borrowingReturnResponse = new BorrowingReturnResponse();
        Borrowing borrowing = new Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.ONGOING);
        borrowingReturnRequest.setId(1);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        Assert.assertTrue(endPoint.returnBook(borrowingReturnRequest).isConfirmation());
    }

    /**
     * teste que toutt se passe correctement s'il y a des waiting list
     */
    @Test
    public void returnBook_IfWaitingList() {

        BorrowingReturnRequest borrowingReturnRequest = new BorrowingReturnRequest();
        BorrowingReturnResponse borrowingReturnResponse = new BorrowingReturnResponse();
        Borrowing borrowing = new Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.ONGOING);
        Borrowing borrowing1 = new Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), com.openclassrooms.entities.Status.WAITINGLIST);
        Borrowing borrowing2 = new Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), com.openclassrooms.entities.Status.WAITINGLIST);
        List<Borrowing> borrowings = Arrays.asList(
                borrowing1,
                borrowing2
        );
        borrowingReturnRequest.setId(1);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        when(borrowingService.getBorrowingsByBookAndStatus(b2, Status.WAITINGLIST)).thenReturn(borrowings);
        endPoint.returnBook(borrowingReturnRequest);
        verify(bookService).updateBook(b2);

    }

    /**
     * teste que l'ordre de la liste d'attente descend
     */
    @Test
    public void forwardWaitingListOrderIfGreaterThan2(){
        Borrowing borrowing3 = new Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), com.openclassrooms.entities.Status.WAITINGLIST);
        borrowing3.setWaitingListOrder(3);
        endPoint.forwardWaitingListOrder(borrowing3);
        Assert.assertEquals(2, borrowing3.getWaitingListOrder());
        verify(borrowingService).updateBorrowing(borrowing3);
    }

    /**
     * teste que la si la position dans l'attente on appelle la méthode pourenvoyer un email au suivant
     */
    @Test
    public void forwardWaitingListOrderIfEquals1(){
        Borrowing borrowing = new Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), com.openclassrooms.entities.Status.WAITINGLIST);
        borrowing.setWaitingListOrder(1);
        endPoint.forwardWaitingListOrder(borrowing);
        Assert.assertEquals(0, borrowing.getWaitingListOrder());
        verify(borrowingService).updateBorrowing(borrowing);
        verify(borrowingService).sendEmailToNextBorrower(borrowing);
    }

    @Test
    public void extendBorrowingIfAlreadyExtended() {
        BorrowingExtendRequest request = new BorrowingExtendRequest();
        request.setBorrowingId(1);
        Borrowing borrowing = new Borrowing(new AppUser(), b1, date1, date2, date3, Status.ONGOING);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        request.setBorrowingId(1);
        borrowing.setExtended(true);
        Assert.assertEquals(2, endPoint.extendBorrowing(request).getCodeResp());
    }

    @Test
    public void extendBorrowingIfDueReturnDateExpired() {
        BorrowingExtendRequest request = new BorrowingExtendRequest();
        request.setBorrowingId(1);
        Borrowing borrowing = new Borrowing(new AppUser(), b1, date1, date2, date3, Status.ONGOING);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        request.setBorrowingId(1);
        Assert.assertEquals(3, endPoint.extendBorrowing(request).getCodeResp());
    }

    @Test
    public void extendBorrowingIfStatusIsNotONGOING() {
        BorrowingExtendRequest request = new BorrowingExtendRequest();
        request.setBorrowingId(1);
        Borrowing borrowing = new Borrowing(new AppUser(), b1, date1, date4, null, Status.RETURNED);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        request.setBorrowingId(1);
        Assert.assertEquals(4, endPoint.extendBorrowing(request).getCodeResp());
    }

  //  @Test
  //  public void extendBorrowingIfStatusAllIsOK() {
  //      BorrowingExtendRequest request = new BorrowingExtendRequest();
  //      request.setBorrowingId(1);
  //      Borrowing borrowing = new Borrowing(new AppUser(), b1, date1, date4, null, Status.ONGOING);
  //      when(borrowingService.getById(1)).thenReturn(borrowing);
  //      request.setBorrowingId(1);
  //      endPoint.extendBorrowing(request);
  //      verify(borrowingService).updateBorrowing(borrowing);
  //  }

    @Test
    public void getExpiredBorrowings() {
    }

    @Test
    public void deleteBorrowingListById() {
    }

    /**
     * teste que la méthode renvoie la position suivante
     */
    @Test
    public void waitingListPositonTest(){

        Borrowing borrowing = new Borrowing(b1);
        List<Borrowing> borrowings = Arrays.asList(
                new Borrowing(b1, 1),
                new Borrowing(b1, 2),
                new Borrowing(b1, 3),
                new Borrowing(b1, 4)
        );

        when(borrowingService.getBorrowingsByBook(b1)).thenReturn(borrowings);
        Assert.assertTrue(endPoint.waitingListPosition(borrowing) == 5);
    }

    @Test
    public void alreadyBorrowed() {

    }

    @Test
    public void setDRD() {

    }

    @Test
    public void sendEmail() {
        EmailRequest request = new EmailRequest();
        request.setText("test");
        endPoint.sendEmail(request);
        verify(borrowingService).sendEmail(request.getText());
    }



}
