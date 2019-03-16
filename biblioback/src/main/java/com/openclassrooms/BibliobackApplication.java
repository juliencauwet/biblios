package com.openclassrooms;

import com.openclassrooms.beans.MailSender;
import com.openclassrooms.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class BibliobackApplication {

	@Autowired
	private BorrowingService borrowingService;

	public static void main(String[] args) {
		SpringApplication.run(BibliobackApplication.class, args);
	}

	@Scheduled(fixedRate = 60000)
	public void test(){
		System.out.println("too late pick up process");
		borrowingService.cancelIfLatePickup();
	}

}
