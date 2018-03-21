package com.project.model;
public class EmailCheck {
	public String ranEmail() {
		String  email = "";
		StringBuffer ran1 = new StringBuffer();
		
		// 대문자 4개를 임의 발생 
		ran1.append((char)((Math.random() * 26)+65));  // 첫글자는 대문자, 첫글자부터 특수문자 나오면 안 이쁨
		for(int i = 0; i<3; i++) {
		   ran1.append((char)((Math.random() * 26)+65));  // 아스키번호 65(A) 부터 26글자 중에서 택일
		} 

		// 소문자 4개를 임의발생
		for( int i = 0; i<4; i++) {
		    ran1.append((char)((Math.random() * 26)+97)); // 아스키번호 97(a) 부터 26글자 중에서 택일
		}  

		// 숫자 2개를 임의 발생
		for( int i = 0; i<2; i++) {
		    ran1.append((char)((Math.random() * 10)+48)); //아스키번호 48(1) 부터 10글자 중에서 택일
		}
		
		email = ran1.toString();
		return email;
		}
	}
		
	
	

