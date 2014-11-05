package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {

	private String pk;
	private String strName;
	
	public Role() {
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
}
