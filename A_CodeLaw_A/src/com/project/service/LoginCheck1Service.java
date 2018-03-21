package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.MemberDao;
import com.project.dao.SunjuDao;
import com.project.model.ForwardService;

public class LoginCheck1Service implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String memberCheck = request.getParameter("memberCheck");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		
		if (memberCheck.equals("normal")) {
			MemberDao dao = new MemberDao();
			String result = dao.loginCheck(id, pass);
			if (result.equals("")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디 또는 비밀번호가 틀립니다.');");
				out.println("window.history.back();");
				out.println("</script>");
				return null;
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("isLogin_N", true);
				session.setAttribute("id", result);
			}
			System.out.println("일반회원");
		}else if (memberCheck.equals("sunju")) {
			SunjuDao dao = new SunjuDao();
			String result = dao.loginCheck(id, pass);
			if (result.equals("")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디 또는 비밀번호가 틀립니다.');");
				out.println("window.history.back();");
				out.println("</script>");
				return null;
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("isLogin_S", true);
				session.setAttribute("id", result);
			}
			System.out.println("선주회원");
		}
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("index.mvc");
		return forward;
	}

	
}
