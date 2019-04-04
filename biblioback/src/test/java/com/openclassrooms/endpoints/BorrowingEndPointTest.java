package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.endpoints.BorrowingEndPoint;
import com.openclassrooms.entities.AppUser;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.BorrowingProperty;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private com.openclassrooms.entities.Borrowing borrowingTest;
    private Borrowing borrowingWSTest;

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

    private BorrowingProperty property = new BorrowingProperty();


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

        BorrowingProperty property = new BorrowingProperty();
        property.setBorrowingExtensionLength(4);
        property.setBorrowingLength(4);
    }

    @Test
    public void getAllBorrowings() {
        endPoint.getAllBorrowings(new BorrowingGetAllRequest());
        verify(borrowingService).borrowingReport();
    }

    @Test
    public void addBorrowingIfAlreadyBorrowed() {
        BorrowingAddRequest request = new BorrowingAddRequest();
        BorrowingAddResponse expectedResponse = new BorrowingAddResponse();
        BookEntity book = new BookEntity();
        request.setAppUserId(7);
        request.setBookId(2);

        when(appUserService.getAppUserById(7)).thenReturn(appUserTest);
        when(bookService.getBookById(2)).thenReturn(book);
        when(borrowingService.alreadyBorrowed(appUserTest, book)).thenReturn(true);
        when(borrowingConversion.toWS(any())).thenReturn(borrowingWSTest);
        endPoint.addBorrowing(request);
        verify(borrowingConversion).toWS(any());
    }

    @Test
    public void addBorrowingIfNumberEqualsZero() {
        BorrowingAddRequest request = new BorrowingAddRequest();
        BorrowingAddResponse expectedResponse = new BorrowingAddResponse();
        BookEntity book = new BookEntity();
        book.setNumber(0);
        request.setAppUserId(7);
        request.setBookId(2);

        List<com.openclassrooms.entities.Borrowing> borrowingsOnWaitingList = Arrays.asList(new com.openclassrooms.entities.Borrowing(), new com.openclassrooms.entities.Borrowing());
        List<com.openclassrooms.entities.Borrowing> borrowingsOngoing = Arrays.asList(new com.openclassrooms.entities.Borrowing());

        when(appUserService.getAppUserById(7)).thenReturn(appUserTest);
        when(bookService.getBookById(2)).thenReturn(book);
        when(borrowingService.alreadyBorrowed(appUserTest, book)).thenReturn(false);
        when(borrowingConversion.toWS(any())).thenReturn(borrowingWSTest);
        when(borrowingService.getBorrowingsByBookAndStatus(book, Status.WAITINGLIST)).thenReturn(borrowingsOnWaitingList);
        when(borrowingService.getBorrowingsByBookAndStatus(book, Status.ONGOING)).thenReturn(borrowingsOngoing);
        endPoint.addBorrowing(request);
        verify(borrowingService).getBorrowingsByBookAndStatus(book, Status.ONGOING);
        verify(borrowingService).getBorrowingsByBookAndStatus(book, Status.WAITINGLIST);
    }

    @Test
    public void addBorrowingIfNumberGreaterThanZero() {
        BorrowingAddRequest request = new BorrowingAddRequest();
        BorrowingAddResponse expectedResponse = new BorrowingAddResponse();
        BookEntity book = new BookEntity();
        book.setNumber(2);
        request.setAppUserId(7);
        request.setBookId(2);

        List<com.openclassrooms.entities.Borrowing> borrowingsOnWaitingList = Arrays.asList(new com.openclassrooms.entities.Borrowing(), new com.openclassrooms.entities.Borrowing());
        List<com.openclassrooms.entities.Borrowing> borrowingsOngoing = Arrays.asList(new com.openclassrooms.entities.Borrowing(), new com.openclassrooms.entities.Borrowing());

        when(appUserService.getAppUserById(7)).thenReturn(appUserTest);
        when(bookService.getBookById(2)).thenReturn(book);
        when(borrowingService.alreadyBorrowed(appUserTest, book)).thenReturn(false);
        when(borrowingConversion.toWS(any())).thenReturn(borrowingWSTest);
        when(propertiesRepository.getByLibraryId(1)).thenReturn(property);
        endPoint.addBorrowing(request);
        verify(bookService).updateBook(book);
        verify(borrowingService).newBorrowing(any());
    }


    @Test
    public void getBorrowingById() {
        BorrowingGetRequest request = new BorrowingGetRequest();
        Borrowing b = new Borrowing();
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
        request.setUserId(7);
        Borrowing b = new Borrowing();
        when(borrowingService.getByAppUserId(7)).thenReturn(
                Arrays.asList(
                        new com.openclassrooms.entities.Borrowing(),
                        new com.openclassrooms.entities.Borrowing()
                ));
        when(borrowingConversion.toWS(any())).thenReturn(b);

        Assert.assertEquals(2, endPoint.getBorrowings(request).getBorrowingGetCurrent().size());
    }

    /**
     * teste que la réponse est fausse si le statut n'est pas  ONGOING
     */
    @Test
    public void returnBook_IfNotOnGoing() {

        BorrowingReturnRequest borrowingReturnRequest = new BorrowingReturnRequest();
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.RETURNED);
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
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.ONGOING);
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
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.ONGOING);
        com.openclassrooms.entities.Borrowing borrowing1 = new com.openclassrooms.entities.Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.WAITINGLIST);
        com.openclassrooms.entities.Borrowing borrowing2 = new com.openclassrooms.entities.Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.WAITINGLIST);
        List<com.openclassrooms.entities.Borrowing> borrowings = Arrays.asList(
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
        com.openclassrooms.entities.Borrowing borrowing3 = new com.openclassrooms.entities.Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.WAITINGLIST);
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
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b2, new Date(), new Date(), new Date(), Status.WAITINGLIST);
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
        com.openclassrooms.entities.Borrowing  borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b1, date1, date2, date3, Status.ONGOING);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        request.setBorrowingId(1);
        borrowing.setExtended(true);
        Assert.assertEquals(2, endPoint.extendBorrowing(request).getCodeResp());
    }

    @Test
    public void extendBorrowingIfDueReturnDateExpired() {
        BorrowingExtendRequest request = new BorrowingExtendRequest();
        request.setBorrowingId(1);
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b1, date1, date2, date3, Status.ONGOING);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        request.setBorrowingId(1);
        Assert.assertEquals(3, endPoint.extendBorrowing(request).getCodeResp());
    }

    @Test
    public void extendBorrowingIfStatusIsNotONGOING() {
        BorrowingExtendRequest request = new BorrowingExtendRequest();
        request.setBorrowingId(1);
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b1, date1, date4, null, Status.RETURNED);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        request.setBorrowingId(1);
        Assert.assertEquals(4, endPoint.extendBorrowing(request).getCodeResp());
    }

    @Test
    public void extendBorrowingIfStatusAllIsOK() {
        BorrowingExtendRequest request = new BorrowingExtendRequest();
        request.setBorrowingId(1);
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(new AppUser(), b1, date1, date4, null, Status.ONGOING);
        when(borrowingService.getById(1)).thenReturn(borrowing);
        when(propertiesRepository.getByLibraryId(1)).thenReturn(property);
        endPoint.extendBorrowing(request);
        verify(borrowingService).updateBorrowing(borrowing);
    }

    @Test
    public void getExpiredBorrowings() {
        BorrowingGetExpiredRequest request = new BorrowingGetExpiredRequest();
        List<com.openclassrooms.entities.Borrowing> borrowings = Arrays.asList(new com.openclassrooms.entities.Borrowing(), new com.openclassrooms.entities.Borrowing());
        when(borrowingService.getExpiredBorrowing()).thenReturn(borrowings);
        when(borrowingConversion.toWS(any())).thenReturn(borrowingWSTest);
        endPoint.getExpiredBorrowings(request);
        verify(borrowingConversion, times(2)).toWS(any());

    }

    @Test
    public void deleteBorrowingListById() {
    }

    /**
     * teste que la méthode renvoie la position suivante
     */
    @Test
    public void waitingListPositonTest(){

        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(b1);
        List<com.openclassrooms.entities.Borrowing> borrowings = Arrays.asList(
                new com.openclassrooms.entities.Borrowing(b1, 1),
                new com.openclassrooms.entities.Borrowing(b1, 2),
                new com.openclassrooms.entities.Borrowing(b1, 3),
                new com.openclassrooms.entities.Borrowing(b1, 4)
        );

        when(borrowingService.getBorrowingsByBook(b1)).thenReturn(borrowings);
        Assert.assertTrue(endPoint.waitingListPosition(borrowing) == 5);
    }

    @Test
    public void getExtensionLength() {
        GetExtensionLengthRequest request = new GetExtensionLengthRequest();
        request.setLibraryId(1);
        when(propertiesRepository.getByLibraryId(1)).thenReturn(property);
        endPoint.getExtensionLength(request);
        verify(propertiesRepository).getByLibraryId(1);

    }

    @Test
    public void getBorrowingLength() {
        GetBorrowingLengthRequest request = new GetBorrowingLengthRequest();
        request.setLibraryId(1);
        when(propertiesRepository.getByLibraryId(1)).thenReturn(property);
        endPoint.getBorrowingLength(request);
        verify(propertiesRepository).getByLibraryId(1);
    }

    @Test
    public void updateProperties() {
        UpdatePropertiesRequest request = new UpdatePropertiesRequest();
        request.setExtensionLength(2);
        request.setLibraryId(1);
        request.setBorrowingLength(3);
        when(propertiesRepository.getByLibraryId(1)).thenReturn(property);
        Assert.assertTrue(endPoint.updateProperties(request).isConfirmation());
    }

    @Test
    public void updateBorrowing() {
        BorrowingUpdateRequest request = new BorrowingUpdateRequest();
        request.setBorrowing(borrowingWSTest);
        endPoint.updateBorrowing(request);
        verify(borrowingConversion).toEntity(any());
    }

    @Test
    public void pickup() {
        PickupRequest request = new PickupRequest();
        borrowingWSTest = new Borrowing();
        request.setId(15);
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(b1, 2);
        when(borrowingService.getById(15)).thenReturn(borrowing);
        when(borrowingConversion.toWS(borrowing)).thenReturn(borrowingWSTest);
        when(propertiesRepository.getByLibraryId(1)).thenReturn(property);
        endPoint.pickup(request);
        Assert.assertEquals(Status.ONGOING, borrowing.getStatus());
    }


    @Test
    public void sendEmail() {
        EmailRequest request = new EmailRequest();
        request.setText("test");
        endPoint.sendEmail(request);
        verify(borrowingService).sendEmail(request.getText());
    }

    @Test
    public void cancelBorrowingIfIsNotOnWaitingList() {
        DeleteBorrowingRequest request = new DeleteBorrowingRequest();
        request.setId(2);
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(bookTest, 2);
        List<com.openclassrooms.entities.Borrowing> borrowings = Arrays.asList(new com.openclassrooms.entities.Borrowing(bookTest,4), new com.openclassrooms.entities.Borrowing(bookTest, 3));
        when(borrowingService.getById(2)).thenReturn(borrowing);
        when(borrowingService.getBorrowingsByBookAndStatus(bookTest, Status.WAITINGLIST)).thenReturn(borrowings);
        borrowing.setStatus(Status.ONGOING);
        Assert.assertFalse(endPoint.cancelBorrowing(request).isConfirmation());
    }

    @Test
    public void cancelBorrowingIfIsOnWaitingList() {
        DeleteBorrowingRequest request = new DeleteBorrowingRequest();
        request.setId(2);
        com.openclassrooms.entities.Borrowing borrowing = new com.openclassrooms.entities.Borrowing(bookTest, 2);
        List<com.openclassrooms.entities.Borrowing> borrowings = Arrays.asList(new com.openclassrooms.entities.Borrowing(bookTest, 4), new com.openclassrooms.entities.Borrowing(bookTest, 3));
        when(borrowingService.getById(2)).thenReturn(borrowing);
        when(borrowingService.getBorrowingsByBookAndStatus(bookTest, Status.WAITINGLIST)).thenReturn(borrowings);
        borrowing.setStatus(Status.WAITINGLIST);
        borrowing.setWaitingListOrder(2);
        Assert.assertTrue(endPoint.cancelBorrowing(request).isConfirmation());
    }

    @Test
    public void getExpiringSoonBorrowings() {
        BorrowingsExpiringSoonRequest request = new BorrowingsExpiringSoonRequest();
        List<com.openclassrooms.entities.Borrowing> borrowings = Arrays.asList(new com.openclassrooms.entities.Borrowing(), new com.openclassrooms.entities.Borrowing());
        when(borrowingService.expiringSoonBorrowings()).thenReturn(borrowings);
        when(borrowingConversion.toWS(any())).thenReturn(borrowingWSTest);
        endPoint.getExpiringSoonBorrowings(request);
        verify(borrowingConversion, times(2)).toWS(any());
    }

    @Test
    public void setDRD() {

    }



}
