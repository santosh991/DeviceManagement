package com.smart.school.devicemanagement.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.smart.school.devicemanagement.models.User;

public class SchoolInfoModel {

	private String pk;
	@NotEmpty(message="校园名称不能为空")
	private String strName;
	private UserLoginModel userModel;
	@NotEmpty(message="地址不能为空")
	private String address;
	private String telephone;
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
	public UserLoginModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserLoginModel userModel) {
		this.userModel = userModel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
