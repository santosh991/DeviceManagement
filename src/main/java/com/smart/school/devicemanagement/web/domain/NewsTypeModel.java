package com.smart.school.devicemanagement.web.domain;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class NewsTypeModel {

	private String pk;
	@NotEmpty(message="标题不能为空")
	private String strName;
	@Min(0)
	private int level;
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public NewsTypeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
