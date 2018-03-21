package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.MemberDao;
import com.project.model.ForwardService;

public class IdFindResultService implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		MemberDao dao = new MemberDao();
		String result = dao.idFind(name, phone);
		
		
		
		if (result.equals(null) || result.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('일치하는 정보가 없습니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			return null;
		}else {
			
			
			request.setAttribute("result", result);
		}
		
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=login/idFindResult.jsp");
		
		return forward;
	}

	
}
