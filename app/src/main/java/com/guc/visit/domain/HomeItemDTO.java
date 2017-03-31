package com.guc.visit.domain;


import com.guc.visit.base.BaseDTO;

public class HomeItemDTO extends BaseDTO {
    private int sourceId;
    private String lable;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
