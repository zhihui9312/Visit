package com.guc.visit.domain;


import com.guc.visit.base.BaseDTO;

public class OrganizationDTO extends BaseDTO {


    private static final long serialVersionUID = 1L;
    private String id;
    private String orgname;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
