package com.guc.visit.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.guc.visit.base.BaseDTO;


public class ArchivesQueryInDTO extends BaseDTO {
    private String ehr_id;
    private String name;
    private String crid_code;
    private String work_unit;
    @JSONField(name = "address")
    private String address_str;
    private String telephone;
    private String cr_org_code;
    private String census_register;
    private String hypertension;
    private String diabetes;
    private String mental_illness;
    private String maternal;
    private String children;

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

    public String getCrid_code() {
        return crid_code;
    }

    public void setCrid_code(String crid_code) {
        this.crid_code = crid_code;
    }

    public String getWork_unit() {
        return work_unit;
    }

    public void setWork_unit(String work_unit) {
        this.work_unit = work_unit;
    }

    public String getAddress_str() {
        return address_str;
    }

    public void setAddress_str(String address_str) {
        this.address_str = address_str;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCr_org_code() {
        return cr_org_code;
    }

    public void setCr_org_code(String cr_org_code) {
        this.cr_org_code = cr_org_code;
    }

    public String getCensus_register() {
        return census_register;
    }

    public void setCensus_register(String census_register) {
        this.census_register = census_register;
    }

    public String getHypertension() {
        return hypertension;
    }

    public void setHypertension(String hypertension) {
        this.hypertension = hypertension;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getMental_illness() {
        return mental_illness;
    }

    public void setMental_illness(String mental_illness) {
        this.mental_illness = mental_illness;
    }

    public String getMaternal() {
        return maternal;
    }

    public void setMaternal(String maternal) {
        this.maternal = maternal;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
