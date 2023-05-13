package com.patient_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientServiceApplication {

//	Logger logger= LoggerFactory.getLogger(PatientServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
//		logger.info("test");

	}


}
