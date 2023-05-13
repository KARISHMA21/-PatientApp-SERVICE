package com.patient_service.controller;

import com.patient_service.PatientServiceApplication;
import com.patient_service.bean.model.PasswordResetRequest;
import com.patient_service.bean.model.PatientReg;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.security.response.AuthenticationResponse;
import com.patient_service.service.serviceinteface.PatientAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PatientController {
    Logger logger= LoggerFactory.getLogger(PatientServiceApplication.class);
    PatientAccountService accountService;


    public PatientController(PatientAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create-account")
    public ResponseEntity creatAccount(@RequestBody PatientReg patient) {
        System.out.println("Inside create account "+patient.getPid()+" "+patient.getPid());
        try {

            accountService.addCredentials(patient);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

    }



}
