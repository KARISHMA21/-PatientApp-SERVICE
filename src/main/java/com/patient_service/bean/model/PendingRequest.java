package com.patient_service.bean.model;

import java.util.Date;
import java.util.Objects;

public class PendingRequest {
    private String pendingRequestId;
    private String pid;
    private String requestor_id;
    private String requestor_eid;
    private String requestor_ename;
    private String requestor_name;
    private String tag1;
    private String tag2;
    private String tag3;
    private String reason;
    private Date request_date;
    private Date from_date;
    private Date to_date;
    private Date expiry_date;

    @Override
    public String toString() {
        return "PendingRequest{" +
                "pendingRequestId='" + pendingRequestId + '\'' +
                ", pid='" + pid + '\'' +
                ", requestor_id='" + requestor_id + '\'' +
                ", requestor_eid='" + requestor_eid + '\'' +
                ", requestor_ename='" + requestor_ename + '\'' +
                ", requestor_name='" + requestor_name + '\'' +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", reason='" + reason + '\'' +
                ", request_date=" + request_date +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                ", expiry_date=" + expiry_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PendingRequest that = (PendingRequest) o;
        return Objects.equals(pendingRequestId, that.pendingRequestId) && Objects.equals(pid, that.pid) && Objects.equals(requestor_id, that.requestor_id) && Objects.equals(requestor_eid, that.requestor_eid) && Objects.equals(requestor_ename, that.requestor_ename) && Objects.equals(requestor_name, that.requestor_name) && Objects.equals(tag1, that.tag1) && Objects.equals(tag2, that.tag2) && Objects.equals(tag3, that.tag3) && Objects.equals(reason, that.reason) && Objects.equals(request_date, that.request_date) && Objects.equals(from_date, that.from_date) && Objects.equals(to_date, that.to_date) && Objects.equals(expiry_date, that.expiry_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pendingRequestId, pid, requestor_id, requestor_eid, requestor_ename, requestor_name, tag1, tag2, tag3, reason, request_date, from_date, to_date, expiry_date);
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getPendingRequestId() {
        return pendingRequestId;
    }

    public void setPendingRequestId(String pendingRequestId) {
        this.pendingRequestId = pendingRequestId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRequestor_id() {
        return requestor_id;
    }

    public void setRequestor_id(String requestor_id) {
        this.requestor_id = requestor_id;
    }

    public String getRequestor_eid() {
        return requestor_eid;
    }

    public void setRequestor_eid(String requestor_eid) {
        this.requestor_eid = requestor_eid;
    }

    public String getRequestor_ename() {
        return requestor_ename;
    }

    public void setRequestor_ename(String requestor_ename) {
        this.requestor_ename = requestor_ename;
    }

    public String getRequestor_name() {
        return requestor_name;
    }

    public void setRequestor_name(String requestor_name) {
        this.requestor_name = requestor_name;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
}
