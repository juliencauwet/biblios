package com.openclassrooms.bibliobatch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class BibliobatchApplication {

	private static final Logger log = LoggerFactory.getLogger(BibliobatchApplication.class);

	public static void main(String[] args) {
		log.info("Entrée dans l'application");

		SpringApplication.run(BibliobatchApplication.class, args);
	}
}
