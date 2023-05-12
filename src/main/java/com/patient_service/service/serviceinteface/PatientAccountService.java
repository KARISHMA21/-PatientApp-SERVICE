package com.patient_service.service.serviceinteface;

import com.patient_service.bean.model.PasswordResetRequest;
import com.patient_service.bean.model.PatientReg;
import com.patient_service.bean.model.UpdatedProfile;

public interface PatientAccountService {

   public void addCredentials(PatientReg patientd);

   public void updateprofile(UpdatedProfile patientprofile);

   public int authenticatepwdResetRequest(PasswordResetRequest pwdRequest);
   public int updatePassword(PasswordResetRequest newpassword);

}
