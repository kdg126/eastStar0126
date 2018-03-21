package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.SunjuDao;
import com.project.model.ForwardService;

public class IdFindResult01Service implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		SunjuDao dao = new SunjuDao();
		String result = dao.idFind(name, phone);
		if (result.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('일치하는 정보가 없습니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("result", result);
		}
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=sunju/idFindResult01.jsp");
		
		return forward;
	}

	
}
