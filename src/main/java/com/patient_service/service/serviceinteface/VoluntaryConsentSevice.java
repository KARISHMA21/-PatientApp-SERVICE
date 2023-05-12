package com.patient_service.service.serviceinteface;

import com.patient_service.bean.model.VoluntaryConsent;

import java.util.List;


public interface VoluntaryConsentSevice {

    public int sendVoluntaryConsent(List<VoluntaryConsent> voluntaryConsent);

}
