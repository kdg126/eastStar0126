package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.MemberDao;
import com.project.model.ForwardService;
import com.project.vo.Member;

public class MemberInfoService implements CommandProcess{

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		MemberDao dao = new MemberDao();
		Member member = dao.getUserInfo(id);
		request.setAttribute("member", member);
		
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=login/myInfoModify.jsp");
		return forward;
	}

	
}
