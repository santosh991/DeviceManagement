package com.smart.school.devicemanagement.web.domain;

import javax.validation.constraints.Min;


public class UserModel  implements java.io.Serializable {

	private String pk;
	private String strName;
	private String psd;
	private String phone1;
	@Min(0)
	private int enumState;
	private String email;
	
	public UserModel(String pk) {
		super();
		this.pk = pk;
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public int getEnumState() {
		return enumState;
	}
	public void setEnumState(int enumState) {
		this.enumState = enumState;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
