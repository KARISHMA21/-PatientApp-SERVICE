package com.patient_service.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.patient_service.service.serviceinteface.OTPservice;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.Random;
import java.util.concurrent.TimeUnit;
@Transactional
@Service
public class OTPserviceImpl implements OTPservice {


    private static final Integer EXPIRE_MINS = 5;
//    private LoadingCache<String,Integer> otpCache;

    LoadingCache<String, String> otpCache = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                public String load(String key) {
                    return "";
                }
            });

//    public void OtpServiceImpl() {
//        otpCache = CacheBuilder.newBuilder()
//                .expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
//                .build(new CacheLoader<String, Integer>() {
//                    @Override
//                    public Integer load(String key) throws Exception {
//                        return null;
//                    }
//                });
//    }


    public int generateandSendOtp(String key,String pid) {

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
//        System.out.println("Generated OTP for "+key+" "+pid+"is "+otp);
        try {
            otpCache.put(key, String.valueOf(otp));
        }catch (Exception e){
            System.out.println("Error occured"+e.getMessage());
            return 403;
        }
        System.out.println("Generated OTP for "+key+" "+pid+"is "+otpCache.getIfPresent(key));


        Twilio.init("AC17fc7568e92e3b7dc4e2e74b24e9dc60", "3ce3ec39c382fbd7a62d8b72a6d820e7");
        String phoneno = "+91" + key;
        Message.creator(new PhoneNumber(phoneno),
                new PhoneNumber("+12708126564"), "\nHello password reset request has been generated for your account with Patient Id : "+ pid+"\nOTP to validate the password reset is: \n"+String.valueOf(otp))
                .create();

        return 200 ;
    }

    public boolean verifyOtp(String key, String otp) {
        String cacheOtp = otpCache.getIfPresent(key);

        if(cacheOtp.equals(otp)) {
            otpCache.invalidate(key); // remove OTP from cache
            System.out.println("OTP Validation Successful");
            return true;
        } else {
            return false;
        }
    }
}
