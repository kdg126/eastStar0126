package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.SunjuDao;
import com.project.model.ForwardService;

public class MyInfoModify01Service implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		SunjuDao dao = new SunjuDao();
		dao.myInfoModify(id, name, phone, email);
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("index.mvc");
		return forward;
	}

	
}
