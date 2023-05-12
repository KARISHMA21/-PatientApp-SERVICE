package com.patient_service.service.impl;

import com.patient_service.bean.model.ActiveConsentRecords;
import com.patient_service.bean.model.ActiveConsents;
import com.patient_service.bean.model.ListFinalRecord;
import com.patient_service.bean.model.VoluntaryConsent;
import com.patient_service.bean.response.ConsentStats;
import com.patient_service.bean.response.ListActiveConsents;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.service.serviceinteface.GetActiveConsentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GetActiveConsentsImpl implements GetActiveConsentService {
    private RestTemplate restTemplate_cms;
    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;

    public GetActiveConsentsImpl(@Value("${cms_service.base.url}"+"/api/v1")String cmsBaseURL, RestTemplateBuilder builder){
        this.restTemplate_cms= builder.rootUri(cmsBaseURL).build();

    }
    public String generateToken(HttpHeaders headers){

        String cms="/auth/authenticate-patient";
        //get the token for communication
        AuthenticationRequest entitycred=new AuthenticationRequest();

        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);


        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println(request_token.getBody());

        String Token= restTemplate_cms.postForObject(cms,request_token, String.class,"");
        return  Token;
    }
    @Override
    public ResponseEntity<ListActiveConsents> getActiveConsents(String pid){

        ResponseEntity<ListActiveConsents> response;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String Token=generateToken(headers);
        headers.set("Authorization", "Bearer "+Token);
        HttpEntity<?> request_records = new HttpEntity<>("", headers);
        String url="/cms/get-active-consent/{pid}";
        response = restTemplate_cms.exchange(url, HttpMethod.GET, request_records, new ParameterizedTypeReference<ListActiveConsents>() {
        }, pid);


//        System.out.println(response.toString());
        return response;
    }
    @Override
    public ResponseEntity<ListFinalRecord> getActiveConsentsRecords(String pid,String cid){

        ResponseEntity<ListFinalRecord> response;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String Token=generateToken(headers);
        headers.set("Authorization", "Bearer "+Token);
        HttpEntity<?> request_records = new HttpEntity<>("", headers);
        String url="/his/get-active-consent-records/{pid}/{cid}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("pid", pid);
        uriVariables.put("cid", cid);

        response = restTemplate_cms.exchange(url, HttpMethod.GET, request_records, new ParameterizedTypeReference<ListFinalRecord>() {
        }, uriVariables);


        System.out.println(response.toString());
        return response;
    }

    @Override
    public int updateActiveConsentRecords(List<ActiveConsentRecords> activeConsentRecordsList){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String Token=generateToken(headers);
        headers.set("Authorization", "Bearer "+Token);


//        System.out.println(entity.get(0).toString());
        HttpEntity<?> request = new HttpEntity<>(activeConsentRecordsList, headers);
        String url="/cms/update-active-consent-records";
//        ResponseEntity response= restTemplate_cms.put(url,request, ResponseEntity.class,"");
        ResponseEntity response = restTemplate_cms.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<ResponseEntity>() {
        }, "");

        if(response.getStatusCode().value()==200){
            return 0;
        }


        return 1 ;
    }

    @Override
    public int revokeConsent(ActiveConsents activeConsent){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String Token=generateToken(headers);
        headers.set("Authorization", "Bearer "+Token);


//        System.out.println(entity.get(0).toString());
        HttpEntity<?> request = new HttpEntity<>(activeConsent, headers);
        String url="/cms/revoke-active-consent/";
//        ResponseEntity response= restTemplate_cms.put(url,request, ResponseEntity.class,"");
        ResponseEntity response = restTemplate_cms.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<ResponseEntity>() {
        }, "");

        if(response.getStatusCode().value()==200){
            return 0;
        }


        return 1 ;
    }

    public ResponseEntity<ConsentStats> getConsentStats(String pid){

        ResponseEntity<ConsentStats> response;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String Token=generateToken(headers);
        headers.set("Authorization", "Bearer "+Token);
        HttpEntity<?> request_records = new HttpEntity<>("", headers);
        String url="/cms/get-stats/{pid}";
        response = restTemplate_cms.exchange(url, HttpMethod.GET, request_records, new ParameterizedTypeReference<ConsentStats>() {
        }, pid);


//        System.out.println(response.toString());
        return response;
    }



}

