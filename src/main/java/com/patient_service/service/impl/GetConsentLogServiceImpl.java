package com.patient_service.service.impl;

import com.patient_service.bean.model.ConsentLog;
import com.patient_service.bean.model.VoluntaryConsent;
import com.patient_service.bean.response.ConsentLogResponse;
import com.patient_service.bean.response.ConsentStats;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.service.serviceinteface.GetConsnetLogsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class GetConsentLogServiceImpl implements GetConsnetLogsService {

    private RestTemplate restTemplate_cms;
    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;

    public GetConsentLogServiceImpl(@Value("${cms_service.base.url}"+"/api/v1")String cmsBaseURL, RestTemplateBuilder builder){
        this.restTemplate_cms= builder.rootUri(cmsBaseURL).build();

    }

    @Override
    public ResponseEntity<ConsentLogResponse> getConsentLogs(String pid) {

        ResponseEntity<ConsentLogResponse> response;

        String cms = "/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        //get the token for communication
        AuthenticationRequest entitycred = new AuthenticationRequest();

        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);


        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println(request_token.getBody());

        String Token = restTemplate_cms.postForObject(cms, request_token, String.class, "");
        headers.set("Authorization", "Bearer " + Token);


        HttpEntity<?> request = new HttpEntity<>("", headers);
        String url = "/cms/get-patient-consent-logs/{pid}";
        response = restTemplate_cms.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<ConsentLogResponse>() {
        }, pid);


        return response;

    }
}
