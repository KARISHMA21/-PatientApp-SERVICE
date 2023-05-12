package com.patient_service.service.serviceinteface;

import com.patient_service.bean.model.ActiveConsentRecords;
import com.patient_service.bean.model.ActiveConsents;
import com.patient_service.bean.model.ListFinalRecord;
import com.patient_service.bean.response.ConsentStats;
import com.patient_service.bean.response.ListActiveConsents;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GetActiveConsentService {
    public ResponseEntity<ListActiveConsents> getActiveConsents(String pid);
    public ResponseEntity<ListFinalRecord> getActiveConsentsRecords(String pid,String cid);
    public int updateActiveConsentRecords(List<ActiveConsentRecords> activeConsentRecordsList);

    public int revokeConsent(ActiveConsents activeConsent);
    public ResponseEntity<ConsentStats> getConsentStats(String pid);
}
