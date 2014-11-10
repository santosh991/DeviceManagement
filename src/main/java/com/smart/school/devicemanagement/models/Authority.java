package com.smart.school.devicemanagement.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Authority")
public class Authority implements java.io.Serializable{

	private String pk;
	private String strName;
	private String itemIcon;
	private String url;
	private String matchUrl;
	private String levelCode;
	private int position;
	private int enumState;
	private String theValue;
	private Authority parent;
	
	private String strHint;
	
	public Authority() {
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

	@Column(name = "url", length = 256)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "strHint", length = 256)
	public String getStrHint() {
		return strHint;
	}

	public void setStrHint(String strHint) {
		this.strHint = strHint;
	}

	@Column(name = "itemIcon", length = 256)
	public String getItemIcon() {
		return itemIcon;
	}

	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}

	@Column(name = "matchUrl", length = 256)
	public String getMatchUrl() {
		return matchUrl;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	@Column(name = "levelCode", length = 36)
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	@Column(name = "position", length = 36)
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Column(name = "enumState", length = 2)
	public int getEnumState() {
		return enumState;
	}

	public void setEnumState(int enumState) {
		this.enumState = enumState;
	}

	@Column(name = "theValue", length = 72)
	public String getTheValue() {
		return theValue;
	}

	public void setTheValue(String theValue) {
		this.theValue = theValue;
	}

	@ManyToOne(targetEntity = Authority.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    @JoinColumn(name = "parent")    
	public Authority getParent() {
		return parent;
	}

	public void setParent(Authority parent) {
		this.parent = parent;
	}
	
}
