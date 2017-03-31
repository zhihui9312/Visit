package com.guc.visit.domain;


import com.guc.visit.base.BaseDTO;

public class WorkloadStatistics extends BaseDTO{
    private String rs;
    private String rc;
    private String mbbz;

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMbbz() {
        return mbbz;
    }

    public void setMbbz(String mbbz) {
        this.mbbz = mbbz;
    }
}
