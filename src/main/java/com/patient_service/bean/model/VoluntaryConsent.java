package com.patient_service.bean.model;

public class VoluntaryConsent {
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "VoluntaryConsent{" +
                "cid='" + cid + '\'' +
                ", pid='" + pid + '\'' +
                ", accessor_id='" + accessor_id + '\'' +
                ", accessing_eid='" + accessing_eid + '\'' +
                ", last_update='" + last_update + '\'' +
                ", status='" + status + '\'' +
                ", create_date='" + create_date + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                ", action_taken_by='" + action_taken_by + '\'' +
                ", reason='" + reason + '\'' +
                ", rid='" + rid + '\'' +
                ", record_creator_id='" + record_creator_id + '\'' +
                ", provider_eid='" + provider_eid + '\'' +
                ", tags='" + tag1 + '\'' +
                ", active_flag=" + active_flag +
                '}';
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

    public String getAccessing_eid() {
        return accessing_eid;
    }

    public void setAccessing_eid(String accessing_eid) {
        this.accessing_eid = accessing_eid;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
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

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRecord_creator_id() {
        return record_creator_id;
    }

    public void setRecord_creator_id(String record_creator_id) {
        this.record_creator_id = record_creator_id;
    }

    public String getProvider_eid() {
        return provider_eid;
    }

    public void setProvider_eid(String provider_eid) {
        this.provider_eid = provider_eid;
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

    public int getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(int active_flag) {
        this.active_flag = active_flag;
    }

    public String getConsent_tag1() {
        return consent_tag1;
    }

    public void setConsent_tag1(String consent_tag1) {
        this.consent_tag1 = consent_tag1;
    }

    public String getConsent_tag2() {
        return consent_tag2;
    }

    public void setConsent_tag2(String consent_tag2) {
        this.consent_tag2 = consent_tag2;
    }

    public String getConsent_tag3() {
        return consent_tag3;
    }

    public void setConsent_tag3(String consent_tag3) {
        this.consent_tag3 = consent_tag3;
    }

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

    private String cid;
    private String pid;
    private String accessor_id;
    private String accessing_eid;
    private String last_update;
    private String status;
    private String create_date;
    private String expiry_date;
    private String action_taken_by;
    private String reason;
    private String rid;
    private String record_creator_id;
    private String provider_eid;
    private String tag1;
    private String tag2;
    private String tag3;
    private String consent_tag1;
    private String consent_tag2;
    private String consent_tag3;

    private String accessor_name;
    private String accessing_ename;
    private int active_flag;

}
