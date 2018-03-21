package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.SunjuDao;
import com.project.model.ForwardService;
import com.project.vo.Sunju;

public class MemberInfo1Service implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		SunjuDao dao = new SunjuDao();
		Sunju sunju = dao.getUserInfo(id);
		request.setAttribute("sunju", sunju);
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=sunju/myInfoModify01.jsp");
		return forward;
	}

	
}
