package com.patient_service.bean.response;

import com.patient_service.bean.model.ActiveConsents;

import java.util.List;

public class ListActiveConsents {

    public ListActiveConsents(){

    }
    public ListActiveConsents(List<ActiveConsents> activeConsentsList) {
        this.activeConsentsList = activeConsentsList;
    }

    public List<ActiveConsents> getActiveConsentsList() {
        return activeConsentsList;
    }

    public void setActiveConsentsList(List<ActiveConsents> activeConsentsList) {
        this.activeConsentsList = activeConsentsList;
    }

    List<ActiveConsents> activeConsentsList;
}
