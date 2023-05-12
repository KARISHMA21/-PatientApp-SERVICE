package com.patient_service.service.impl;

import com.patient_service.bean.model.UpdatedProfile;
import com.patient_service.bean.response.PatientDemographicsResponse;
import com.patient_service.repository.PatientRepository;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.service.serviceinteface.PatientProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Transactional
@Service
public class PatientProfileServiceImpl implements PatientProfileService {
    @Autowired
    PatientRepository credentialsrepo;

    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;

    private RestTemplate restTemplate_admin;

    public PatientProfileServiceImpl(@Value("${admin_service.base.url}"+"/api/v1")String adminBaseURL, RestTemplateBuilder builder){
        this.restTemplate_admin= builder.rootUri(adminBaseURL).build();
//
    }

    public PatientDemographicsResponse getPatientDemographics(String pid)
    {
        System.out.println("========================== Patient Authentication Request to admin ==========================");

        String admin_auth="/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //get the token for communication
        AuthenticationRequest entitycred=new AuthenticationRequest();

        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);

        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println("---"+request_token.getBody());

        String Token= restTemplate_admin.postForObject(admin_auth,request_token, String.class,"");
        System.out.println("Admin returned Token --> \n"+Token);
        //Getting the medical records
        headers.set("Authorization", "Bearer "+Token);



        HttpEntity<?> request_records = new HttpEntity<>("", headers);
//        patientDemographics= restTemplate_admin.postForEntity(admin,request_records,PatientDemographicsResponse.class ,pid);

        PatientDemographicsResponse patientDemographics =new PatientDemographicsResponse();
        patientDemographics= restTemplate_admin.postForObject("/admin/get-patient-demographics/"+pid,request_records, PatientDemographicsResponse.class ,pid);
        System.out.println("The received Demographic details are --> \n"+patientDemographics.toString());

//        patientDemographics= restTemplate_admin.getForObject("/his/get-patient-demographics/{pid}", PatientDemographicsResponse.class ,pid);
        return patientDemographics;
    }


    public UpdatedProfile updateDemographicsinAdminDB(UpdatedProfile patientprofile)
    {
        System.out.println("========================== Patient Authentication Request to admin ==========================");

        String admin_auth="/auth/authenticate-patient";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //get the token for communication
        AuthenticationRequest entitycred=new AuthenticationRequest();

        entitycred.setUsername(patientClientId);
        entitycred.setPassword(patientClientSecret);

        HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
        System.out.println("---"+request_token.getBody());

        String Token= restTemplate_admin.postForObject(admin_auth,request_token, String.class,"");
        System.out.println("Admin returned Token --> \n"+Token);
        //Getting the medical records
        headers.set("Authorization", "Bearer "+Token);



        HttpEntity<?> request_records = new HttpEntity<>(patientprofile, headers);
//        patientDemographics= restTemplate_admin.postForEntity(admin,request_records,PatientDemographicsResponse.class ,pid);

        UpdatedProfile updateprof =new UpdatedProfile();
        updateprof= restTemplate_admin.postForObject("/admin/update-patient-profile",request_records, UpdatedProfile.class);
//        System.out.println("The received Demographic details are --> \n"+updateprof.toString());

//        patientDemographics= restTemplate_admin.getForObject("/his/get-patient-demographics/{pid}", PatientDemographicsResponse.class ,pid);
        return updateprof;
    }


}
