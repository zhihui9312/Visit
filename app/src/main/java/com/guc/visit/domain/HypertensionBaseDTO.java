package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;


public class HypertensionBaseDTO extends BaseDTO {
    private String ehr_id;
    private String sex;
    private String birth_date;
    private String disease_status;
    private String diagnose_date;
    private String source_flag;
    private String height;
    private String weight;
    private String systolic_pressure;
    private String diastolic_pressure;
    private String name;
    private String address_str;

    public String getAddress_str() {
        return address_str;
    }

    public void setAddress_str(String address_str) {
        this.address_str = address_str;
    }

    public String getEhr_id() {
        return ehr_id;
    }

    public void setEhr_id(String ehr_id) {
        this.ehr_id = ehr_id;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDisease_status() {
        return disease_status;
    }

    public void setDisease_status(String disease_status) {
        this.disease_status = disease_status;
    }

    public String getDiagnose_date() {
        return diagnose_date;
    }

    public void setDiagnose_date(String diagnose_date) {
        this.diagnose_date = diagnose_date;
    }

    public String getSource_flag() {
        return source_flag;
    }

    public void setSource_flag(String source_flag) {
        this.source_flag = source_flag;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSystolic_pressure() {
        return systolic_pressure;
    }

    public void setSystolic_pressure(String systolic_pressure) {
        this.systolic_pressure = systolic_pressure;
    }

    public String getDiastolic_pressure() {
        return diastolic_pressure;
    }

    public void setDiastolic_pressure(String diastolic_pressure) {
        this.diastolic_pressure = diastolic_pressure;
    }
}
