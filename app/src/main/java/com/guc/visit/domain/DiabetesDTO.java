package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;


public class DiabetesDTO extends BaseDTO {
    private String ehr_id;
    private String name;
    private String sex;
    private String birth_date;
    private String disease_status;
    private String diagnose_date;
    private String source_flag;
    private String diabetes_type;
    private String height;
    private String family_diabetes;
    private String blood_sugar;
    private String hr_status;
    private String audit_flag;
    private String address_str;

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

    public String getDiabetes_type() {
        return diabetes_type;
    }

    public void setDiabetes_type(String diabetes_type) {
        this.diabetes_type = diabetes_type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFamily_diabetes() {
        return family_diabetes;
    }

    public void setFamily_diabetes(String family_diabetes) {
        this.family_diabetes = family_diabetes;
    }

    public String getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(String blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public String getHr_status() {
        return hr_status;
    }

    public void setHr_status(String hr_status) {
        this.hr_status = hr_status;
    }

    public String getAudit_flag() {
        return audit_flag;
    }

    public void setAudit_flag(String audit_flag) {
        this.audit_flag = audit_flag;
    }

    public String getAddress_str() {
        return address_str;
    }

    public void setAddress_str(String address_str) {
        this.address_str = address_str;
    }
}
