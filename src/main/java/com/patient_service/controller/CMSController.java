package com.patient_service.controller;

import com.patient_service.PatientServiceApplication;
import com.patient_service.bean.model.ConsentActionRequestBody;
import com.patient_service.bean.model.ConsentLogModel;
import com.patient_service.bean.model.PendingRequest;
import com.patient_service.bean.model.VoluntaryConsent;
import com.patient_service.bean.response.MedicalRecordResponse;
import com.patient_service.service.serviceinteface.ConsentService;
import com.patient_service.bean.model.*;
import com.patient_service.bean.response.ConsentLogResponse;
import com.patient_service.bean.response.ConsentStats;
import com.patient_service.bean.response.ListActiveConsents;
import com.patient_service.service.serviceinteface.GetActiveConsentService;
import com.patient_service.service.serviceinteface.GetConsnetLogsService;
import com.patient_service.service.serviceinteface.VoluntaryConsentSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;


@RestController
@RequestMapping("/api/v1/cms")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CMSController {
    Logger logger= LoggerFactory.getLogger(PatientServiceApplication.class);
    private final VoluntaryConsentSevice voluntaryConsentService;
    private final ConsentService consentService;
    private final GetActiveConsentService getActiveConsentService;
    private final GetConsnetLogsService getConsnetLogsService;

    public CMSController(VoluntaryConsentSevice voluntaryConsentService, ConsentService consentService, GetActiveConsentService getActiveConsentService,GetConsnetLogsService getConsnetLogsService) {
        this.voluntaryConsentService = voluntaryConsentService;
        this.consentService = consentService;
        this.getActiveConsentService = getActiveConsentService;
        this.getConsnetLogsService=getConsnetLogsService;
    }

    @PostMapping("/send-voluntary-consent")
    public ResponseEntity sendVoluntaryConsent(@RequestBody List<VoluntaryConsent> voluntaryConsentList) {
        try {

            int status=voluntaryConsentService.sendVoluntaryConsent(voluntaryConsentList);
            if(status==0)
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
    }
    @GetMapping("/get-active-consent/{pid}")
    public ResponseEntity<ListActiveConsents> get_active_consent(@PathVariable String pid) {
        try {
            ResponseEntity<ListActiveConsents> consentsResponseEntity;
            consentsResponseEntity=getActiveConsentService.getActiveConsents(pid);

            return ResponseEntity.ok(consentsResponseEntity.getBody());
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
    }

    @GetMapping("/get-pending-request-list/{pid}")
    public ResponseEntity<List<PendingRequest>> getPendingRequests(@PathVariable String pid){
        return consentService.getPendingRequests(pid);
    }

    @GetMapping("/get-pending-request-details/{pid}/{pendingrequestid}")
    public ResponseEntity<List<MedicalRecordResponse>> getPendingRequestDetail(@PathVariable String pid, @PathVariable String pendingrequestid){
        List<MedicalRecordResponse> medicalRecordResponseList = consentService.getPendingRequestDetails(pid, pendingrequestid);
        if(isNull(medicalRecordResponseList)){
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body(medicalRecordResponseList);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(medicalRecordResponseList);
        }
    }

    @PostMapping("/action-on-pending-request/{pendingrequestid}")
    public ResponseEntity<String> actionOnPendingRequest(@PathVariable String pendingrequestid, @RequestBody ConsentActionRequestBody consentActionRequestBody){
        Integer status = consentService.actionOnPendingRequest(pendingrequestid, consentActionRequestBody);
        if(status==1){
            return new ResponseEntity<>("The action on pending request was successful",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("The action on pending request was unsuccessful",HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/view-active-consents/{pid}")
    public ResponseEntity<List<ConsentLogModel>> viewActiveConsents(@PathVariable String pid){
        List<ConsentLogModel> consentLogModelList = consentService.viewActiveConsents(pid);
        if(isNull(consentLogModelList)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(consentLogModelList);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(consentLogModelList);
        }
    }

    @GetMapping("/get-consent-logs/{pid}")
    public ResponseEntity<ConsentLogResponse> getConsentLogs(@PathVariable String pid) {
        try {
            ResponseEntity<ConsentLogResponse> consentsResponseEntity;
            consentsResponseEntity=getConsnetLogsService.getConsentLogs(pid);

            return ResponseEntity.ok(consentsResponseEntity.getBody());
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
    }

    @GetMapping("/get-active-consent-records/{pid}/{cid}")
    public ResponseEntity<ListFinalRecord> get_active_consent_records(@PathVariable String pid,@PathVariable String cid) {
        try {
            ResponseEntity<ListFinalRecord> consentsResponseEntity;
            consentsResponseEntity=getActiveConsentService.getActiveConsentsRecords(pid,cid);

            return ResponseEntity.ok(consentsResponseEntity.getBody());
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
    }


    @PutMapping("/update-active-consent-records/")
    public ResponseEntity updateActiveConsentRecords(@RequestBody List<ActiveConsentRecords> activeConsentRecordsList) {
        try {
            System.out.println(activeConsentRecordsList.get(0).getCid());
            int status=getActiveConsentService.updateActiveConsentRecords(activeConsentRecordsList);
            if(status==0)
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
    }
    @PutMapping("/revoke-active-consent/")
    public ResponseEntity revokeConsent(@RequestBody ActiveConsents activeConsent) {
        try {
//            System.out.println(activeConsentRecordsList.get(0).getCid());
            int status=getActiveConsentService.revokeConsent(activeConsent);
            if(status==0)
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
    }


    @GetMapping("/get-stats/{pid}")
    public ResponseEntity<ConsentStats> get_consent_stats(@PathVariable String pid) {
        try {
            ResponseEntity<ConsentStats> consentsResponseEntity;
            consentsResponseEntity=getActiveConsentService.getConsentStats(pid);

            return ResponseEntity.ok(consentsResponseEntity.getBody());
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
    }

}

