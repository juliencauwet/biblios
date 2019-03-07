package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.StateOfBorrowingRequest;
import com.openclassrooms.biblioback.ws.test.StateOfBorrowingResponse;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;
import com.openclassrooms.services.BookService;
import com.openclassrooms.services.BorrowingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class BookEndPointTest {

    @Mock
    BorrowingService borrowingService;

    @Mock
    BookService bookService;

    @InjectMocks
    BookEndPoint endPoint;

    private BorrowingConversion conversion = new BorrowingConversion();


    @Before
    public void setUp()throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addBook() {
    }

    @Test
    public void getBookById() {
    }

    @Test
    public void getBooksByName() {
    }

    @Test
    public void getAllBooks() {
    }

    @Test
    public void getStateOfBorrowing() {
        StateOfBorrowingRequest request = new StateOfBorrowingRequest();
        request.setBookId(1);
        StateOfBorrowingResponse response = new StateOfBorrowingResponse();
        BookEntity bookEntity = new BookEntity("Test", "test", "test", 0);

        List<com.openclassrooms.entities.Borrowing> books = Arrays.asList(
                new Borrowing(bookEntity),
                new Borrowing(bookEntity),
                new Borrowing(bookEntity)
        );

        when(bookService.getBookById(anyInt())).thenReturn(bookEntity);
        when(borrowingService.getBorrowingsByBookAndStatus(bookEntity, Status.WAITINGLIST)).thenReturn(books);
        when(borrowingService.nextReturnDate(any())).thenReturn(new Date());
        Assert.assertEquals(response , endPoint.getStateOfBorrowing(request));

    }

    @Test
    public void convertBooks() {
    }
}