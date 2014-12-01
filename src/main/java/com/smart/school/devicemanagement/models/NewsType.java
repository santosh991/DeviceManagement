package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NewsType")
public class NewsType {

	private String pk;
	private String strName;
	private int level;
	
	public NewsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NewsType(String pk, String strName) {
		super();
		this.pk = pk;
		this.strName = strName;
	}
	
	@Id
	@Column(name = "pk",  nullable = false, length = 36)
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	
	@Column(name = "strName",  nullable = false, length = 36)
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	
	@Column(name = "level",  nullable = false, length = 2)
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
}
