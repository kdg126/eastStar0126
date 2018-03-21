package com.project.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ShipDao;
import com.project.model.ForwardService;
import com.project.vo.Ship;

public class NewShipListService implements CommandProcess {

	// private static final int PAGE_SIZE = 8;
	//
	// private static final int PAGE_GROUP = 5;

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int listCount = 0;
		ArrayList<Ship> shipList = null;
		String headcount = request.getParameter("headcount");
		String province = request.getParameter("province");
		String port1 = request.getParameter("port1");

		System.out.println(province);
		System.out.println(port1);
		System.out.println(headcount);

		ShipDao dao = ShipDao.getInstance();

		/*
		 * boolean allContent = (province == null && port1 == null && headcount ==null);
		 * boolean searchNone = (province.equals("0") && port1.equals("0") &&
		 * headcount.equals("")); boolean provinceOnly = (!province.equals("0") &&
		 * port1.equals("0") && headcount.equals("")); boolean headcountOnly =
		 * (province.equals("0") && port1.equals("0") && !headcount.equals("")); boolean
		 * searchAll = (!province.equals("0") && !port1.equals("0") &&
		 * !headcount.equals("")); boolean provincePort = (!province.equals("0") &&
		 * !port1.equals("0") && headcount.equals(""));
		 */

		if (province == null && port1 == null && headcount == null) {
			// 전체 게시 글 수를 구한다.
			shipList = dao.shipList();
			System.out.println("전부 : " + shipList);

		} else if (!province.equals("0") && port1.equals("0") && headcount.equals("")) {
			// 지역만 선택해서 검색할 때
			shipList = dao.searchList(province);

		} else if (province.equals("0") && port1.equals("0") && !headcount.equals("")) {
			// 인원만 선택해서 검색할 때
			int head = 0;
			head = Integer.parseInt(headcount);
			shipList = dao.searchList(head);
			System.out.println("인원만 : " + shipList);

		} else if (!province.equals("0") && !port1.equals("0") && !headcount.equals("")) {
			// 지역 항구 인원다 선택해서 검색할 때
			int head = 0;
			head = Integer.parseInt(headcount);
			shipList = dao.searchList(province, port1, head);
			System.out.println("지역 항구 인원 다 : " + shipList);

		} else if (!province.equals("0") && !port1.equals("0") && headcount.equals("")) {
			// 지역 항구만선택해서 검색할 때
			shipList = dao.searchList(province, port1);
			System.out.println("지역 항구 : " + shipList);
		} else if (province.equals("0") && port1.equals("0") && headcount.equals("")) {
			// 전체 게시 글 수를 구한다.
			shipList = dao.shipList();
			System.out.println("전부 : " + shipList);

		}

		System.out.println("1" + province);
		System.out.println("1" + port1);
		System.out.println("1" + headcount);
		System.out.println("1" + shipList);

		listCount = dao.getShipCount();

		request.setAttribute("listCount", listCount);
		request.setAttribute("shipList", shipList);
		request.setAttribute("headcount", headcount);

		/*
		 * request.setAttribute("provinceOnly", provinceOnly);
		 * request.setAttribute("provincePort", provincePort);
		 * request.setAttribute("headcountOnly", headcountOnly);
		 * request.setAttribute("searchAll", searchAll);
		 * request.setAttribute("allContent", allContent);
		 * request.setAttribute("searchNone", searchNone);
		 */

		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=ship/shipList.jsp");
		return forward;
	}

}
