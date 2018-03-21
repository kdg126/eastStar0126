package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.PaymentDao;
import com.project.model.ForwardService;

public class PaymentDeleteService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 세션 영역에 isLogin이 존재하지 않으면 NullPointerException이 발생하기 때문에 먼저 null인지를 체크하고 형 변환
		boolean isLogin = session.getAttribute("isLogin_N") != null ? (Boolean) session.getAttribute("isLogin_N")
				: false;

		String userId = (String) session.getAttribute("id");
		int paymentNo = Integer.parseInt(request.getParameter("payment-No"));

		PaymentDao dao = PaymentDao.getInstance();
		dao.paymentDelete(userId, paymentNo);

		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("index.mvc");
		return forward;
	}

}
