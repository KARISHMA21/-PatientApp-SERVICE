package com.patient_service.controller;

import com.patient_service.bean.model.ListFinalRecord;
import com.patient_service.bean.model.MinorAccounts;
import com.patient_service.bean.model.UpdatedProfile;
import com.patient_service.bean.response.PatientDemographicsResponse;
import com.patient_service.security.contoller.AuthenticationController;
import com.patient_service.service.serviceinteface.GetMedicalRecordsService;

import com.patient_service.service.serviceinteface.GetMinorsService;
import com.patient_service.service.serviceinteface.PatientAccountService;
import com.patient_service.service.serviceinteface.PatientProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AdminController {
    Logger logger= LoggerFactory.getLogger(AuthenticationController.class);
    private static final Logger CUSTOM_LOGGER = LoggerFactory.getLogger("CustomLogger");
    GetMedicalRecordsService getMedicalRecordsService;
    GetMinorsService getMinorsService;

    PatientAccountService patientaccountService;
    PatientProfileService PatientProfileSvc;
    public AdminController( GetMedicalRecordsService getMedicalRecordsService,GetMinorsService getMinorsService,
                            PatientAccountService patientaccountService,PatientProfileService PatientProfileSvc) {

        this.getMedicalRecordsService = getMedicalRecordsService;
        this.getMinorsService = getMinorsService;
        this.PatientProfileSvc=PatientProfileSvc;
        this.patientaccountService=patientaccountService;
    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping ("/get-medical-records/{pid}")
    public ResponseEntity<ListFinalRecord> getEntityById(@PathVariable String pid ) {

        ResponseEntity<ListFinalRecord> medicalRecordResponse=getMedicalRecordsService.getMedicalRecords(pid);

        CUSTOM_LOGGER.info("[GetMedicalRecords] "+"["+pid+"]"+" "+"CMSService"+" "+"\"\"");
        CUSTOM_LOGGER.info("[GetMedicalRecords] "+"["+pid+"]"+" "+"AdminService"+" "+"\"\"");
        CUSTOM_LOGGER.info("[GetMedicalRecords] "+"["+pid+"]"+" "+"HISService"+" "+"\"\"");

        return ResponseEntity.ok().body(medicalRecordResponse.getBody());
    }

    @GetMapping ("/get-minor-accounts/{pid}")
    public ResponseEntity<List<MinorAccounts>> getMinors(@PathVariable String pid ) {

        CUSTOM_LOGGER.info("[SwitchAccount] "+"["+pid+"]"+" "+"AdminService"+" "+"\"\"");
        CUSTOM_LOGGER.info("[GetMedicalRecords] "+"["+pid+"]"+" "+"PatientService"+" "+"\"\"");
        ResponseEntity<List<MinorAccounts>> minoraccounts=getMinorsService.getMinorAccounts(pid);

        return ResponseEntity.ok().body(minoraccounts.getBody());
    }

    @GetMapping ("/get-profile-details/{pid}")
    public ResponseEntity<PatientDemographicsResponse> getPatientProfile(@PathVariable String pid ) {

        System.out.println("========================== PID : "+pid+"==========================");
        PatientDemographicsResponse PatientDemoDetail = PatientProfileSvc.getPatientDemographics(pid);
        CUSTOM_LOGGER.info("[ProfileDetails] "+"["+pid+"]"+" "+"AdminService"+" "+"\"\"");
        CUSTOM_LOGGER.info("[ProfileDetails] "+"["+pid+"]"+" "+"PatientService"+" "+"\"\"");
        System.out.println("Patient profile details"+PatientDemoDetail.toString());
        return ResponseEntity.status(HttpStatus.OK).body(PatientDemoDetail);
    }

    @PostMapping("/update-profile-details")
    ResponseEntity UpdatePatientProfile(@RequestBody UpdatedProfile patientprofile){
        System.out.println("Received data -"+patientprofile.toString());

        patientaccountService.updateprofile(patientprofile);
        UpdatedProfile response=new UpdatedProfile();
        response =PatientProfileSvc.updateDemographicsinAdminDB(patientprofile);
        System.out.println("Sending Response "+response.getPid());
        CUSTOM_LOGGER.info("[ProfileDetails] "+"["+patientprofile.getPid()+"]"+" "+"AdminService"+" "+"\"\"");
        CUSTOM_LOGGER.info("[ProfileDetails] "+"["+patientprofile.getPid()+"]"+" "+"PatientService"+" "+"\"\"");
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();

//        return 5 ;
    }



}

