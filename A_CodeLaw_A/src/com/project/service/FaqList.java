package com.project.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.FaqDao;
import com.project.model.ForwardService;
import com.project.vo.Faq;

public class FaqList implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FaqDao dao = FaqDao.getInstance();
		ArrayList<Faq> faqList = dao.getFaqList();
		
		request.setAttribute("faqList", faqList);
		
		ForwardService forward = new ForwardService();	
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=faq/faqList.jsp");
		return forward;
	}
}
