package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.SunjuDao;
import com.project.model.ForwardService;

public class OverlapIdCheckService1 implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		SunjuDao dao = new SunjuDao();
		boolean result = dao.idCheck(id);
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
		
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/join/overlapIdCheck1.jsp");
		return forward;
	}

	
}
