package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	private String pk;
	private String strName;
	private String psd;
	
	
	public User() {
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

	@Column(name = "strName", nullable=false, length = 36)
	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	@Column(name = "psd",nullable=false, length = 12)
	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}
	
	
	
}
