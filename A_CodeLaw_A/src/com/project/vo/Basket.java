package com.project.vo;

import java.sql.Date;

public class Basket {
	int basketNo;
	String userId;
	int shipNo;
	String productName;
	String price;
	String basketFile;
	Date regDate;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBasketFile() {
		return basketFile;
	}

	public void setBasketFile(String basketFile) {
		this.basketFile = basketFile;
	}

	public Basket() {
		super();
	}

	public int getBasketNo() {
		return basketNo;
	}

	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getShipNo() {
		return shipNo;
	}

	public void setShipNo(int shipNo) {
		this.shipNo = shipNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
