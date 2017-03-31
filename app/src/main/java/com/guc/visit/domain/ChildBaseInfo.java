package com.guc.visit.domain;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ChildBaseInfo extends QueryResultItem {
    private String address_str;
    private String birth_date;
    private String birth_week;
    private String birth_week_days;
    private String bodylong;
    private String born_weight;
    private String ehr_id;
    private String father_name;
    private String father_telephone;
    private String mother_name;
    private String mother_telephone;
    private String name;
    private String sex;

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

    public String getBirth_week() {
        return birth_week;
    }

    public void setBirth_week(String birth_week) {
        this.birth_week = birth_week;
    }

    public String getBirth_week_days() {
        return birth_week_days;
    }

    public void setBirth_week_days(String birth_week_days) {
        this.birth_week_days = birth_week_days;
    }

    public String getBodylong() {
        return bodylong;
    }

    public void setBodylong(String bodylong) {
        this.bodylong = bodylong;
    }

    public String getBorn_weight() {
        return born_weight;
    }

    public void setBorn_weight(String born_weight) {
        this.born_weight = born_weight;
    }

    public String getEhr_id() {
        return ehr_id;
    }

    public void setEhr_id(String ehr_id) {
        this.ehr_id = ehr_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getFather_telephone() {
        return father_telephone;
    }

    public void setFather_telephone(String father_telephone) {
        this.father_telephone = father_telephone;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getMother_telephone() {
        return mother_telephone;
    }

    public void setMother_telephone(String mother_telephone) {
        this.mother_telephone = mother_telephone;
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

}

