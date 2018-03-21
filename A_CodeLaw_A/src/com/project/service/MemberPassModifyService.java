package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.MemberDao;
import com.project.model.ForwardService;

public class MemberPassModifyService implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String pass1 = request.getParameter("pass1");
		String pass = request.getParameter("pass");
		
		MemberDao dao = new MemberDao();
		int result = dao.passCheck(id, pass1);
		
		if (result == 1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('기존의 비밀번호를 잘못 입력하셨습니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			return null;
		}else if (result == 0) {
			
			dao = new MemberDao();
			dao.passModify(id, pass);
		}
		
		
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("index.mvc");
		return forward;
	}

	
}
