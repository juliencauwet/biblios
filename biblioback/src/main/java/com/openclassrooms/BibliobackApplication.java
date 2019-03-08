package com.openclassrooms;

import com.openclassrooms.beans.MailSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BibliobackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliobackApplication.class, args);
	}

}
