package com.project.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.PaymentDao;
import com.project.model.ForwardService;
import com.project.vo.Basket;
import com.project.vo.Payment;

public class PaymentFinishService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// 주문번호
		String orderNo = request.getParameter("orderNo");

		// 먼저 아이디에 해당하는 장바구니 리스트를 가져와서
		// 이 데이터를 바탕으로 주문테이블에 주문정보를 추가
		PaymentDao dao = PaymentDao.getInstance();
		ArrayList<Basket> basketList = dao.basketList(id);
		dao.addPayment(basketList);

		ArrayList<Payment> paymentList = dao.paymentList(id);

		request.setAttribute("orderNo", orderNo);
		request.setAttribute("orderOk", "결제 성공");
		request.setAttribute("paymentList", paymentList);

		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=payment/paymentFinish.jsp");
		return forward;
	}

}
