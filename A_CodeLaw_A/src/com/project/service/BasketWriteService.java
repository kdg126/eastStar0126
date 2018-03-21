package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.PaymentDao;
import com.project.model.ForwardService;
import com.project.vo.Basket;

public class BasketWriteService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 로그인 상태에서만 세션에 접속 가능하게 하기 위해 세션 구현
		HttpSession session = request.getSession();

		// 세션 영역에 isLogin이 존재하지 않으면 NullPointerException이 발생하기 때문에 먼저 null인지를 체크하고 형 변환
		boolean isLogin = session.getAttribute("isLogin_N") != null ? (Boolean) session.getAttribute("isLogin_N")
				: false;

		if (!isLogin) {

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("	alert('회원 전용 서비스입니다.\\n회원 로그인을 해주세요');");
			out.println("	history.back();");
			out.println("</script>");

			return null;
		}

		String id = (String) session.getAttribute("id");
		String productName = request.getParameter("detail_shipName");
		String price = request.getParameter("detail_price");
		String strShipNo = request.getParameter("detail_shipNo");
		String basketFile = request.getParameter("detail_img");
		String strDate = request.getParameter("detail-regDate");
		System.out.println("strDate : " + strDate);
		int shipNo = Integer.parseInt(strShipNo);
		int year = Integer.parseInt(strDate.split("/")[2]);
		int month = Integer.parseInt(strDate.split("/")[0]);
		int day = Integer.parseInt(strDate.split("/")[1]);

		Date regDate = new Date(year - 1900, month - 1, day);

		Basket basket = new Basket();
		basket.setShipNo(shipNo);
		basket.setUserId(id);
		basket.setProductName(productName);
		basket.setPrice(price);
		basket.setBasketFile(basketFile);
		basket.setRegDate(regDate);

		// session.setAttribute("basket", basket);

		// 아래 productName은 배 이름 등록 될 때의 html의 name 들어가야 됨
		PaymentDao bdao = PaymentDao.getInstance();
		bdao.basketAdd(basket);

		System.out.println("장바구니 등록");
		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("basketList.mvc");
		return forward;

	}
}
