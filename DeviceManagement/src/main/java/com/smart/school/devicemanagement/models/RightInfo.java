package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RightInfo")
public class RightInfo {

	private String pk;
	private String strName;
	private String url;
	private String strHint;
	
	public RightInfo() {
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

	@Column(name = "strName", length = 72)
	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	@Column(name = "url", length = 256)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "strHint", length = 256)
	public String getStrHint() {
		return strHint;
	}

	public void setStrHint(String strHint) {
		this.strHint = strHint;
	}
	
	
}
