package com.patient_service.security.contoller;

import com.patient_service.bean.model.PasswordResetRequest;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.security.response.AuthenticationResponse;
import com.patient_service.security.service.AuthenticationService;
import com.patient_service.security.service.LogoutService;
import com.patient_service.security.service.JwtService;
import com.patient_service.service.serviceinteface.OTPservice;
import com.patient_service.service.serviceinteface.PatientAccountService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    Logger logger= LoggerFactory.getLogger(AuthenticationController.class);
    private static final Logger CUSTOM_LOGGER = LoggerFactory.getLogger("CustomLogger");

    private final AuthenticationService service;

    private final PatientAccountService accountService;

    private final OTPservice OTPsvc;

    @Value("${admin.client.id}")
    private String adminClientId;

    @Value("${admin.client.secret}")
    private String adminClientSecret;


    @Autowired
    private JwtService jwtService;

    private final LogoutService logoutService;


    //    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ) {
//        return ResponseEntity.ok(service.register(request));
//    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
//            logger.info("[Log in] "+request.getUsername());
            CUSTOM_LOGGER.info("[Login] "+"["+request.getUsername()+"]"+" "+"PatientService"+" "+"\"\"");
            return ResponseEntity.ok(service.authenticate(request));
        }
        catch (Exception e){
            CUSTOM_LOGGER.error("[Login] "+"["+request.getUsername()+"]"+" "+"PatientService"+" "+"\""+e.getMessage()+"\"");
            return ResponseEntity.status(403).build();
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<AuthenticationResponse> logout(@RequestHeader HttpHeaders headers) {
        try {
            System.out.println(headers);
            logoutService.logout(headers);


            // String pid=jwtService.extractClaim(headers.get("Authorization").get(0),Claims::getSubject);
            CUSTOM_LOGGER.info("[LogOut] "+"[NA]"+" "+"PatientService"+" "+"\"\"");
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            System.out.println(e);
            CUSTOM_LOGGER.error("[LogOut] "+"[NA]"+" "+"PatientService"+" "+"\""+e.getMessage()+"\"");

            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping(value = "/authenticate-admin")
    public ResponseEntity<?> adminAuthentication(@RequestBody AuthenticationRequest authRequest){

        if(adminClientId.equals(authRequest.getUsername()) && adminClientSecret.equals(authRequest.getPassword())){
            String token=jwtService.createToken(adminClientId);
            System.out.println(token);
            CUSTOM_LOGGER.info("[AdminService] "+"["+authRequest.getUsername()+"]"+" "+"AdminService"+"\"\"");
            return ResponseEntity.ok(token);
        }
//        CUSTOM_LOGGER.error("[Admin Service] "+authRequest.getUsername());
        ResponseEntity<String> resp=new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
//        CUSTOM_LOGGER.error("[Admin Service] "+authRequest.getUsername()+""+resp.getBody());
        CUSTOM_LOGGER.error("[AdminService] "+"["+authRequest.getUsername()+"]"+" "+"AdminService"+" "+"\""+resp.getBody()+"\"");
        return resp;
    }

    @PostMapping("/password-reset")
    public ResponseEntity authenticatepwdresetrequest(@RequestBody PasswordResetRequest pwdresetrequest) {
        CUSTOM_LOGGER.info("[PasswordReset] "+"["+pwdresetrequest.getPid()+"]"+" "+"PatientService"+"\"\"");

        try {
            System.out.println("authenticatepwdResetRequest validated "+pwdresetrequest.getPhone()+" "+pwdresetrequest.getPid()+pwdresetrequest.getOtp());

            int status=accountService.authenticatepwdResetRequest(pwdresetrequest);
            if(status==200){
                System.out.println("Valid request");
                status=OTPsvc.generateandSendOtp(String.valueOf(pwdresetrequest.getPhone()),pwdresetrequest.getPid());
                if(status==200)
                {
                    System.out.println("OTP generated Successfully");
                    return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
                }
                else {
                    System.out.println("OTP generation failed");
                    return ResponseEntity.status(HttpStatusCode.valueOf(501)).build();
                }
            }
            else
            {
                System.out.println("Account not found");
//                CUSTOM_LOGGER.error("[Password Reset] "+" Account not found");
                CUSTOM_LOGGER.error("[PasswordReset] "+"["+pwdresetrequest.getPid()+"]"+" "+"PatientService"+" "+"\"Account not found\"");
                return ResponseEntity.status(403).build();
            }



        }
        catch (Exception e){
            CUSTOM_LOGGER.error("[PasswordReset] "+"["+pwdresetrequest.getPid()+"]"+" "+"PatientService"+" "+"\""+e.getMessage()+"\"");

            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/validate-OTP")
    public ResponseEntity validateOTP(@RequestBody PasswordResetRequest enteredOTP) {
        try {
            boolean status=OTPsvc.verifyOtp(String.valueOf(enteredOTP.getPhone()),enteredOTP.getOtp());
            if(status==true)
            {
                System.out.println("OTP Validation Successful");
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
            }
            else
            {
                System.out.println("OTP Validation Failed");

                CUSTOM_LOGGER.error("[PasswordReset] "+"["+enteredOTP.getPid()+"]"+" "+"PatientService"+" "+"\"OTP Validation Failed\"");

                return ResponseEntity.status(403).build();
            }


        }
        catch (Exception e){
            CUSTOM_LOGGER.error("[PasswordReset] "+"["+enteredOTP.getPid()+"]"+" "+"PatientService"+" "+"\""+e.getMessage()+"\"");
            return ResponseEntity.status(403).build();
        }
    }


    @PostMapping("/update-password")
    public ResponseEntity updatePassword(@RequestBody PasswordResetRequest newpassword) {
        try {
            System.out.println("Updating password for "+newpassword.getPid()+" to "+newpassword.getNewpassword());

            int status=accountService.updatePassword(newpassword);

            System.out.println("Password Update Successful");
//            CUSTOM_LOGGER.info("[Password Reset] "+newpassword.getPid()+" Success");
            CUSTOM_LOGGER.info("[PasswordReset] "+"["+newpassword.getPid()+"]"+" "+"PatientService"+" "+"\"Success\"");

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();



        }
        catch (Exception e){
            System.out.println("Passoword update failed");
            CUSTOM_LOGGER.error("[PasswordReset] "+"["+newpassword.getPid()+"]"+" "+"PatientService"+" "+"\"Passoword update failed\"");

            return ResponseEntity.status(403).build();
        }
    }


}