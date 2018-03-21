package com.project.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ShipDao;
import com.project.model.ForwardService;
import com.project.vo.Ship;

public class ShipSearchListService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String headcount = request.getParameter("headcount");
		String province = request.getParameter("province");
		String port1 = request.getParameter("port1");

		System.out.println(province);
		System.out.println(port1);
		System.out.println(headcount);

		ArrayList<Ship> shipSearchList = null;
		ShipDao dao = ShipDao.getInstance();

		boolean searchOption = (province.equals("0") && port1.equals("0")
				&& (headcount == null || headcount.equals(""))) ? false : true;

		if (headcount.equals("") || headcount == null) {
			headcount = "0";
		}

		if (!searchOption) {
			// 전체 게시 글 수를 구한다.
			shipSearchList = dao.shipList();

		} else if (searchOption && (province != "0" && headcount == "0" && port1 == "0")) {
			// 지역만 선택해서 검색할 때
			shipSearchList = dao.searchList(province);

		} else if (province == "0" && headcount != "0" && port1 == "0") {
			// 인원만 선택해서 검색할 때
			int head = 0;
			head = Integer.parseInt(headcount);
			shipSearchList = dao.searchList(head);

		} else if (province != "0" && headcount != "0" && port1 != "0") {
			// 지역 항구 인원 다 선택해서 검색할 때
			int head = 0;
			head = Integer.parseInt(headcount);
			shipSearchList = dao.searchList(province, port1, head);

		} else if (province != "0" && port1 != "0" && headcount == "0") {
			// 지역 항구만 선택해서 검색할 때
			shipSearchList = dao.searchList(province, port1);
		}

		request.setAttribute("headcount", headcount);
		request.setAttribute("shipSearchList", shipSearchList);

		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=ship/shipSearchList.jsp");
		return forward;

	}

}
