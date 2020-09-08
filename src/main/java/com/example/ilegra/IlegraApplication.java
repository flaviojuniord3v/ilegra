package com.example.ilegra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IlegraApplication {

	private static final Logger log = LoggerFactory.getLogger(IlegraApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IlegraApplication.class, args);
		log.info("I'm running !");

	}

}
