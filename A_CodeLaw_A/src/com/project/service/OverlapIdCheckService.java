package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.MemberDao;
import com.project.model.ForwardService;

public class OverlapIdCheckService implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		boolean result = dao.idCheck(id);
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
		
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/join/overlapIdCheck.jsp");
		return forward;
	}

	
}
