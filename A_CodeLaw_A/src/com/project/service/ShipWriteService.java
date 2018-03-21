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

public class ShipWriteService implements CommandProcess {

	@Override
	public ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String sunjuId = (String) session.getAttribute("id");

		boolean isLogin = session.getAttribute("isLogin_S") != null ? (Boolean) session.getAttribute("isLogin_S")
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

		String uploadDir = (String) request.getServletContext().getAttribute("uploadDir");
		String realPath = request.getServletContext().getRealPath(uploadDir);

		int maxFileSize = 100 * 1024 * 1024;

		String encoding = "UTF-8";

		MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding,
				new DefaultFileRenamePolicy());

		System.out.println("3번");
		String area = multi.getParameter("province1");
		String port = multi.getParameter("port2");
		String shipName = multi.getParameter("shipName");
		String price = multi.getParameter("price");
		int maxPeople = Integer.valueOf(multi.getParameter("maxPeople"));
		int minPeople = Integer.valueOf(multi.getParameter("minPeople"));
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
		String item1 = multi.getParameter("item1");
		String item2 = multi.getParameter("item2");
		String item3 = multi.getParameter("item3");
		String item4 = multi.getParameter("item4");
		String facility1 = multi.getParameter("facility1");
		String facility2 = multi.getParameter("facility2");
		String facility3 = multi.getParameter("facility3");
		String facility4 = multi.getParameter("facility4");
		String lat = multi.getParameter("lat");
		String lng = multi.getParameter("lng");

		System.out.println(area);
		System.out.println(port);
		System.out.println(shipName);
		System.out.println(price);
		System.out.println(maxPeople);
		System.out.println(minPeople);
		System.out.println(flatFish);
		System.out.println(squid);
		System.out.println(rockFish);
		System.out.println(octopus);
		System.out.println(whale);
		System.out.println(seabream);
		System.out.println(mackerel);
		System.out.println(mullet);
		System.out.println(shark);
		System.out.println(greenling);
		System.out.println(item1);
		System.out.println(item2);
		System.out.println(item3);
		System.out.println(item4);
		System.out.println(facility1);
		System.out.println(facility2);
		System.out.println(facility3);
		System.out.println(facility4);

		System.out.println("4번");
		Ship ship = new Ship();
		ship.setArea(area);
		ship.setPort(port);
		ship.setShipName(shipName);
		ship.setPrice(price);
		ship.setMaxPeople(maxPeople);
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
		ship.setItem1(item1 != null ? item1 : null);
		ship.setItem2(item2 != null ? item2 : null);
		ship.setItem3(item3 != null ? item3 : null);
		ship.setItem4(item4 != null ? item4 : null);
		ship.setFacility1(facility1 != null ? facility1 : null);
		ship.setFacility2(facility2 != null ? facility2 : null);
		ship.setFacility3(facility3 != null ? facility3 : null);
		ship.setFacility4(facility4 != null ? facility4 : null);
		ship.setSunjuId(sunjuId);
		ship.setLat(lat);
		ship.setLng(lng);

		System.out.println("5번");
		String fileName1 = multi.getFilesystemName("file1");
		String fileName2 = multi.getFilesystemName("file2");
		String fileName3 = multi.getFilesystemName("file3");
		String fileName4 = multi.getFilesystemName("file4");
		System.out.println("업로드된 파일 : " + fileName1 + ", " + fileName2 + ", " + fileName3 + ", " + fileName4);

		ship.setFile1(fileName1 != null ? uploadDir + "/" + fileName1 : null);
		ship.setFile2(fileName2 != null ? uploadDir + "/" + fileName2 : null);
		ship.setFile3(fileName3 != null ? uploadDir + "/" + fileName3 : null);
		ship.setFile4(fileName4 != null ? uploadDir + "/" + fileName4 : null);

		ShipDao dao = ShipDao.getInstance();
		dao.insertShip(ship);

		ForwardService forward = new ForwardService();
		forward.setRedirect(true);
		forward.setPath("newShipList.mvc");
		return forward;
	}

}
