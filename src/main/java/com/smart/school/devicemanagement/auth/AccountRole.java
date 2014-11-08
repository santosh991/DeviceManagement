package com.smart.school.devicemanagement.auth;

import java.util.List;

public class AccountRole {

	private String pk;
	private String strName;
	private List<AuthorityMenu> authorityMenus;
	private List<PermissionMenu> permissionMenus;

	public AccountRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountRole(String pk, String strName) {
		super();
		this.pk = pk;
		this.strName = strName;
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

	public void setPermissionMenus(List<PermissionMenu> permissionMenus) {
		this.permissionMenus = permissionMenus;
	}

	public void setAuthorityMenus(List<AuthorityMenu> authorityMenus) {
		this.authorityMenus = authorityMenus;
	}

	public List<PermissionMenu> getPermissionMenus() {
		return this.permissionMenus;
	}

	public List<AuthorityMenu> getAuthorityMenus() {
		return this.authorityMenus;
	}

}
