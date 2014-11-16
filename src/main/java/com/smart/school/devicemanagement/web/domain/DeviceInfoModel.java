package com.smart.school.devicemanagement.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.smart.school.devicemanagement.models.SchoolInfo;

public class DeviceInfoModel implements java.io.Serializable{

	private String pk;
	@NotEmpty(message="设备名称不能为空")
	private String strName;
	@NotEmpty(message="mac地址不能为空")
	private String mac;
//	private SchoolInfo schoolInfo;
	@NotEmpty(message="设备地址不能为空")
	private String address;
	@NotEmpty(message="公开级别不能为空")
	private int publicLevel;
	@NotEmpty(message="流服务地址不能为空")
	private String streamUrl1;
	private String streamUrl2;
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPublicLevel() {
		return publicLevel;
	}
	public void setPublicLevel(int publicLevel) {
		this.publicLevel = publicLevel;
	}
	public String getStreamUrl1() {
		return streamUrl1;
	}
	public void setStreamUrl1(String streamUrl1) {
		this.streamUrl1 = streamUrl1;
	}
	public String getStreamUrl2() {
		return streamUrl2;
	}
	public void setStreamUrl2(String streamUrl2) {
		this.streamUrl2 = streamUrl2;
	}
	
	
}
