package com.patient_service.bean.model;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data //for getters and setters
public class ConsentLogModel {

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAccessor_id() {
        return accessor_id;
    }

    public void setAccessor_id(String accessor_id) {
        this.accessor_id = accessor_id;
    }

//    public String getAccessor_name() {
//        return accessor_name;
//    }
//
//    public void setAccessor_name(String accessor_name) {
//        this.accessor_name = accessor_name;
//    }

    public String getAccessing_eid() {
        return accessing_eid;
    }

    public void setAccessing_eid(String accessing_eid) {
        this.accessing_eid = accessing_eid;
    }

//    public String getAccessing_ename() {
//        return accessing_ename;
//    }
//
//    public void setAccessing_ename(String accessing_ename) {
//        this.accessing_ename = accessing_ename;
//    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getAction_taken_by() {
        return action_taken_by;
    }

    public void setAction_taken_by(String action_taken_by) {
        this.action_taken_by = action_taken_by;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Id
    private String cid;
    private String pid;
    private String accessor_id;
    private String accessor_name;
    private String accessing_eid;
    private String accessing_ename;
    private Date last_update;
    private String status;
    private Date create_date;
    private Date expiry_date;
    private String action_taken_by;
    private String reason;
    private String tag1;
    private String tag2;

    public String getAccessor_name() {
        return accessor_name;
    }

    public void setAccessor_name(String accessor_name) {
        this.accessor_name = accessor_name;
    }

    public String getAccessing_ename() {
        return accessing_ename;
    }

    public void setAccessing_ename(String accessing_ename) {
        this.accessing_ename = accessing_ename;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    private String tag3;

}
