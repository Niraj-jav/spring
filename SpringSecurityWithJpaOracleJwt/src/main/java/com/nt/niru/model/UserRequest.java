package com.nt.niru.model;

public class UserRequest {
	private String username;
	private String pwd;
	public UserRequest(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	public UserRequest() {}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", pwd=" + pwd + "]";
	}
}
