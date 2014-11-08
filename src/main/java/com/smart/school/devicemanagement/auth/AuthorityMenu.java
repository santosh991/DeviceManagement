package com.smart.school.devicemanagement.auth;

import java.util.List;

public class AuthorityMenu {

	private String pk;
	private String strName;
	private String itemIcon;
	private String url;
	private List<AuthorityMenu> childrens;

	public AuthorityMenu(String pk, String strName, String itemIcon, String url) {
		this.pk = pk;
		this.strName = strName;
		this.itemIcon = itemIcon;
		this.url = url;
	}

	public AuthorityMenu(String pk, String strName, String itemIcon,
			String url, List<AuthorityMenu> childrens) {
		this.pk = pk;
		this.strName = strName;
		this.itemIcon = itemIcon;
		this.url = url;
		this.childrens = childrens;
	}

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

	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setChildrens(List<AuthorityMenu> childrens) {
		this.childrens = childrens;
	}

	public String getItemIcon() {
		return this.itemIcon;
	}

	public String getUrl() {
		return this.url;
	}

	public List<AuthorityMenu> getChildrens() {
		return this.childrens;
	}

}