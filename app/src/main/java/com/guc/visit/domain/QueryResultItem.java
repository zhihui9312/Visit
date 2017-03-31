package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;

import java.io.Serializable;


public class QueryResultItem extends BaseDTO {

    public String name;
    public String address_str;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_str() {
        return address_str;
    }

    public void setAddress_str(String address_str) {
        this.address_str = address_str;
    }
}
