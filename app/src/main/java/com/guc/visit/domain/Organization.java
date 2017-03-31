package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;

public class Organization extends BaseDTO{
	private String name;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
