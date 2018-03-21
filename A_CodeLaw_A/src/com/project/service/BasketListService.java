package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.PaymentDao;
import com.project.model.ForwardService;
import com.project.vo.Basket;

public class BasketListService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 로그인 상태에서만 세션에 접속 가능하게 하기 위해 세션 구현
		HttpSession session = request.getSession();
		// 세션 영역에 isLogin이 존재하지 않으면 NullPointerException이 발생하기 때문에 먼저 null인지를 체크하고 형 변환
		boolean isLogin = session.getAttribute("isLogin_N") != null ? (Boolean) session.getAttribute("isLogin_N")
				: false;

		if (!isLogin) {
			/*
			 * 스트림에 직접 쓰기위해 응답 객체로 부터 스트림을 구한다. 응답 객체의 스트림을 구하기 전해 ContentType이 설정되야 한다. 그렇지
			 * 않으면 한글과 같은 데이터는 깨져서 출력된다.
			 **/
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("	alert('회원 전용 서비스입니다.\\n회원 로그인을 해주세요');");
			out.println("	history.back();");
			out.println("</script>");

			/*
			 * ForwardService가 null 이면 컨트롤러에서 뷰를 거치지 않고 그대로 응답되기 때문에 자바스크립트 구문이 클라이언트로 응답된다.
			 **/
			return null;
		}
		String id = (String) session.getAttribute("id");

		PaymentDao bDao = PaymentDao.getInstance();
		ArrayList<Basket> basketList = bDao.basketList(id);

		// 주문번호는 어떻게 ->
		long orderNo01 = new Date().getTime();
		String orderNo = "ship_" + orderNo01;
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("basketList", basketList);
		request.setAttribute("id", id);

		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=basket/basketList.jsp");
		return forward;
	}

}
