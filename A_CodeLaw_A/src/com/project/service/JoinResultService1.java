package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.SunjuDao;
import com.project.model.ForwardService;
import com.project.vo.Sunju;

public class JoinResultService1 implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Sunju sunju = (Sunju) session.getAttribute("sunju");
		SunjuDao dao = new SunjuDao();
		dao.insertSunju(sunju);
		
		session.removeAttribute("sunju");
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("index.mvc");
		
		return forward;
	}

	
}
