package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RoleRightInfo")
public class RoleRightInfo {

	private String pk;
	private Role role;
	private RightInfo rightInfo;
	public RoleRightInfo() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_role")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_rightInfo")
	public RightInfo getRightInfo() {
		return rightInfo;
	}
	public void setRightInfo(RightInfo rightInfo) {
		this.rightInfo = rightInfo;
	}
	
	
}
