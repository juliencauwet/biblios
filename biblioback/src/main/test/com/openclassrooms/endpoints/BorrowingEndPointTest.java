package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.borrowing.BorrowingAddResponse;
import com.openclassrooms.biblioback.ws.test.BorrowingAddRequest;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.entities.AppUser;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.services.AppUserService;
import com.openclassrooms.services.BorrowingService;
import com.openclassrooms.services.IAppUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BorrowingEndPointTest {
    private Date date1;
    private Date date2;
    private Date date3;

    private String strDate1 = "26/05/2018";
    private String strDate2 = "27/06/2018";
    private String strDate3 = "28/07/2018";


    BorrowingService borrowingService = Mockito.mock(BorrowingService.class);

    @Mock
    AppUserService appUserService;

    BookEntity b1 = new BookEntity("La Peste", "Camus", "Albert", 4);
    BookEntity b2 = new BookEntity("L'Ecume des jours", "Vian", "Boris", 1);
    BookEntity b3 = new BookEntity("L'assomoir", "Zola", "Emile", 1);




    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private BorrowingConversion conversion = new BorrowingConversion();

    BorrowingEndPoint endPoint = new BorrowingEndPoint(new BorrowingService());

    @Before
    public void init() throws ParseException {
        date1 = sdf.parse(strDate1);
        date2 = sdf.parse(strDate2);
        date3 = sdf.parse(strDate3);

    }

    @Test
    public void getAllBorrowings() {
    }

    @Test
    public void addBorrowing() {
    //    BorrowingAddRequest request = new BorrowingAddRequest();
    //    BorrowingAddResponse expectedResponse = new BorrowingAddResponse();
    //    when(appUserService.getAppUserById(7)).thenReturn(new AppUser());
    //    request.setAppUserId(7);
    //    request.setBookId(2);
    //    request.setDueReturnDate(conversion.toXmlGregorianCalendar(conversion.dateToGregorianCalendar(date1)));
    //    request.setStartDate(conversion.toXmlGregorianCalendar(conversion.dateToGregorianCalendar(date2)));
    //    expectedResponse.setConfirmation(true);
    //    Assert.assertEquals(expectedResponse, endPoint.addBorrowing(request));
    }

    @Test
    public void getBorrowingById() {
    }

    @Test
    public void getBorrowings() {
    }

    @Test
    public void returnBook() {
    }

    @Test
    public void extendBorrowing() {
    }

    @Test
    public void getExpiredBorrowings() {
    }

    @Test
    public void deleteBorrowingListById() {
    }

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
}