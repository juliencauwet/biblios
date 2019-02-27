package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.*;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookAction extends ActionSupport{

    TestPortService service = new TestPortService();
    TestPort testPort = service.getTestPortSoap11();

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Book> books = null;

    private Book book;
    private int id;
    private String title;
    private String authorFirstName;
    private String authorName;
    private int number;

    private int waitingListNumber;
    private Date nextReturn;

    private List<StateOfBorrowing> states;

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }

    public String getBookById(){
        BookGetByIdRequest request = new BookGetByIdRequest();
        request.setId(id);
        setBook(testPort.bookGetById(request).getBookGet());
        return SUCCESS;
    }

    public String getBookByTitle(){
        BookGetRequest request = new BookGetRequest();
        request.setTitle(title);
        setBooks(testPort.bookGet(request).getBookGet());

        return SUCCESS;
    }

    public String getAllBooks(){
        setBooks(testPort.bookGetAll(new BookGetAllRequest()).getBookGetAll());
        StateOfBorrowingRequest request = new StateOfBorrowingRequest();
        states = new ArrayList<>();
        for(Book b :books){
            request.setBookId(b.getId());
            logger.info(testPort.stateOfBorrowing(request).getStateOfBorrowing().getBook().getTitle());
            states.add(testPort.stateOfBorrowing(request).getStateOfBorrowing());
        }
        for (StateOfBorrowing sB: states) {
            logger.info("sB: " + sB.getBook().getTitle() + " " + sB.getWaitingListNumber() + " " + sB.getNextReturn());
        }
        return SUCCESS;
    }

    public String addBook() throws Exception {
        BookAddRequest request = new BookAddRequest();
        request.setAuthorFirstName(authorFirstName);
        request.setAuthorName(authorName);
        request.setTitle(title);
        request.setNumber(number);

        testPort.bookAdd(request);
        addActionMessage(number +" ouvrage(s) de" + title + " de l'auteur " + authorFirstName + " " + authorName + " a/ont été enregistré(s)");

        return SUCCESS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<StateOfBorrowing> getStates() {
        return states;
    }

    public void setStates(List<StateOfBorrowing> states) {
        this.states = states;
    }

    public int getWaitingListNumber() {
        return waitingListNumber;
    }

    public void setWaitingListNumber(int waitingListNumber) {
        this.waitingListNumber = waitingListNumber;
    }

    public Date getNextReturn() {
        return nextReturn;
    }

    public void setNextReturn(Date nextReturn) {
        this.nextReturn = nextReturn;
    }
}
