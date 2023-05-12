package com.patient_service.service.impl;//package com.example.backend.service;
//

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.patient_service.bean.model.FinalRecords;
import com.patient_service.bean.model.ListFinalRecord;
import com.patient_service.bean.response.MedicalRecordResponse;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.security.service.EncryptDecrypt;
import com.patient_service.service.serviceinteface.GetMedicalRecordsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GetMedicalRecordsImpl implements GetMedicalRecordsService {
//  @Autowired
    private RestTemplate restTemplate_admin;
    private final EncryptDecrypt encryptDecrypt;
    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;
    public GetMedicalRecordsImpl(@Value("${admin_service.base.url}"+"/api/v1")String adminBaseURL, RestTemplateBuilder builder,EncryptDecrypt encryptDecrypt){
        this.restTemplate_admin= builder.rootUri(adminBaseURL).build();
        this.encryptDecrypt=encryptDecrypt;
    }

 public ResponseEntity<ListFinalRecord> getMedicalRecords(String pid){
                MedicalRecordResponse medicalRecordResponse =new MedicalRecordResponse();

                ListFinalRecord finalRecordResponseEntity=new ListFinalRecord();
                ResponseEntity<String> res=null;

                 //set data by making a rest api call
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


                HttpEntity<?> request_records = new HttpEntity<>("entity", headers);
//                res= restTemplate_admin.postForEntity(,request_records,  String.class,pid);
                res = restTemplate_admin.exchange("/cms/get-medical-records/"+pid, HttpMethod.GET, request_records, new ParameterizedTypeReference<String>() {
     }, pid);
                 String decryptedData=encryptDecrypt.decryptData(res.getBody()) ;
                 Gson gson = new Gson();
                 Type type = new TypeToken<ListFinalRecord>(){}.getType();
                 finalRecordResponseEntity =  gson.fromJson(decryptedData,type);

                 return ResponseEntity.ok(finalRecordResponseEntity);


 }


}
