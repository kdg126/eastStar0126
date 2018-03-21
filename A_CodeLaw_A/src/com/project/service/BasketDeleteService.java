package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.PaymentDao;
import com.project.model.ForwardService;

public class BasketDeleteService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 로그인 상태에서만 세션에 접속 가능하게 하기 위해 세션 구현
		HttpSession session = request.getSession();
		// 세션 영역에 isLogin이 존재하지 않으면 NullPointerException이 발생하기 때문에 먼저 null인지를 체크하고 형 변환
		boolean isLogin = session.getAttribute("isLogin_N") != null ? (Boolean) session.getAttribute("isLogin_N")
				: false;

		String userId = (String) session.getAttribute("id");
		int basketNo = Integer.parseInt(request.getParameter("hidden_basketNo"));

		PaymentDao dao = PaymentDao.getInstance();
		dao.basketDelete(userId, basketNo, false);

		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("index.mvc");
		return forward;
	}

}
