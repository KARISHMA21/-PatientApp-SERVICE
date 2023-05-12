package com.patient_service.bean.response;

import java.math.BigInteger;

public class ConsentStats {


    public BigInteger getTotalconsent() {
        return totalconsent;
    }

    public void setTotalconsent(BigInteger totalconsent) {
        this.totalconsent = totalconsent;
    }

    public BigInteger getActiveconsent() {
        return activeconsent;
    }

    public void setActiveconsent(BigInteger activeconsent) {
        this.activeconsent = activeconsent;
    }

    public BigInteger getRevokedconsent() {
        return revokedconsent;
    }

    public void setRevokedconsent(BigInteger revokedconsent) {
        this.revokedconsent = revokedconsent;
    }

    public BigInteger getPendingrequests() {
        return pendingrequests;
    }

    public void setPendingrequests(BigInteger pendingrequests) {
        this.pendingrequests = pendingrequests;
    }

    private BigInteger totalconsent;
    private BigInteger activeconsent;
    private BigInteger revokedconsent;
    private BigInteger pendingrequests;



}
