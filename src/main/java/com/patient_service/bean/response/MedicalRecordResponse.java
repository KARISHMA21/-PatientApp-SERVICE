package com.patient_service.bean.response;

import com.patient_service.bean.model.FinalRecords;


import java.util.List;

public class MedicalRecordResponse {


    public List<FinalRecords> getFinalRecordsList() {
        return finalRecordsList;
    }

    @Override
    public String toString() {
        return "MedicalRecordResponse{" +
                "finalRecordsList=" + finalRecordsList +
                '}';
    }

    public void setFinalRecordsList(List<FinalRecords> finalRecordsList) {
        this.finalRecordsList = finalRecordsList;
    }

    List<FinalRecords> finalRecordsList;



}
