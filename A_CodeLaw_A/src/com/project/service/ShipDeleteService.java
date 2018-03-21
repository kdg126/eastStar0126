package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.ShipDao;
import com.project.model.ForwardService;

public class ShipDeleteService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		ShipDao dao = ShipDao.getInstance();

		String shipNo = request.getParameter("detail_shipNo");
		String userId = (String) session.getAttribute("id");

		int no = Integer.parseInt(shipNo);

		boolean isIdCheck = dao.isIdCheck(no, userId);
		if (!isIdCheck) {
			System.out.println("유저가 다른 사람임");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('수정 할 수 있는 권한이 없습니다.');");
			sb.append("	history.back();");
			sb.append("</script>");
			out.println(sb.toString());
			return null;
		}

		dao.deleteShip(no, userId);

		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("newShipList.mvc");
		return forward;
	}

}
