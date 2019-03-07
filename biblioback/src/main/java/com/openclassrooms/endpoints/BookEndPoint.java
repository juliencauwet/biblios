package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.conversions.BookConversion;
import com.openclassrooms.conversions.BorrowingConversion;
import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.entities.Status;
import com.openclassrooms.services.BookService;
import com.openclassrooms.services.IBookService;
import com.openclassrooms.services.IBorrowingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class BookEndPoint {
    private static final String NAMESPACE_URI = "http://test.ws.biblioback.openclassrooms.com";

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBorrowingService borrowingService;

    private BookConversion bookConversion = new BookConversion();

    private BorrowingConversion borrowingConversion = new BorrowingConversion();

    @Autowired
    public BookEndPoint(BookService bookService){
        this.bookService = bookService;
    }


    /**
     *
     * @param request
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bookAddRequest")
    @ResponsePayload
    public void addBook(@RequestPayload BookAddRequest request){

        BookEntity book = new BookEntity();
        book.setAuthorFirstName(request.getAuthorFirstName());
        book.setAuthorName(request.getAuthorName());
        book.setTitle(request.getTitle());
        book.setNumber(book.getNumber()+request.getNumber());
        bookService.addBook(book);
    }

     @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bookGetByIdRequest")
     @ResponsePayload
     public BookGetByIdResponse getBookById(@RequestPayload BookGetByIdRequest request) {

         BookGetByIdResponse response = new BookGetByIdResponse();
         Book book = new Book();

         BeanUtils.copyProperties(bookService.getBookById(request.getId()), book);
         response.setBookGet(book);

         return response;
     }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bookGetRequest")
    @ResponsePayload
    public BookGetResponse getBooksByName(@RequestPayload BookGetRequest request){

        BookGetResponse response = new BookGetResponse();

        List<BookEntity> bookEntities = bookService.getBookByTitle(request.getTitle());

        response.getBookGet().addAll(convertBooks(bookEntities));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bookGetAllRequest")
    @ResponsePayload
    public BookGetAllResponse getAllBooks() {

        BookGetAllResponse response = new BookGetAllResponse();
        List<BookEntity> bookEntities = bookService.getAllBooks();
        response.getBookGetAll().addAll(convertBooks(bookEntities));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "stateOfBorrowingRequest")
    @ResponsePayload
    public StateOfBorrowingResponse getStateOfBorrowing(@RequestPayload StateOfBorrowingRequest request){
        StateOfBorrowingResponse response = new StateOfBorrowingResponse();
        BookEntity book = bookService.getBookById(request.getBookId());
        StateOfBorrowing state = new StateOfBorrowing();

        state.setBook(bookConversion.bookEntityToBook(book));
        state.setWaitingListNumber(borrowingService.getBorrowingsByBookAndStatus(book, Status.WAITINGLIST).size());
        //searches for the next return date and converts into XML

        state.setNextReturn(borrowingConversion.toXmlGregorianCalendar(borrowingConversion.dateToGregorianCalendar(borrowingService.nextReturnDate(book))));
        response.setStateOfBorrowing(state);
        return response;
    }



    List<Book> convertBooks(List<BookEntity> bookEntities){
        List<Book> books = new ArrayList<>();

        for(int i = 0; i < bookEntities.size(); i++){
            Book b = new Book();
            BeanUtils.copyProperties(bookEntities.get(i), b);
            books.add(b);
        }
        return books;
    }

}

