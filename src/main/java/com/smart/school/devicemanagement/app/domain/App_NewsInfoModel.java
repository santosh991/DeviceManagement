package com.smart.school.devicemanagement.app.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.User;

public class App_NewsInfoModel implements Serializable {

	private String pk;
	private String schoolInfoPk;
	private String schoolInfoName;
	private String publicUserId;
	private String publicUserName;
	private String title;
	private String content;
	private Calendar publicTime;
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getSchoolInfoPk() {
		return schoolInfoPk;
	}
	public void setSchoolInfoPk(String schoolInfoPk) {
		this.schoolInfoPk = schoolInfoPk;
	}
	public String getSchoolInfoName() {
		return schoolInfoName;
	}
	public void setSchoolInfoName(String schoolInfoName) {
		this.schoolInfoName = schoolInfoName;
	}
	public String getPublicUserId() {
		return publicUserId;
	}
	public void setPublicUserId(String publicUserId) {
		this.publicUserId = publicUserId;
	}
	public String getPublicUserName() {
		return publicUserName;
	}
	public void setPublicUserName(String publicUserName) {
		this.publicUserName = publicUserName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Calendar getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(Calendar publicTime) {
		this.publicTime = publicTime;
	}
	
	
}
