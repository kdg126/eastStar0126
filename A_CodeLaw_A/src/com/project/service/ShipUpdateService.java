package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.dao.ShipDao;
import com.project.model.ForwardService;
import com.project.vo.Ship;

public class ShipUpdateService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);
		ShipDao dao = ShipDao.getInstance();
		Ship ship = null;

		String uploadDir = (String) request.getServletContext().getAttribute("uploadDir");
		String realPath = request.getServletContext().getRealPath(uploadDir);

		int maxFileSize = 10 * 1024 * 1024;

		String encoding = "UTF-8";

		MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding,
				new DefaultFileRenamePolicy());

		String shipNo = multi.getParameter("updateShipNo");
		String area = multi.getParameter("province1");
		String port = multi.getParameter("port2");
		String shipName = multi.getParameter("uShipName");
		String price = multi.getParameter("uPrice");
		int maxPeople = Integer.valueOf(multi.getParameter("uMaxPeople"));
		int minPeople = Integer.valueOf(multi.getParameter("uMinPeople"));
		String flatFish = multi.getParameter("flatFish");
		String squid = multi.getParameter("squid");
		String rockFish = multi.getParameter("rockFish");
		String octopus = multi.getParameter("octopus");
		String whale = multi.getParameter("whale");
		String seabream = multi.getParameter("seabream");
		String mackerel = multi.getParameter("mackerel");
		String mullet = multi.getParameter("mullet");
		String shark = multi.getParameter("shark");
		String greenling = multi.getParameter("greenling");
		String item1 = multi.getParameter("uItem1");
		String item2 = multi.getParameter("uItem2");
		String item3 = multi.getParameter("uItem3");
		String item4 = multi.getParameter("uItem4");
		String facility1 = multi.getParameter("uFacility1");
		String facility2 = multi.getParameter("uFacility2");
		String facility3 = multi.getParameter("uFacility3");
		String facility4 = multi.getParameter("uFacility4");
		String lat = multi.getParameter("ulat");
		String lng = multi.getParameter("ulng");

		String userId = (String) session.getAttribute("id");

		System.out.println("넘버 나와라");
		int no = Integer.parseInt(multi.getParameter("updateShipNo"));

		System.out.println("배업뎃서비스5 : " + no);
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

		System.out.println("배업뎃서비스6");
		ship = new Ship();
		ship.setSunjuId(userId);
		ship.setShipNo(no);
		ship.setArea(area);
		ship.setPort(port);
		ship.setShipName(shipName);
		ship.setPrice(price);
		ship.setMaxPeople(Integer.valueOf(maxPeople));
		ship.setMinPeople(minPeople);
		ship.setFlatFish(flatFish);
		ship.setSquid(squid);
		ship.setRockFish(rockFish);
		ship.setOctopus(octopus);
		ship.setWhale(whale);
		ship.setSeabream(seabream);
		ship.setMackerel(mackerel);
		ship.setMullet(mullet);
		ship.setShark(shark);
		ship.setGreenling(greenling);
		ship.setItem1(item1);
		ship.setItem2(item2);
		ship.setItem3(item3);
		ship.setItem4(item4 != null ? item4 : null);
		ship.setFacility1(facility1 != null ? facility1 : null);
		ship.setFacility2(facility2 != null ? facility2 : null);
		ship.setFacility3(facility3 != null ? facility3 : null);
		ship.setFacility4(facility4 != null ? facility4 : null);
		ship.setLat(lat);
		ship.setLng(lng);

		String fileName1 = multi.getFilesystemName("uFile1");
		String fileName2 = multi.getFilesystemName("uFile2");
		String fileName3 = multi.getFilesystemName("uFile3");
		String fileName4 = multi.getFilesystemName("uFile4");
		System.out.println("업로드된 파일 : " + fileName1 + ", " + fileName2 + ", " + fileName3 + ", " + fileName4);

		ship.setFile1(fileName1 != null ? uploadDir + "/" + fileName1 : null);
		ship.setFile2(fileName2 != null ? uploadDir + "/" + fileName2 : null);
		ship.setFile3(fileName3 != null ? uploadDir + "/" + fileName3 : null);
		ship.setFile4(fileName4 != null ? uploadDir + "/" + fileName4 : null);

		dao.updateShip(ship, no, userId);

		System.out.println("lat : " + lat + "lng : " + lng);
		System.out.println("ship:" + ship + "shipNo : " + shipNo + "userId : " + userId);
		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("newShipList.mvc");
		return forward;
	}
}
