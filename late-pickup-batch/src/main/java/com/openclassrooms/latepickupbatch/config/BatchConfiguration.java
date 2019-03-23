package com.openclassrooms.latepickupbatch.config;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.latepickupbatch.tuto.ProcessorImpl;
import com.openclassrooms.latepickupbatch.tuto.ReaderImpl;
import com.openclassrooms.latepickupbatch.tuto.Writer;
import com.openclassrooms.latepickupbatch.writer.SimpleMailItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobLauncher jobLauncher;

    private TestPortService service = new TestPortService();
    private TestPort testPort = service.getTestPortSoap11();


    @Scheduled(fixedRate = 5000)
    public void deleteLate() throws Exception {

        System.out.println(" Job Started at :"+ new Date());

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(job(), param);

        System.out.println("Job finished with status :" + execution.getStatus());
    }


    public Job job() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }


    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Borrowing, SimpleMailMessage> chunk(10)
                .reader(itemReader())
                .processor(new ProcessorImpl())
                .writer(new SimpleMailItemWriter())
                .build();
    }




    /*
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        jobLauncher.run(ExpiredBorrowingJob(jobBuilderFactory, stepBuilderFactory, itemReader(), processor2(), new SimpleMailItemWriter()), param);
    }

    @Bean
    public Processor processor2(){
        return new Processor();
    }



    @Bean
    public Job ExpiredBorrowingJob(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory,
                                   ItemReader<Borrowing> itemReader,
                                   ItemProcessor<Borrowing, SimpleMailMessage> processor,
                                   ItemWriter<SimpleMailMessage> itemWriter
    ) {

        Step step = stepBuilderFactory.get("Expired-Borrowing-List")
                .<Borrowing, SimpleMailMessage>chunk(100)
                .reader(itemReader)
                .processor(processor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("send-Borrowing-List")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
*/

    @Bean
    public ListItemReader<Borrowing> itemReader() {
        //List<Borrowing> borrowings = testPort.borrowingsExpiringSoon(new BorrowingsExpiringSoonRequest()).getExpiringSoonBorrowings();
        List<Borrowing> borrowings =testPort.borrowingGetAll(new BorrowingGetAllRequest()).getBorrowingGetAll();
        for(Borrowing borrowing : borrowings) {
            System.out.println("titre: " + borrowing.getBook().getTitle());
            System.out.println("auteur: " + borrowing.getBook().getAuthorFirstName());
        }
        ListItemReader<Borrowing> reader = new ListItemReader<Borrowing>(borrowings);

        return reader;
    }


}
