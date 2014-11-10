package com.smart.school.devicemanagement.auth;

public class AccountAuth implements java.io.Serializable{

	private String pk;

	private String strName;
	private AccountRole accountRole;

	public AccountAuth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountAuth(String pk, String strName) {
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

	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}

	public AccountRole getAccountRole() {
		return this.accountRole;
	}

}
