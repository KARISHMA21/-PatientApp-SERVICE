package com.patient_service.security.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Service
public class EncryptDecrypt {
    @Value("${his.client.aeskey}")
    private  String AES_KEY;
    public String encrytData(String data) {

        AES aes=new AES(AES_KEY);
        return aes.encrytData(data);
    }
    public String decryptData(String data) {

        AES aes=new AES(AES_KEY);
        return aes.decryptData(data);
    }
    private class AES {
        private SecretKeySpec secretkey;
        private byte[] key;

        AES(String secret) {
            MessageDigest sha = null;
            try {
                key = secret.getBytes(StandardCharsets.ISO_8859_1);
                sha = MessageDigest.getInstance("SHA-1");
                key = sha.digest(key);
                key= Arrays.copyOf(key,16);
                secretkey = new SecretKeySpec(key, "AES");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public String encrytData(String data) {
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretkey);
                return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.ISO_8859_1)));
            } catch (Exception e) {
                e.printStackTrace();

            }
            return data;
        }

        public String decryptData(String data) {
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretkey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

    }
}

