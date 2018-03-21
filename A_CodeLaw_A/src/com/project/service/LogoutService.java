package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.model.ForwardService;

public class LogoutService implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("index.mvc");
		return forward;
	}

	
}
