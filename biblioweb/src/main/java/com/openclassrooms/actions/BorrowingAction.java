package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.config.PropSource;
import com.opensymphony.xwork2.ActionSupport;
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

    //WebService
    TestPortService service = new TestPortService();
    TestPort testPort = service.getTestPortSoap11();
    //Properties loading
    PropSource propSource = new PropSource();
    Properties props = propSource.getProps();

    private Date startDate = new Date();

    private String message = "";
    private List<Borrowing> borrowings = null;
    private Borrowing borrowing;

    HttpSession session = ServletActionContext.getRequest().getSession(false);
    private int id;
    private AppUser appUser = (AppUser)session.getAttribute("appUser");
    private Book book;
    private int bookId;

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
        BorrowingGetCurrentRequest request = new BorrowingGetCurrentRequest();
        AppUser appUser = (AppUser)session.getAttribute("appUser");

        request.setUserId(appUser.getId());
        setBorrowings(testPort.borrowingGetCurrent(request).getBorrowingGetCurrent());
        return SUCCESS;
    }

    public String borrowThisBook() {

        if(session == null || session.getAttribute("appUser") == null) {
            addActionError("Veuillez vous identifier pour pouvoir effectuer un emprunt.");
            return LOGIN;
        }

        BorrowingAddRequest request = new BorrowingAddRequest();
        XMLGregorianCalendar xmlCalendar;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        xmlCalendar = toXmlGregorianCalendar(calendar);

        request.setStartDate(xmlCalendar);
        request.setAppUserId(appUser.getId());
        request.setDueReturnDate(toXmlGregorianCalendar(setDRD(calendar)));

        request.setBookId(bookId);

        if (testPort.borrowingAdd(request).isConfirmation())
            setMessage("L'emprunt a bien été enregistré. Veuillez s'il vous plait le retourner avant le " + calendar.getTime());
        else
            setMessage("L'emprunt n'a pas pu être effectué.");

        return SUCCESS;
    }

    public String returnThisBook(){
        BorrowingReturnRequest request = new BorrowingReturnRequest();
        request.setId(id);

        if (testPort.borrowingReturn(request).isConfirmation())
            addActionMessage("Le retour du livre a bien été pris en compte.");
        else
            addActionError("L'emprunt n'a pas pu être effectué.");

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
        request.setNewDueReturnDate(toXmlGregorianCalendar(setDRD(borrowing.getDueReturnDate().toGregorianCalendar())));

        int codeResp = testPort.borrowingExtend(request).getCodeResp();

        if(codeResp == 1 ) {
           borrowing = testPort.borrowingGet(getRequest).getBorrowing();
           message = "L'emprunt a été prolongé avec succès au " + borrowing.getDueReturnDate();
        }else if (codeResp == 2){
           addActionError("Le prolongement de l'emprunt n'a pas pu être effectué. Une seule prolongation est autorisée");
        }
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
