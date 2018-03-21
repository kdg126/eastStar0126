package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.ForwardService;

public class JoinService2 implements CommandProcess{


	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String agree = request.getParameter("agree");
		
		if (agree.equals("false")) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('약관에 동의하셔야 회원으로 가입하실 수 있습니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			return null;
		}else if (agree.equals("true")) {
			request.setAttribute("agree", agree);
		} 
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=sunju/joinForm02.jsp");
		return forward;
	}

	
}

