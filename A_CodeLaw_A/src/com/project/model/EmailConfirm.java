package com.project.model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class EmailConfirm {
	
	public String connectEmail(String email) {
		EmailCheck e = new EmailCheck();
		String to1=email; 			     //인증위해 사용자가 입력한 이메일주소
		String host="smtp.gmail.com";    //smtp 서버(네이버던,구글이던 설정해서 쓰면돼
		String subject="코드로 이메일 인증번호입니다.";		 //보내는 제목 설정
		String fromName="코드로 관리자";		 //보내는 이름 설정
		String from="kdg012633@gmail.com";	//보내는 사람(구글계정)
		String authNum=e.ranEmail(); //난 여기서 내가만든 이메일 난수 를 썼어.
		String content="가입을 위한 인증번호입니다. ["+authNum+"]"; //보내느 내용 입력해
		
		//SMTP 이용하기 위해 설정해주는 설정값들
		
		try {
			Properties props=new Properties();
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.setProperty
			("mail.smtp.soketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "25");
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			
			Session mailSession
			= Session.getInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("kdg012633@gmail.com", "rlaehdrb1!");
							 //email 계정과 비번입력해
				}
			});
			
			
			Message msg= new MimeMessage(mailSession);
			InternetAddress[] address1 = {new InternetAddress(to1)};
			msg.setFrom(new InternetAddress
							(from,MimeUtility.encodeText(fromName,"utf-8","B")));
			msg.setRecipients(Message.RecipientType.TO, address1);//받는 사람 설정
			msg.setSubject(subject); //제목설정
			msg.setSentDate(new java.util.Date());	//보내는 날짜 설정
			msg.setContent(content,"text/html;charset=utf-8");	// 내용 설정
			
			Transport.send(msg); //메일 보내기
			
		}catch(MessagingException e1) {
			e1.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		return authNum;
	}
}
		
