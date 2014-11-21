package com.smart.school.devicemanagement.web.domain;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.User;

public class CustomInfoModel  implements java.io.Serializable{

	private String pk;
	
	private int gender;
	@NotEmpty(message="联系人姓名不能为空")
	private String strName;
	@NotEmpty(message="密码不能为空")
	private String psd;
	@NotEmpty(message="儿童姓名不能为空")
	private String childrenName;
	@NotEmpty(message="与儿童关系不能为空")
	private String relation;
	
	private String phone1;
	private String phone2;
	
	private User user;
	
	private Calendar expirationTime;
	
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
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
	public String getChildrenName() {
		return childrenName;
	}
	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Calendar getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Calendar expirationTime) {
		this.expirationTime = expirationTime;
	}

	
	
}
