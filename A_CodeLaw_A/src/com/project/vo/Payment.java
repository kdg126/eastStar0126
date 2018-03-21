package com.project.vo;

import java.sql.Date;

public class Payment {
	int paymentNo;
	String userId;
	int productNo; // 배번호
	String productName; // 배이름
	String price;
	String contact; // 전번
	String paymentFile;
	Date regDate;

	public String getPaymentFile() {
		return paymentFile;
	}

	public void setPaymentFile(String paymentFile) {
		this.paymentFile = paymentFile;
	}

	public Payment() {
		super();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}