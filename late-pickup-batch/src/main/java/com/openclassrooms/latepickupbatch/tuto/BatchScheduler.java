package com.openclassrooms.latepickupbatch.tuto;

import com.openclassrooms.biblioback.ws.test.Borrowing;
import com.openclassrooms.latepickupbatch.batchitems.BorrowingsProcessor;
import com.openclassrooms.latepickupbatch.batchitems.BorrowingsReader;
import com.openclassrooms.latepickupbatch.batchitems.BorrowingsWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchScheduler {


    @Configuration
    @EnableBatchProcessing
    public class ChunksConfig {

        @Autowired
        private JobBuilderFactory jobs;

        @Autowired
        private StepBuilderFactory steps;

        @Autowired
        private JobLauncher jobLauncher;

        @Bean
        public ItemReader<Borrowing> itemReader() {
            return new BorrowingsReader();
        }

        @Bean
        public ItemProcessor<Borrowing, SimpleMailMessage> itemProcessor() {
            return new BorrowingsProcessor();
        }

        @Bean
        public ItemWriter<SimpleMailMessage> itemWriter() {
            return new BorrowingsWriter();
        }


        @Scheduled(fixedRate = 60000)
        public void deleteLate() throws Exception {

            System.out.println(" Job was at :"+ new Date());

            JobParameters param = new JobParametersBuilder().addString("Job Id",
                    String.valueOf(System.currentTimeMillis())).toJobParameters();

            JobExecution execution = jobLauncher.run(job(), param);

            System.out.println("Job finished with status :" + execution.getStatus());
        }

        @Bean
        protected Step processLines(ItemReader<Borrowing> reader,
                                    ItemProcessor<Borrowing, SimpleMailMessage> processor, ItemWriter<SimpleMailMessage> writer) {
            return steps.get("processLines").<Borrowing, SimpleMailMessage> chunk(2)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer)
                    .build();
        }

        @Bean
        public Job job() {
            return jobs
                    .get("chunksJob")
                    .start(processLines(itemReader(), itemProcessor(), itemWriter()))
                    .build();
        }

    }

}



