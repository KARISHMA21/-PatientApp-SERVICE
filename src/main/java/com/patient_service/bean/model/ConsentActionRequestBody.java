package com.patient_service.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentActionRequestBody {
// this model is used to store the incoming action on a pending consent
    private List<List<String>> rid;
    private String action_taken_by;
    private String status;
    private String cid;
    private Date expiry_date;

    @Override
    public String toString() {
        return "ConsentActionRequestBody{" +
                "rid=" + rid +
                ", action_taken_by='" + action_taken_by + '\'' +
                ", status='" + status + '\'' +
                ", cid='" + cid + '\'' +
                ", expiry_date=" + expiry_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsentActionRequestBody that = (ConsentActionRequestBody) o;
        return Objects.equals(rid, that.rid) && Objects.equals(action_taken_by, that.action_taken_by) && Objects.equals(status, that.status) && Objects.equals(cid, that.cid) && Objects.equals(expiry_date, that.expiry_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, action_taken_by, status, cid, expiry_date);
    }

    public List<List<String>> getRid() {
        return rid;
    }

    public void setRid(List<List<String>> rid) {
        this.rid = rid;
    }

    public String getAction_taken_by() {
        return action_taken_by;
    }

    public void setAction_taken_by(String action_taken_by) {
        this.action_taken_by = action_taken_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }
}
