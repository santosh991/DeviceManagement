package com.smart.school.devicemanagement.models;

import java.util.Calendar;

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
	private String phone1;
	private int enumState;
	private String email;
	private Calendar registerTime;
	
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

	@Column(name = "phone1", length = 11)
	public String getPhone1() {
		return phone1;
	}

	
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "enumState",nullable=false, length = 1)
	public int getEnumState() {
		return enumState;
	}

	public void setEnumState(int enumState) {
		this.enumState = enumState;
	}

	@Column(name = "email", length = 56)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "registerTime",nullable=false)
	public Calendar getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Calendar registerTime) {
		this.registerTime = registerTime;
	}
	
	
	
}
