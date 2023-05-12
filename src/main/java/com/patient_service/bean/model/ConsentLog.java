package com.patient_service.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentLog {

    private String cid;
    private String pid;
    private String accessor_id;
    //    private String accessor_name;
    private String accessing_eid;
    //    private String accessing_ename;
    private Date last_update;

    private String status;
    private Date create_date;
    private Date expiry_date;
    private String action_taken_by;

    private String reason;

    @Override
    public String toString() {
        return "ConsentLog{" +
                "cid=" + cid +
                ", pid='" + pid + '\'' +
                ", accessor_id='" + accessor_id + '\'' +
                ", accessing_eid='" + accessing_eid + '\'' +
                ", last_update='" + last_update + '\'' +
                ", status='" + status + '\'' +
                ", create_date='" + create_date + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                ", action_taken_by='" + action_taken_by + '\'' +
                '}';
    }

}