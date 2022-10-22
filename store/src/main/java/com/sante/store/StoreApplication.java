package com.sante.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {
	private static final Logger logger = LoggerFactory.getLogger(StoreApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(StoreApplication.class, args);
		logger.info("Application Started");
	}

}
