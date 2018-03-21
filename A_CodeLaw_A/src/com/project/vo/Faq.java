package com.project.vo;

public class Faq {
	
	private int no;
	private String title;
	private String content;
	private int readCount;
	private String pass;
	
	public Faq() {
		
	}
	
	public Faq(int no, String title, String content, int readCount, String pass) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
		this.pass = pass;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	

}
