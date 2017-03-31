package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;

import java.util.List;


public class PregnantOutDTO<T> extends BaseDTO {
    private T visitList;
    private String errInfo;

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public T getVisitList() {
        return visitList;
    }

    public void setVisitList(T visitList) {
        this.visitList = visitList;
    }
}
