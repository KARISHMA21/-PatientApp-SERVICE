package com.patient_service.service.serviceinteface;


import com.patient_service.bean.model.UpdatedProfile;
import com.patient_service.bean.response.PatientDemographicsResponse;

public interface PatientProfileService {
   public PatientDemographicsResponse getPatientDemographics(String pid);
   public UpdatedProfile updateDemographicsinAdminDB(UpdatedProfile patientprofile);
}
