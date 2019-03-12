package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.config.PropSource;
import com.opensymphony.xwork2.ActionSupport;
import freemarker.core.ReturnInstruction;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.*;

public class BorrowingAction extends ActionSupport {

    private static final Logger log = LoggerFactory.getLogger(BorrowingAction.class);


    TestPortService service = new TestPortService();
    TestPort testPort = service.getTestPortSoap11();

    PropSource propSource = new PropSource();
    Properties props = propSource.getProps();

    private Date startDate = new Date();

    private String message = "";
    private List<Borrowing> borrowings = null;
    private Borrowing borrowing;

    private List<StateOfBorrowing> bookings = null;
    private StateOfBorrowing booking;

    HttpSession session = ServletActionContext.getRequest().getSession(false);
    private int id;
    private AppUser appUser = (AppUser)session.getAttribute("appUser");
    private Book book;
    private int bookId;

    private Status status;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String getAllBorrowings(){
        setBorrowings(testPort.borrowingGetAll(new BorrowingGetAllRequest()).getBorrowingGetAll());
        return SUCCESS;
    }

    public String getBorrowingById(){
        BorrowingGetRequest request = new BorrowingGetRequest();
        request.setId(id);
        setBorrowing(testPort.borrowingGet(request).getBorrowing());
        return SUCCESS;
    }

    public String getCurrentBorrowings(){
        log.info("Entrée dans getCurrentBorrowings");
        if(session == null || session.getAttribute("appUser") == null) {
            addActionError("Veuillez vous identifier pour pouvoir consulter la liste de vos emprunts.");
            return LOGIN;
        }
        List<Borrowing> allBorrowings = listAllBorrowingsfromUser();

        splitBorrowings(allBorrowings);
        return SUCCESS;
    }

    public String delete(){
        DeleteBorrowingRequest request = new DeleteBorrowingRequest();
        for (Borrowing b: listAllBorrowingsfromUser()
             ) {
            if (b.getBook().getId() == bookId){
                request.setId(b.getId());
                testPort.deleteBorrowing(request);
            }
        }
        return SUCCESS;
    }

    public List<Borrowing> listAllBorrowingsfromUser(){
        BorrowingGetCurrentRequest request = new BorrowingGetCurrentRequest();
        AppUser appUser = (AppUser)session.getAttribute("appUser");
        request.setUserId(appUser.getId());
        return testPort.borrowingGetCurrent(request).getBorrowingGetCurrent();
    }

    public void splitBorrowings(List<Borrowing> allBorrowings){
        borrowings = new ArrayList<>();
        bookings = new ArrayList<>();
        StateOfBorrowingRequest stateOfBorrowingRequest = new StateOfBorrowingRequest();

        for (Borrowing b :
                allBorrowings) {
            if (b.getStatus() == Status.ONGOING )
                borrowings.add(b);

            if (b.getStatus() == Status.WAITINGLIST || b.getStatus() == Status.AVAILABLE) {
                stateOfBorrowingRequest.setBookId(b.getBook().getId());
                bookings.add(testPort.stateOfBorrowing(stateOfBorrowingRequest).getStateOfBorrowing());
            }
        }
    }

    public String borrowThisBook() {

        if(session == null || session.getAttribute("appUser") == null) {
            addActionError("Veuillez vous identifier pour pouvoir effectuer un emprunt.");
            return LOGIN;
        }

        BorrowingAddRequest request = new BorrowingAddRequest();

        request.setAppUserId(appUser.getId());
        request.setBookId(bookId);

        Borrowing borrowing = testPort.borrowingAdd(request).getBorrowing();
        log.info("status: " + borrowing.getStatus());

        switch (borrowing.getStatus()){
            case DENIED:
                setMessage("Vous avez déjà emprunté ce livre.");
            break;
            case ONGOING:
                setMessage("L'emprunt a bien été enregistré. Veuillez s'il vous plait le retourner avant le " + borrowing.getDueReturnDate());
            break;
            case NONE:
                setMessage("L'emprunt n'a pas pu être effectué car la liste d'attente est pleine.");
            break;
            case WAITINGLIST:
                setMessage("Le livre est réservé! Nous vous enverrons un email pour que vous puissiez le récupérer!");
            break;
        }

        return SUCCESS;
    }

    public String returnThisBook(){
        BorrowingReturnRequest request = new BorrowingReturnRequest();
        request.setId(id);

        if (testPort.borrowingReturn(request).isConfirmation())
            addActionMessage("Le retour du livre a bien été pris en compte.");
        else
            addActionError("L'enregistrement de retour a échoué");

        return SUCCESS;

    }

    public String extend() {
        BorrowingExtendRequest request = new BorrowingExtendRequest();
        BorrowingGetRequest getRequest = new BorrowingGetRequest();
        GregorianCalendar calendar = new GregorianCalendar();

        //recherche l'emprunt par l'id
        getRequest.setId(id);
        Borrowing borrowing = testPort.borrowingGet(getRequest).getBorrowing();

        //Envoie les variables à la requête pour recevoir le code réponse
        request.setBorrowingId(id);
        //request.setNewDueReturnDate(toXmlGregorianCalendar(setDRD(borrowing.getDueReturnDate().toGregorianCalendar())));

        int codeResp = testPort.borrowingExtend(request).getCodeResp();

        switch (codeResp){
            case 1:
                borrowing = testPort.borrowingGet(getRequest).getBorrowing();
                message = "L'emprunt a été prolongé avec succès au " + borrowing.getDueReturnDate();
                break;
            case 2:
                addActionError("Le prolongement de l'emprunt n'a pas pu être effectué. Une seule prolongation est autorisée");
                break;
            case 3:
                addActionError("Le prolongement de l'emprunt n'a pas pu être effectué. La date de retour prévue est dépassée.");
                break;
        }
        return SUCCESS;
    }

    public String pickup(){
        PickupRequest request = new PickupRequest();
        request.setId(id);
        setBorrowing(testPort.pickup(request).getBorrowing());
        return SUCCESS;
    }



    public int getId() {
        return id;
    }

    public void setId(int borrowingId) {
        this.id = borrowingId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Borrowing borrowing) {
        this.borrowing = borrowing;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<StateOfBorrowing> getBookings() {
        return bookings;
    }

    public void setBookings(List<StateOfBorrowing> bookings) {
        this.bookings = bookings;
    }

    public XMLGregorianCalendar toXmlGregorianCalendar(GregorianCalendar cal){
        try {
            DatatypeFactory dataTypeFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar calendar = dataTypeFactory.newXMLGregorianCalendar(cal);
            return calendar;
        } catch (Exception e) {
            log.error("Exception dans la conversion XMLGregorianCalendar \n" + e);
            return null;
        }
    }
    public String changeDateFormat(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String newFormat = sdf.format(date);
        return newFormat;
    }

    public Date xmlGregToDate(XMLGregorianCalendar calendar){
        GregorianCalendar cal = calendar.toGregorianCalendar();
        Date date = cal.getTime();
        return date;
    }

     public GregorianCalendar setDRD(GregorianCalendar calendar){
           calendar.add(Calendar.DAY_OF_MONTH,7 * (Integer.parseInt(props.getProperty("extension-duration"))));
           return calendar;
     }

}
