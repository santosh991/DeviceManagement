package com.smart.school.devicemanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DeviceInfo")
public class DeviceInfo {
	
	private String pk;
	private String strName;
	private String mac;
	private SchoolInfo schoolInfo;
	private String address;
	private int publicLevel;
	private String streamUrl1;
	private String streamUrl2;
	
	public DeviceInfo() {
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

	@Column(name = "mac", length = 72)
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_schoolInfo")
	public SchoolInfo getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(SchoolInfo schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	@Column(name = "address", length = 72)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "publicLevel", length = 2)
	public int getPublicLevel() {
		return publicLevel;
	}

	public void setPublicLevel(int publicLevel) {
		this.publicLevel = publicLevel;
	}

	@Column(name = "streamUrl1", length = 256)
	public String getStreamUrl1() {
		return streamUrl1;
	}

	public void setStreamUrl1(String streamUrl1) {
		this.streamUrl1 = streamUrl1;
	}

	@Column(name = "streamUrl2", length = 256)
	public String getStreamUrl2() {
		return streamUrl2;
	}

	public void setStreamUrl2(String streamUrl2) {
		this.streamUrl2 = streamUrl2;
	}
	
	
	
	
}
