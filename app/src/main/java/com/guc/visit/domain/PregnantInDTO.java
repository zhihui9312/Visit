package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;


public class PregnantInDTO extends BaseDTO {
    private String visit_date;
    private String doctor;
    private String drug;
    private String srv_name;
    private String tbl_name;
    private String record_code;

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getSrv_name() {
        return srv_name;
    }

    public void setSrv_name(String srv_name) {
        this.srv_name = srv_name;
    }

    public String getTbl_name() {
        return tbl_name;
    }

    public void setTbl_name(String tbl_name) {
        this.tbl_name = tbl_name;
    }

    public String getRecord_code() {
        return record_code;
    }

    public void setRecord_code(String record_code) {
        this.record_code = record_code;
    }
}
