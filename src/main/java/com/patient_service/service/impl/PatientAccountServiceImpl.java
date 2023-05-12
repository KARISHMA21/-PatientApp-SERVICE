package com.patient_service.service.impl;

import com.patient_service.bean.entity.PatientEntity;
import com.patient_service.bean.model.PasswordResetRequest;
import com.patient_service.bean.model.PatientReg;
import com.patient_service.bean.model.UpdatedProfile;
import com.patient_service.repository.PatientRepository;
import com.patient_service.service.serviceinteface.PatientAccountService;
import com.twilio.Twilio;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import static com.patient_service.bean.entity.Role.USER;

@Transactional
@Service
public class PatientAccountServiceImpl implements PatientAccountService {

    @Autowired
    PatientRepository credentialsrepo;

    @Value("${hospital.emailId}")
    private String myEmail;

    @Value("${hospital.password}")
    private String myPassword;
    private String text;
    private JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();

    public void addCredentials(PatientReg patient){
        PatientEntity cred = new PatientEntity();
        String defaultPWD="NA";
        if(!patient.isAlreadyRegistered()) {
//        System.out.println("Received Patient ID : "+patientId);



            int strength = 10; // work factor of bcrypt
            BCryptPasswordEncoder bCryptPasswordEncoder =
                    new BCryptPasswordEncoder(
//                            strength, new SecureRandom()
                    );
            defaultPWD = String.format(RandomStringUtils.randomAlphanumeric(10));
//

            System.out.println("The generated default password is : " + defaultPWD);
            text="\nUserID: "+patient.getPid()+"\nDefault Password: "+defaultPWD;
            String encodedPassword = bCryptPasswordEncoder.encode(defaultPWD);

            cred.setName(patient.getName());
            cred.setEmail(patient.getEmail());
            cred.setPhone(patient.getPhone());
            cred.setPid(patient.getPid());
            cred.setPassword(encodedPassword);

            cred.setUsername(patient.getPid());
            cred.setRole(USER);

            credentialsrepo.save(cred);
        }
        else {
            System.out.println("Already Registered");
            text="\nUserID: "+patient.getPid();
        }


        javaMailSender.setPort(587);
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername(myEmail);
        javaMailSender.setPassword(myPassword);
        Properties properties=javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(patient.getEmail());
        mailMessage.setSubject("Hello "+patient.getName()+" your Account Details are");

        mailMessage.setText(text);
        mailMessage.setFrom(myEmail);
        javaMailSender.send(mailMessage);


//    syntax    Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
//        Message.creator(new PhoneNumber("+919873109905"),
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Twilio.init("AC17fc7568e92e3b7dc4e2e74b24e9dc60", "3ce3ec39c382fbd7a62d8b72a6d820e7");
        String phoneno="+91"+cred.getPhone();
        Message.creator(new PhoneNumber(phoneno),
                new PhoneNumber("+12708126564"), "Welcome to central medical service "+patient.getName()+" below are your account details.\n" +
                        " Please reset the default password if you are new user \n UserName : "+patient.getPid()+"\n Password : "+defaultPWD).create();


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);

        return;




    }
    public void updateprofile(UpdatedProfile patientprofile){

        Optional<PatientEntity> result = credentialsrepo.findById(patientprofile.getPid());
        PatientEntity profile=new PatientEntity();
        try {
            if (result.isPresent()) {
                profile = result.get();
                javaMailSender.setPort(587);
                javaMailSender.setHost("smtp.gmail.com");
                javaMailSender.setUsername(myEmail);
                javaMailSender.setPassword(myPassword);
                Properties properties = javaMailSender.getJavaMailProperties();
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.auth", "true");
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(profile.getEmail());

                mailMessage.setSubject("Account Profile Update Alert !!");

                text = "Hello " + profile.getName() + " below Account Profile update has been requested for your Patient account -->\n" +
                        "\n Patient ID : " + profile.getPid() +
                        "\n Name : " + patientprofile.getName() +
                        "\n Contact No. : " + patientprofile.getPhone() +
                        "\n Email Id : " + patientprofile.getEmail()
//                        + "\n\n If the updation was not inititated by you kindly change your password on priority"
                ;

                mailMessage.setText(text);
                mailMessage.setFrom(myEmail);
                javaMailSender.send(mailMessage);


//    syntax    Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
//        Message.creator(new PhoneNumber("+919873109905"),
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                Twilio.init("AC17fc7568e92e3b7dc4e2e74b24e9dc60", "3ce3ec39c382fbd7a62d8b72a6d820e7");
                String phoneno = "+91" + profile.getPhone();
                Message.creator(new PhoneNumber(phoneno),
                        new PhoneNumber("+12708126564"), "Hello " + profile.getName() + " below Account Profile update has been requested for your Patient account  -->\n" +
                                "\n Patient ID : " + profile.getPid() +
                                "\n Name : " + patientprofile.getName() +
                                "\n Contact No. : " + patientprofile.getPhone() +
                                "\n Email Id : " + patientprofile.getEmail()

//                                +"\n\n If the updation was not inititated by you kindly change your password on priority"
                ).create();


                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println("[ALERT] Error occured while sending notification : \n"+e.getMessage());

        }


        credentialsrepo.updateProfile(
                patientprofile.getPid(),
                patientprofile.getName(),
                patientprofile.getPhone(),
                patientprofile.getEmail());


        return;

    }

    public int authenticatepwdResetRequest(PasswordResetRequest pwdRequest) {
        Optional<PatientEntity> result = credentialsrepo.findByUsernameAndPhone(pwdRequest.getPid(), pwdRequest.getPhone());
        if (result.isPresent()) {
            // Record found
            PatientEntity patient = result.get();

            return 200;
        }
        else return 404;
    }
    public int updatePassword(PasswordResetRequest newpassword){


        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(
//                            strength, new SecureRandom()
                );
        String encodedPassword = bCryptPasswordEncoder.encode(newpassword.getNewpassword());
        credentialsrepo.updatePassword(
                newpassword.getPid(),
                encodedPassword);

        return 200;
    }

}
