package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SchoolInfo")
public class SchoolInfo implements java.io.Serializable{

	private String pk;
	private String strName;
	private User user;
	private String address;
	private String telephone;
	
	public SchoolInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public SchoolInfo(String pk) {
		super();
		this.pk = pk;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "address", length = 128)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "telephone", length = 11)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
