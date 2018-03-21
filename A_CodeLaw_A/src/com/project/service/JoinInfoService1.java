package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.ForwardService;
import com.project.vo.Sunju;

public class JoinInfoService1 implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sunju_code = request.getParameter("sunju_code");
		String pass = request.getParameter("pass");
		String phone = request.getParameter("phone");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		
		
		Sunju sunju = new Sunju();
		sunju.setId(id);
		sunju.setName(name);
		sunju.setSunju_code(sunju_code);
		sunju.setPass(pass);
		sunju.setPhone(phone);
		sunju.setEmail(email);
		
		request.setAttribute("sunju", sunju);
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=join/joinProcess2.jsp");
		
		return forward;
		
	}

	
}
