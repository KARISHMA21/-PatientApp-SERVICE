package com.patient_service.bean.model;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ListFinalRecord {

    public List<FinalRecords> getFinalRecordsList() {
        return finalRecordsList;
    }

    public void setFinalRecordsList(List<FinalRecords> finalRecordsList) {
        this.finalRecordsList = finalRecordsList;
    }

    public ListFinalRecord(List<FinalRecords> finalRecordsList) {
        this.finalRecordsList = finalRecordsList;
    }

    List<FinalRecords> finalRecordsList;
}
