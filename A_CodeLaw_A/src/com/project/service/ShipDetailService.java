package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ShipDao;
import com.project.model.ForwardService;
import com.project.vo.Ship;

public class ShipDetailService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uploadDir = (String) request.getServletContext().getAttribute("uploadDir");
		int shipNo = Integer.valueOf(request.getParameter("shipNo"));
		String headcount = request.getParameter("headcount");

		System.out.println(shipNo);
		System.out.println(headcount);

		ShipDao dao = ShipDao.getInstance();
		Ship ship = dao.getShip(shipNo);

		ship.setFile1(ship.getFile1());
		ship.setFile2(ship.getFile2() != null ? ship.getFile2() : null);
		ship.setFile3(ship.getFile3() != null ? ship.getFile3() : null);
		ship.setFile4(ship.getFile4() != null ? ship.getFile4() : null);

		/*
		 * ship.setFile2( URLEncoder.encode(ship.getFile2() != null ? ship.getFile2() :
		 * uploadDir + "/noimage.jpg", "utf-8"));
		 */

		System.out.println("shipgetfile : " + ship.getFile1());

		request.setAttribute("ship", ship);
		request.setAttribute("headcount", headcount);

		System.out.println("shipNo : " + shipNo);

		System.out.println("shiplat :" + ship.getLat());
		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=ship/shipDetail.jsp");
		return forward;
	}

}
