package com.patient_service.service.impl;


import com.patient_service.bean.model.VoluntaryConsent;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.service.serviceinteface.VoluntaryConsentSevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class VoluntaryConsentServiceImpl implements VoluntaryConsentSevice {
    private RestTemplate restTemplate_cms;
    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;

    public VoluntaryConsentServiceImpl(@Value("${cms_service.base.url}"+"/api/v1")String cmsBaseURL, RestTemplateBuilder builder){
        this.restTemplate_cms= builder.rootUri(cmsBaseURL).build();

    }

    @Override
    public int sendVoluntaryConsent(List<VoluntaryConsent> voluntaryConsent){


        ResponseEntity response;

        String cms="/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        //get the token for communication
        AuthenticationRequest entitycred=new AuthenticationRequest();

        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);

//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String,String>();

//        body.add("username", adminClientId);
//        body.add("password", adminClientSecret);
        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println(request_token.getBody());

        String Token= restTemplate_cms.postForObject(cms,request_token, String.class,"");
        headers.set("Authorization", "Bearer "+Token);

        List<VoluntaryConsent> entity =  voluntaryConsent;
//        System.out.println(entity.get(0).toString());
        HttpEntity<?> request = new HttpEntity<>(voluntaryConsent, headers);
       String url="/cms/save-voluntary-consent";
        response= restTemplate_cms.postForEntity(url,request, ResponseEntity.class,"");
        System.out.println(response.toString());
        if(response.getStatusCode().value()==200){
          return 0;
        }


        return 1 ;
    }



}
