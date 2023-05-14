package com.patient_service;
import com.patient_service.bean.entity.PatientEntity;
import com.patient_service.bean.model.PendingRequest;
import com.patient_service.security.entity.Token;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.security.repository.TokenRepository;
import com.patient_service.security.service.AuthenticationService;
import com.patient_service.service.serviceinteface.*;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest
class PatientServiceApplicationTests {
//    @LocalServerPort
//    private int port=8081;
//    @Autowired
//    private TestRestTemplate restTemplate;

//    @Test
//    public void testConsentStats() throws Exception {
//
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                new URL("http://localhost:" + port + "/").toString(), String.class);
//        assertEquals("Hello Controller", response.getBody());
//
//    }


    @Autowired
    GetMinorsService getMinorsService;
    @Autowired
    GetMedicalRecordsService getMedicalRecordsService;
    @Autowired
    ConsentService consentService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PatientProfileService patientProfileService;
    @Autowired
    TokenRepository tokenRepository;
    AuthenticationRequest authenticationRequest=new AuthenticationRequest();
    @BeforeAll
    static void  testInit(){
        System.out.println("Starting test cases.");
    }




//1. GET MINOR FOR PAT2122
    @Test
    void testGetMinor() {
        assertEquals("PAT4351", getMinorsService.getMinorAccounts("PAT2122").getBody().get(0).getPid());
    }

//2. GET MEDICAL RECORDS FOR PAT2122
    @Test
    void testGetMedicalRecords() {
        assertEquals("200 OK", getMedicalRecordsService.getMedicalRecords("PAT2122").getStatusCode().toString());
    }


//3.MATCH LOGIN DETAILS FOR PAT4351-POSITIVE
    @Test
    void testPositiveLoginDetails() {



        authenticationRequest.setUsername("PAT4351");
        authenticationRequest.setPassword("12345");
        assertEquals("PAT4351", authenticationService.authenticate(authenticationRequest).getPid());
        assertEquals("Tom Jonas", authenticationService.authenticate(authenticationRequest).getName());
    }


//4. MATCH LOGIN DETAILS FOR PAT4351 -NEGATIVE

    @Test
    void testNegativeLoginDetails() {


        authenticationRequest.setUsername("PAT2122");
        authenticationRequest.setPassword("u9L4ngNpup");
        assertNotEquals("PAT4351", authenticationService.authenticate(authenticationRequest).getPid());
        assertNotEquals("Tom Jonas", authenticationService.authenticate(authenticationRequest).getName());

//        assertNotEquals("PAT2122", authenticationService.authenticate(authenticationRequest).getPid());
//        assertNotEquals("Mickey Jonas", authenticationService.authenticate(authenticationRequest).getPid());

    }
//5. PENDING REQUEST COUNT FOR PAT4351 :2

    @Test
    void testPendingRequestDetails() {
        assertEquals(2, consentService.getPendingRequests("PAT4351").getBody().size());
    }

//6. MATCH DEMOGRAPHICS DETAILS FOR PAT2122

    @Test
    void testDemographicsDetails() {
        assertEquals("Mickey Jonas", patientProfileService.getPatientDemographics("PAT2122").getName());
        assertNotEquals("Tom Jonas", patientProfileService.getPatientDemographics("PAT2122").getName());
        assertEquals("Noida", patientProfileService.getPatientDemographics("PAT2122").getAddress());
        assertEquals("testaccmt2022@gmail.com", patientProfileService.getPatientDemographics("PAT2122").getEmail());
        assertEquals(29, patientProfileService.getPatientDemographics("PAT2122").getAge());
        assertEquals("Yes", patientProfileService.getPatientDemographics("PAT2122").getHaswebappaccess());
        assertEquals("9873109905", patientProfileService.getPatientDemographics("PAT2122").getPhone().toString());

    }
    @AfterAll
    static void testComplete(){
        System.out.println("All test completed.");
    }



}
