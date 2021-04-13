package com.nt.niru.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
@Entity
@Table(name="user_tab")
public class User {
	@Id
	@GeneratedValue
	@Column(name="id")
private Integer id;
	@Column(name="uname")
private String username;
	@Column(name="pass")
private String pwd;
@ElementCollection(fetch =FetchType.EAGER)
@Column(name="uroles")
@CollectionTable(name="roles_tab",
                 joinColumns =@JoinColumn(name="id"))
private Set<String> roles;
public User(Integer id, String username, String pwd, Set<String> roles) {
	super();
	this.id = id;
	this.username = username;
	this.pwd = pwd;
	this.roles = roles;
}
public User() {}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
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
public Set<String> getRoles() {
	return roles;
}
public void setRoles(Set<String> roles) {
	this.roles = roles;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", pwd=" + pwd + ", roles=" + roles + "]";
}
}
