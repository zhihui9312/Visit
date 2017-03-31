package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;


public class ArchivesQueryOutDTO<T>  extends BaseDTO {
    private String errInfo;
    private T personBaseInfoEntity;

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public T getPersonBaseInfoEntity() {
        return personBaseInfoEntity;
    }

    public void setPersonBaseInfoEntity(T personBaseInfoEntity) {
        this.personBaseInfoEntity = personBaseInfoEntity;
    }
}
