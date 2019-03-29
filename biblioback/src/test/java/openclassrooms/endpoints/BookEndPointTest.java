package openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.BookAddRequest;
import com.openclassrooms.biblioback.ws.test.BookGetByIdRequest;
import com.openclassrooms.biblioback.ws.test.StateOfBorrowingRequest;
import com.openclassrooms.biblioback.ws.test.StateOfBorrowingResponse;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.endpoints.BookEndPoint;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
        BookAddRequest request = new BookAddRequest();
        request.setTitle("testAddBook");
        request.setAuthorFirstName("test");
        request.setNumber(4);
        endPoint.addBook(request);
        verify(bookService).addBook(any());
    }

    @Test
    public void getBookById() {
        BookGetByIdRequest request = new BookGetByIdRequest();
        request.setId(3);
        BookEntity book = new BookEntity("test", "test", "test", 3);
        when(bookService.getBookById(3)).thenReturn(book);
        Assert.assertEquals("test", endPoint.getBookById(request).getBookGet().getTitle());

    }

    @Test
    public void getBooksByName() {
    }

    @Test
    public void getAllBooks() {
        endPoint.getAllBooks();
        verify(bookService).getAllBooks();
    }

    @Test
    public void getStateOfBorrowing() {
        StateOfBorrowingRequest request = new StateOfBorrowingRequest();
        request.setBookId(1);
        StateOfBorrowingResponse response = new StateOfBorrowingResponse();

        BookEntity bookEntity = new BookEntity("Test", "test", "test", 0);

        List<Borrowing> books = Arrays.asList(
                new Borrowing(bookEntity),
                new Borrowing(bookEntity),
                new Borrowing(bookEntity)
        );

        when(bookService.getBookById(1)).thenReturn(bookEntity);
        when(borrowingService.getBorrowingsByBookAndStatus(bookEntity, Status.WAITINGLIST)).thenReturn(books);
        when(borrowingService.nextReturnDate(any())).thenReturn(new Date());
        Assert.assertEquals(bookEntity.getTitle() , endPoint.getStateOfBorrowing(request).getStateOfBorrowing().getBook().getTitle());

    }

    @Test
    public void convertBooks() {
        BookEntity book1 = new BookEntity();
        BookEntity book2 = new BookEntity();
        BookEntity book3 = new BookEntity();
        List<BookEntity> books = Arrays.asList(book1, book2, book3);

        Assert.assertEquals(3, endPoint.convertBooks(books).size());
    }
}
