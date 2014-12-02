package com.smart.school.devicemanagement.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class NewsInfoModel implements java.io.Serializable{

	private String pk;
	
	@NotEmpty(message="标题不能为空")
	private String title;
	private String content;
	private NewsTypeModel newsTypeModel;
	
	public NewsInfoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NewsInfoModel(String pk) {
		super();
		this.pk = pk;
	}

	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
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
	public NewsTypeModel getNewsTypeModel() {
		return newsTypeModel;
	}
	public void setNewsTypeModel(NewsTypeModel newsTypeModel) {
		this.newsTypeModel = newsTypeModel;
	}
	
	
	
	
}
