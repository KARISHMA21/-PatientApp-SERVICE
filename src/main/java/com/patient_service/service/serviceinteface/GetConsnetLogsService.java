package com.patient_service.service.serviceinteface;

import com.patient_service.bean.response.ConsentLogResponse;
import org.springframework.http.ResponseEntity;

public interface GetConsnetLogsService {
    public ResponseEntity<ConsentLogResponse> getConsentLogs(String pid);

}
