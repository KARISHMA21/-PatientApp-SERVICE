package com.patient_service.bean.response;

import java.math.BigInteger;

public class PatientDemographicsResponse {
    private String pid;
    private String name;
    private String uniqueid;
    private String dob;
    private int age;
    private String gender;
    private BigInteger phone;
    private String address;

    private String email;
    private String minor_incapacitated;
    private String disabled;

    private String haswebappaccess;

    private String guardian_id;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMinor_incapacitated() {
        return minor_incapacitated;
    }

    public void setMinor_incapacitated(String minor_incapacitated) {
        this.minor_incapacitated = minor_incapacitated;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getHaswebappaccess() {
        return haswebappaccess;
    }

    public void setHaswebappaccess(String haswebappaccess) {
        this.haswebappaccess = haswebappaccess;
    }

    public String getGuardian_id() {
        return guardian_id;
    }

    public void setGuardian_id(String guardian_id) {
        this.guardian_id = guardian_id;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", uniqueid=" + uniqueid +
                ", dob='" + dob + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", minor_incapacitated=" + minor_incapacitated +
                ", disabled=" + disabled +
                ", haswebappaccess=" + haswebappaccess +
                ", guardian_id="+guardian_id+
                '}';
    }
}
