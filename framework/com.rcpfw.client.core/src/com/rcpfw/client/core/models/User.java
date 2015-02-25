package com.rcpfw.client.core.models;

public class User {
	private String loginName;
	private String fullName;
	private String email;

	public User(String loginName, String fullName, String email) {
		super();
		this.loginName = loginName;
		this.fullName = fullName;
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
