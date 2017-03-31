package com.guc.visit.domain;


import com.guc.visit.base.BaseDTO;

public class PregnantBaseDTO<T> extends BaseDTO {
    private String address_str;
    private String birth_date;
    private String blood_rh;
    private String create_week;
    private String create_week_days;
    private String ehr_id;
    private String high_score;
    private String is_high_risk;
    private String menses_over_date;
    private String name;
    private String sex;
    private String record_code;
    private T visitList;
    private String due_birth_date;

    public String getDue_birth_date() {
        return due_birth_date;
    }

    public void setDue_birth_date(String due_birth_date) {
        this.due_birth_date = due_birth_date;
    }

    public T getVisitList() {
        return visitList;
    }

    public void setVisitList(T visitList) {
        this.visitList = visitList;
    }

    public String getAddress_str() {
        return address_str;
    }

    public void setAddress_str(String address_str) {
        this.address_str = address_str;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getBlood_rh() {
        return blood_rh;
    }

    public void setBlood_rh(String blood_rh) {
        this.blood_rh = blood_rh;
    }

    public String getCreate_week() {
        return create_week;
    }

    public void setCreate_week(String create_week) {
        this.create_week = create_week;
    }

    public String getCreate_week_days() {
        return create_week_days;
    }

    public void setCreate_week_days(String create_week_days) {
        this.create_week_days = create_week_days;
    }

    public String getEhr_id() {
        return ehr_id;
    }

    public void setEhr_id(String ehr_id) {
        this.ehr_id = ehr_id;
    }

    public String getHigh_score() {
        return high_score;
    }

    public void setHigh_score(String high_score) {
        this.high_score = high_score;
    }

    public String getIs_high_risk() {
        return is_high_risk;
    }

    public void setIs_high_risk(String is_high_risk) {
        this.is_high_risk = is_high_risk;
    }

    public String getMenses_over_date() {
        return menses_over_date;
    }

    public void setMenses_over_date(String menses_over_date) {
        this.menses_over_date = menses_over_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRecord_code() {
        return record_code;
    }

    public void setRecord_code(String record_code) {
        this.record_code = record_code;
    }
}
