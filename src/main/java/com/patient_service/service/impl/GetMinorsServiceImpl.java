package com.patient_service.service.impl;

import com.patient_service.bean.model.ListFinalRecord;
import com.patient_service.bean.model.MinorAccounts;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.service.serviceinteface.GetMinorsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class GetMinorsServiceImpl implements GetMinorsService {
    private RestTemplate restTemplate_admin;
    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;
    public GetMinorsServiceImpl(@Value("${admin_service.base.url}"+"/api/v1")String adminBaseURL, RestTemplateBuilder builder){
        this.restTemplate_admin= builder.rootUri(adminBaseURL).build();
//        this.restTemplate_his=builder.build();
    }
    @Override
    public ResponseEntity<List<MinorAccounts>> getMinorAccounts(String pid){
        ResponseEntity<List<MinorAccounts> >response=null;

        String admin_auth="/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //get the token for communication
        AuthenticationRequest entitycred=new AuthenticationRequest();

        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);

//
        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        //  System.out.println(request_token.getBody());

        String Token= restTemplate_admin.postForObject(admin_auth,request_token, String.class,"");
        System.out.println(Token);
        //Getting the medical records

        headers.set("Authorization", "Bearer "+Token);

        String  url="/admin/get-minor-accounts/{pid}";
        HttpEntity<?> request_records = new HttpEntity<>("entity", headers);
        response= restTemplate_admin.exchange(url, HttpMethod.GET, request_records, new ParameterizedTypeReference<List<MinorAccounts>>() {
        }, pid);



        return response;
    }

}
