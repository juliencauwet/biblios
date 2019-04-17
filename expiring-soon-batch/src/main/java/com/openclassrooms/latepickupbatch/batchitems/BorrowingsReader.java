package com.openclassrooms.latepickupbatch.batchitems;

import com.openclassrooms.biblioback.ws.test.*;
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
        borrowings = testPort.borrowingsExpiringSoon(new BorrowingsExpiringSoonRequest()).getExpiringSoonBorrowings();
        System.out.println("Borrowing Reader initialized.");
    }

    @Override
    public Borrowing read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        System.out.println("size before : " + borrowings.size());
        while (borrowings.size() > 0) {
            borrowing = borrowings.remove(0);

            return borrowing;
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
