package com.smart.school.devicemanagement.web.domain;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.smart.school.devicemanagement.models.User;

public class SchoolInfoModel  implements java.io.Serializable{

	private String pk;
	@NotEmpty(message="校园名称不能为空")
	private String strName;
	
	@NotEmpty(message="地址不能为空")
	private String address;
	private String telephone;
	
	@NotNull(message="园区必须有管理员")
	private User user;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
