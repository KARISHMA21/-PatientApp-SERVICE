package com.patient_service.service.impl;

import com.patient_service.bean.model.ConsentActionRequestBody;
import com.patient_service.bean.model.ConsentLogModel;
import com.patient_service.bean.model.PendingRequest;
import com.patient_service.bean.response.MedicalRecordResponse;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.service.serviceinteface.ConsentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConsentServiceImpl implements ConsentService {
    private RestTemplate restTemplate_cms;
    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;

    @Value("${cms_service.base.url}")
    private String cmsBaseURL;
    @Value("${admin_service.base.url}")
    private String adminBaseURL;

    public ConsentServiceImpl(@Value("${cms_service.base.url}"+"/api/v1")String cmsBaseURL, RestTemplateBuilder builder) {
        this.restTemplate_cms= builder.rootUri(cmsBaseURL).build();
    }

    @Override
    public ResponseEntity<List<PendingRequest>> getPendingRequests(String pid) {
        //authentication begins
        String cms="/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        AuthenticationRequest entitycred=new AuthenticationRequest();
        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);
        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println(request_token.getBody());
        String Token= restTemplate_cms.postForObject(cms,request_token, String.class,"");
        headers.set("Authorization", "Bearer "+Token);
        //authentication ends
        HttpEntity<?> request = new HttpEntity<>("", headers);
        String url="/cms/get-pending-request-list/"+pid;
        ResponseEntity<List<PendingRequest>> response;
        response= restTemplate_cms.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<PendingRequest>>(){},"");
        List<PendingRequest> pendingRequestList;
        if(response.getStatusCode()==HttpStatus.OK){
            pendingRequestList = response.getBody();
            return ResponseEntity.status(HttpStatus.OK).body(pendingRequestList);
        }
        else{
            return null;
        }

    }

    @Override
    public List<MedicalRecordResponse> getPendingRequestDetails(String pid, String pendingrequestid) {
        //authentication begins
        String cms = "/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        AuthenticationRequest entitycred=new AuthenticationRequest();
        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);
        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println(request_token.getBody());
        String Token= restTemplate_cms.postForObject(cms,request_token, String.class,"");
        headers.set("Authorization", "Bearer "+Token);
        //authentication ends
        HttpEntity<?> request = new HttpEntity<>("", headers);
        String url="/cms/get-pending-request-details/"+pid+"/"+pendingrequestid;
        ResponseEntity<List<MedicalRecordResponse>> response;
        response= restTemplate_cms.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<MedicalRecordResponse>>(){},"");
        List<MedicalRecordResponse> medicalRecordResponseList;
        if(response.getStatusCode().isSameCodeAs(HttpStatus.OK)){
            medicalRecordResponseList = response.getBody();
            return medicalRecordResponseList;
        }
        else{
            return null;
        }
    }

    @Override
    public Integer actionOnPendingRequest(String pendingrequestid, ConsentActionRequestBody consentActionRequestBody) {
        //authentication begins
        String cms="/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        AuthenticationRequest entitycred=new AuthenticationRequest();
        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);
        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println(request_token.getBody());
        String Token= restTemplate_cms.postForObject(cms,request_token, String.class,"");
        headers.set("Authorization", "Bearer "+Token);
        //authentication ends
        HttpEntity<?> request = new HttpEntity<>(consentActionRequestBody, headers);
        String url="/cms/action-on-pending-request/"+pendingrequestid;
        ResponseEntity response= restTemplate_cms.postForEntity(url,request, ResponseEntity.class,"");
        if(response.getStatusCode().isSameCodeAs(HttpStatus.OK)){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public List<ConsentLogModel> viewActiveConsents(String pid) {
        //authentication begins
        String cms=cmsBaseURL + "/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        AuthenticationRequest entitycred=new AuthenticationRequest();
        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);
        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println(request_token.getBody());
        String Token= restTemplate_cms.postForObject(cms,request_token, String.class,"");
        headers.set("Authorization", "Bearer "+Token);
        //authentication ends
        HttpEntity<?> request = new HttpEntity<>("", headers);
        String url=cmsBaseURL + "/cms/view-Active-consents/"+pid;
        ResponseEntity<List<ConsentLogModel>> response;
        response= restTemplate_cms.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<ConsentLogModel>>(){},"");
        if(response.getStatusCode().isSameCodeAs(HttpStatus.OK)){
            return response.getBody();
        }
        else{
            return null;
        }
    }
}
