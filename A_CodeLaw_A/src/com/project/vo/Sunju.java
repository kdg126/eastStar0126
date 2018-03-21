package com.project.vo;

public class Sunju {
private int no;
private String id, name, sunju_code, pass, phone, email;
public Sunju() {
	// TODO Auto-generated constructor stub
}
public Sunju(int no, String id, String name, String sunju_code, String pass, String phone) {
	super();
	this.no = no;
	this.id = id;
	this.name = name;
	this.sunju_code = sunju_code;
	this.pass = pass;
	this.phone = phone;
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSunju_code() {
	return sunju_code;
}
public void setSunju_code(String sunju_code) {
	this.sunju_code = sunju_code;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
