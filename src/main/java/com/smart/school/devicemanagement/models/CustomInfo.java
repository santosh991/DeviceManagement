package com.smart.school.devicemanagement.models;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CustomInfo")
public class CustomInfo implements java.io.Serializable{

	private String pk;
	private int gender;
	private String strName;
	private String psd;
	private String childrenName;
	private String relation;
	private String phone1;
	private String phone2;
	private SchoolInfo schoolInfo;
	private Calendar expirationTime;
	
	public CustomInfo() {
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

	@Column(name = "gender",  nullable = false, length = 2)
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name = "strName",  nullable = false, length = 36)
	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	@Column(name = "psd",  nullable = false, length = 12)
	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	@Column(name = "childrenName",  nullable = false, length = 36)
	public String getChildrenName() {
		return childrenName;
	}

	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}

	@Column(name = "relation",  nullable = false, length = 36)
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Column(name = "phone1",  length = 12)
	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "phone2",  length = 12)
	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_schoolInfo")
	public SchoolInfo getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(SchoolInfo schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	@Column(name = "expirationTime",nullable=false)
	public Calendar getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Calendar expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	
}
