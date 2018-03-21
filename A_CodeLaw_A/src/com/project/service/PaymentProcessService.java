package com.project.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.model.ForwardService;

public class PaymentProcessService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * PaymentDao dao = PaymentDao.getInstance(); ArrayList<Payment> paymentList =
		 * dao.paymentList();
		 * 
		 * request.setAttribute("paymentList", paymentList);
		 */
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		// 주문번호는 어떻게 ->
		long orderNo01 = new Date().getTime();
		String orderNo = "ship_" + orderNo01;
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("id", id);

		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/payment/paymentProcess.jsp");
		return forward;
	}

}
