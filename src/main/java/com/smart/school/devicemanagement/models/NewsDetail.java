package com.smart.school.devicemanagement.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NewsDetail")
public class NewsDetail implements Serializable {

	private String pk;
	private NewsInfo newsInfo;
	private String content;
	
	
	
	public NewsDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NewsDetail(NewsInfo newsInfo, String content) {
		super();
		this.pk = newsInfo.getPk();
		this.newsInfo = newsInfo;
		this.content = content;
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
	@JoinColumn(name="fk_newsInfo")
	public NewsInfo getNewsInfo() {
		return newsInfo;
	}
	public void setNewsInfo(NewsInfo newsInfo) {
		this.newsInfo = newsInfo;
	}
	@Column(name = "content",  length = 2048)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
