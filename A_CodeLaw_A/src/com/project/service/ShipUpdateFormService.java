package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.ShipDao;
import com.project.model.ForwardService;
import com.project.vo.Ship;

public class ShipUpdateFormService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String shipNo = request.getParameter("detail_shipNo");
		String userId = (String) session.getAttribute("id");

		// if (shipNo == null || shipNo.equals("") || pageNum == null ||
		// pageNum.equals("")) {
		//
		// response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		//
		// out.println("<script>");
		// out.println(" alert('정상적인 접근이 아닙니다.');");
		// out.println(" history.back();");
		// out.println("</script>");
		//
		// return null;
		// }

		int no = Integer.parseInt(shipNo);

		ShipDao dao = ShipDao.getInstance();
		boolean isIdCheck = dao.isIdCheck(no, userId);

		if (!isIdCheck) {
			/*
			 * 스트림에 직접 쓰기위해 응답 객체로 부터 스트림을 구한다. 응답 객체의 스트림을 구하기 전해 ContentType이 설정되야 한다. 그렇지
			 * 않으면 한글과 같은 데이터는 깨져서 출력된다.
			 **/
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('수정 할 권한이 없습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}

		Ship ship = dao.getShip(no);

		request.setAttribute("ship", ship);
		// request.setAttribute("pageNum", pageNum);

		ForwardService forward = new ForwardService();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/index.jsp?body=ship/shipUpdateForm.jsp");
		return forward;
	}

}
