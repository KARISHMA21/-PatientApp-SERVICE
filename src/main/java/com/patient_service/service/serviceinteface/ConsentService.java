package com.patient_service.service.serviceinteface;

import com.patient_service.bean.model.ConsentActionRequestBody;
import com.patient_service.bean.model.ConsentLogModel;
import com.patient_service.bean.model.PendingRequest;
import com.patient_service.bean.response.MedicalRecordResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsentService {

    ResponseEntity<List<PendingRequest>> getPendingRequests(String pid);

    List<MedicalRecordResponse> getPendingRequestDetails(String pid, String pendingrequestid);

    Integer actionOnPendingRequest(String pendingrequestid, ConsentActionRequestBody consentActionRequestBody);

    List<ConsentLogModel> viewActiveConsents(String pid);
}
