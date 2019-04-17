package com.openclassrooms.services;

import com.openclassrooms.beans.MailSender;
import com.openclassrooms.entities.*;
import com.openclassrooms.repositories.BorrowingRepository;
import com.openclassrooms.repositories.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingService implements IBorrowingService {

    @Autowired
    BorrowingRepository borrowingRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    @Qualifier("MailSender")
    private MailSender sender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void newBorrowing(Borrowing borrowing) {
            borrowingRepository.save(borrowing);
    }

    @Override
    public void updateBorrowing(Borrowing borrowing) {
        borrowingRepository.save(borrowing);
    }

    @Override
    public List<Borrowing> borrowingReport() {
        List<Borrowing> borrowings = new ArrayList<>();
        borrowingRepository.findAll().forEach(borrowings::add);
        return borrowings;
    }

    @Override
    public Borrowing getById(int id) {
        return borrowingRepository.findById(id);
    }

    @Override
    public List<Borrowing> getByAppUserId(int id) {
        return borrowingRepository.findAllByAppUserId(id);
    }

    @Override
    public List<Borrowing> getExpiredBorrowing() {
        List<Borrowing> borrowings = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date date = c.getTime();
        System.out.println("Today: " + date);
        borrowingRepository.findAllByDueReturnDateBeforeAndReturnDateIsNull(date).forEach(borrowings::add);
        return borrowings;
    }

    @Override
    public List<Borrowing> getAllBorrowings() {
        List<Borrowing> borrowings = new ArrayList<>();
        borrowingRepository.findAll().forEach(borrowings::add);
        return borrowings;
    }

    @Override
    public List<Borrowing> filterBorrowingByStatus(Status status) {
        List<Borrowing> borrowings = new ArrayList<>();
        borrowingRepository.findBorrowingsByStatus(status).forEach(borrowings::add);
        return borrowings;
    }

    @Override
    public void deleteBorrowingById(int id){
        borrowingRepository.deleteBorrowingById(id);
    }

    @Override
    public void deleteBorrowingListById(List<Integer> borrowingIds) {
        for (int i: borrowingIds)
            borrowingRepository.deleteBorrowingById(i);
    }

    @Override
    public List<Borrowing> getBorrowingsByBook(BookEntity book) {
        return borrowingRepository.findBorrowingsByBook(book);
    }

    @Override
    public List<Borrowing> getBorrowingsByBookAndStatus(BookEntity book, Status status) {
        return borrowingRepository.findBorrowingsByBookAndStatus(book, status);
    }

    @Override
    public Boolean alreadyBorrowed(AppUser user, BookEntity book, Status status) {
        logger.info("alreadyBorrowed Method:" + (borrowingRepository.findBorrowingsByBookAndAppUserAndStatusAndReturnDateIsNull(book,user, status)).size());
        return borrowingRepository.findBorrowingsByBookAndAppUserAndStatusAndReturnDateIsNull(book,user, status).size() > 0;
    }

    @Override
    public Date nextReturnDate(BookEntity bookEntity) {
        Date date = new Date();
        List<Borrowing> borrowings = borrowingRepository.findBorrowingsByBook(bookEntity);
        for (Borrowing b : borrowings){
            if (b.getDueReturnDate() != null && date.before(b.getDueReturnDate()))
                date = b.getDueReturnDate();
        }

        return date;
    }

    @Override
    public void sendEmailToNextBorrower(Borrowing borrowing) {
        Email email = new Email();

        String body = "";
        body += "Bonjour M./Mme " + borrowing.getAppUser().getName() + ",\n";
        body += "Veuillez s'il vous plait venir le chercher dans les prochaines 48h.\n";
        body += "Merci et à bientôt! ";
        body += "L'équipe de Biblioweb." ;

        email.setBorrowingId(borrowing.getId());
        email.setSentDate(LocalDate.now());
        email.setSubject("pickup");
        emailRepository.save(email);

        sender.sendMail("julien.app.test@gmail.com", borrowing.getAppUser().getEmail(), "pickup" + borrowing.getBook().getTitle()  + " est disponible!", body);
    }

    @Override
    public void sendEmail(String text) {
        sender.sendMail("julien.app.test@gmail.com", "juliencauwet@yahoo.fr", "JavaMailSender", "Just testing");
    }

    @Override
    public void cancelIfLatePickup() {

        List<Email> emails = emailRepository.findBySubject("pickup");
        for (Email email : emails){
            if(email.getSubject().equals("pickup") && email.getSentDate().isBefore(LocalDate.now().minusDays(2))){
                Borrowing borrowing = borrowingRepository.findById(email.getBorrowingId());
                List<com.openclassrooms.entities.Borrowing> borrowingsOnWaitingList = getBorrowingsByBookAndStatus(borrowing.getBook(), Status.AVAILABLE);
                for (Borrowing b :
                        borrowingsOnWaitingList) {
                    forwardWaitingListOrder(b);
                }
                borrowing.setStatus(Status.DENIED);
                borrowingRepository.save(borrowing);
            }
        }
    }

    @Override
    public List<Borrowing> expiringSoonBorrowings() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date finalDay = calendar.getTime();
        logger.info("processing expiringSoonBorrowings");
        //List<Article> filteredArticleList= articleList.stream().filter(article -> article.getDesArt().contains("test")).collect(Collectors.toList());
        List<Borrowing> borrowings = borrowingRepository.findBorrowingsByDueReturnDateAndStatus(finalDay, Status.ONGOING);
        borrowings = borrowings.stream().filter(borrowing -> borrowing.getAppUser().getAlert()).collect(Collectors.toList());
        return borrowings;
    }

    public void forwardWaitingListOrder(com.openclassrooms.entities.Borrowing b){
        b.setWaitingListOrder(b.getWaitingListOrder() - 1);
        //if position is 0, email to be sent to the borrower
        if (b.getWaitingListOrder() == 0) {
            b.setStatus(Status.AVAILABLE);
            sendEmailToNextBorrower(b);
        }
        updateBorrowing(b);
    }




}
