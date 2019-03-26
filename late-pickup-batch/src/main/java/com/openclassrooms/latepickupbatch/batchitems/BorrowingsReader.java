package com.openclassrooms.latepickupbatch.batchitems;

import com.openclassrooms.biblioback.ws.test.Borrowing;
import com.openclassrooms.biblioback.ws.test.BorrowingGetAllRequest;
import com.openclassrooms.biblioback.ws.test.TestPort;
import com.openclassrooms.biblioback.ws.test.TestPortService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

import java.util.List;


public class BorrowingsReader implements ItemReader<Borrowing> , StepExecutionListener {

    private TestPortService service = new TestPortService();
    private TestPort testPort = service.getTestPortSoap11();
    List<Borrowing> borrowings ;
    Borrowing borrowing;


    @Override
    public void beforeStep(StepExecution stepExecution) {
        //List<Borrowing> borrowings = testPort.borrowingsExpiringSoon(new BorrowingsExpiringSoonRequest()).getExpiringSoonBorrowings();
        borrowings = testPort.borrowingGetAll(new BorrowingGetAllRequest()).getBorrowingGetAll();
        System.out.println("Borrowing Reader initialized.");
    }

    @Override
    public Borrowing read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        System.out.println("size before : " + borrowings.size());
        while (borrowings.size() > 0) {
            borrowing = borrowings.remove(0);

            System.out.println("size after: " + borrowings.size());
            System.out.println("borroeing n° " + borrowing.getId());

            //Borrowing borrowing = new Borrowing();
            //System.out.println("In read");
            ////List<Borrowing> borrowings =testPort.borrowingGetAll(new BorrowingGetAllRequest()).getBorrowingGetAll();
            //for(Borrowing borrowing : borrowings) {
            //    System.out.println("titre: " + borrowing.getBook().getTitle());
            //    System.out.println("auteur: " + borrowing.getBook().getAuthorFirstName());
            //}
            //ListItemReader<Borrowing> reader = new ListItemReader<Borrowing>(borrowings);


            return borrowing;
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("afterStep: " + borrowing);
        return null;
    }
}
