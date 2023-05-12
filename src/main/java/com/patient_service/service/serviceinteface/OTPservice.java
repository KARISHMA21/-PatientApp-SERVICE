package com.patient_service.service.serviceinteface;

public interface OTPservice {

    int generateandSendOtp(String key,String pid);
    boolean verifyOtp(String key, String otp);
}
