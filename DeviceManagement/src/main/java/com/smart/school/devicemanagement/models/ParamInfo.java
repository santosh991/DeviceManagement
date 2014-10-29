package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ParamInfo")
public class ParamInfo {
	private String pk;
	private String strCode;
	private String strName;
	private String strValue;
	private String strHint;
	
	
	public ParamInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column(name = "pk",  nullable = false, length = 36)
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	@Column(name = "strCode",  nullable = false,unique=true, length = 36)
	public String getStrCode() {
		return strCode;
	}
	public void setStrCode(String strCode) {
		this.strCode = strCode;
	}
	@Column(name = "strName", length = 36)
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	@Column(name = "strValue", length = 512)
	public String getStrValue() {
		return strValue;
	}
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
	@Column(name = "strHint", length = 128)
	public String getStrHint() {
		return strHint;
	}
	public void setStrHint(String strHint) {
		this.strHint = strHint;
	}
	
	
}
