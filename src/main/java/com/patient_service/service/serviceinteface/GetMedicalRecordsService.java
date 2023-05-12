package com.patient_service.service.serviceinteface;
import com.patient_service.bean.model.ListFinalRecord;
import com.patient_service.bean.response.MedicalRecordResponse;
import org.springframework.http.ResponseEntity;


public interface GetMedicalRecordsService {
   public ResponseEntity<ListFinalRecord> getMedicalRecords(String pid);

}
