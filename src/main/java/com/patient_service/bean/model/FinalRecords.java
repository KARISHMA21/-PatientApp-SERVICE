package com.patient_service.bean.model;

public class FinalRecords {
    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEtype() {
        return etype;
    }

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGen_date() {
        return gen_date;
    }

    public void setGen_date(String gen_date) {
        this.gen_date = gen_date;
    }

    public String getRec_type() {
        return rec_type;
    }

    public void setRec_type(String rec_type) {
        this.rec_type = rec_type;
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

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    private  String eid;
    private  String ename;
    private  String etype;
    private String rid;
    private String desc;
    private String gen_date;

    @Override
    public String toString() {
        return "FinalRecords{" +
                "eid='" + eid + '\'' +
                ", ename='" + ename + '\'' +
                ", etype='" + etype + '\'' +
                ", rid='" + rid + '\'' +
                ", desc='" + desc + '\'' +
                ", gen_date='" + gen_date + '\'' +
                ", rec_type='" + rec_type + '\'' +
                ", tags='" + tag1+ '\'' +
                ", did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }

    private String rec_type;
    private String tag1;
    private String tag2;
    private String tag3;
    private String did;
    private String dname;





}
