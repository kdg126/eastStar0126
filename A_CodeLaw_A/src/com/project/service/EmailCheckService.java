package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.EmailConfirm;
import com.project.model.ForwardService;

public class EmailCheckService implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		EmailConfirm confirm = new EmailConfirm();
		String authNum = confirm.connectEmail(email);
		
		request.setAttribute("authNum", authNum);
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/join/emailCheck.jsp");
		return forward;
	}

	
}
