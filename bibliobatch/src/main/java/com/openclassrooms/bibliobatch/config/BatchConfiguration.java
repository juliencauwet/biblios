package com.openclassrooms.bibliobatch.config;

import com.openclassrooms.biblioback.ws.test.Borrowing;
import com.openclassrooms.biblioback.ws.test.BorrowingGetExpiredRequest;
import com.openclassrooms.biblioback.ws.test.TestPort;
import com.openclassrooms.biblioback.ws.test.TestPortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobLauncher jobLauncher;


    TestPortService service = new TestPortService();
    TestPort testPort = service.getTestPortSoap11();

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Borrowing> itemReader,
                   ItemProcessor<Borrowing, SimpleMailMessage> itemProcessor,
                   ItemWriter<SimpleMailMessage> itemWriter
    ) {

        Step step = stepBuilderFactory.get("Expired-Borrowing-List")
                .<Borrowing, SimpleMailMessage>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("send-Borrowing-List")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public ListItemReader<Borrowing> itemReader() {
        List<Borrowing> borrowings = testPort.borrowingGetExpired(new BorrowingGetExpiredRequest()).getBorrowingGetExpired();
        for(Borrowing borrowing : borrowings) {
            System.out.println(borrowing.getBook().getTitle());
        }
        ListItemReader<Borrowing> reader = new ListItemReader<Borrowing>(borrowings);


        return reader;
    }



}
