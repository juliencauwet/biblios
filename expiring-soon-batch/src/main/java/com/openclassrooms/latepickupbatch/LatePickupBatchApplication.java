package com.openclassrooms.latepickupbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class LatePickupBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LatePickupBatchApplication.class, args);
	}


}
