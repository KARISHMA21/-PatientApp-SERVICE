package com.patient_service.bean.model;

import java.util.Date;

public class ActiveConsentRecords {

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

    public String getProvider_eid() {
        return provider_eid;
    }

    public void setProvider_eid(String provider_eid) {
        this.provider_eid = provider_eid;
    }

    public String getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(String active_flag) {
        this.active_flag = active_flag;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    private String cid;
    private String pid;
    private String rid;

    private String provider_eid;
    private String active_flag;
    private Date last_update;
}
