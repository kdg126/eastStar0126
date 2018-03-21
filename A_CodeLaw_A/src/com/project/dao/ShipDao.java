package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.vo.Ship;

public class ShipDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static ShipDao instance = new ShipDao();

	private ShipDao() {
	}

	public static ShipDao getInstance() {
		return instance;
	}

	public int getShipCount() {

		String sqlCount = "SELECT COUNT(*) FROM ship";
		int count = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}

	// 지역, 항구, 인원 검색 조건에 따른 작성글의 수를 계산하기 위해 호출되는 메소드
	public int getShipCount(String province, String port1, int headcount) {
		System.out.println("province : " + province + ", port1 : " + port1 + ", headcount : " + headcount);

		String sqlCount = "select count(*) from (select * from  (select * from ship where province =?)"
				+ "where port1 = ?) where minPeople <= ? and maxPeople >= ?";

		int count = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1, province);
			pstmt.setString(2, port1);
			pstmt.setInt(3, headcount);
			pstmt.setInt(4, headcount);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}

	// 아이디 체크 메소드
	public boolean isIdCheck(int shipNo, String userId) {
		boolean isIdCheck = false;
		String sql = "Select sunjuId from ship where shipno = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shipNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isIdCheck = rs.getString(1).equals(userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return isIdCheck;
	}

	// 한 페이지에 보여질 글 리스트 요청시 해당하는 게시글 리스트 읽어오는 메소드
	public ArrayList<Ship> shipList() {

		// int startRow, int endRow 이거 파라미터로 넣을꺼면 아래 쿼리
		/*
		 * String sqlShipList =
		 * "select * from (select rownum num, shipno, province, port1, shipname, price, maxpeople, minpeople, flatfish, "
		 * +
		 * "squid, rockfish, octopus, whale, seabream, mackerel, mullet, shart, greenling, item1, item2, item3, item4, "
		 * +
		 * "facility1, facility2, facility3, facility4, file1, file2, file3, file4 from (select * from ship order by shipno desc)) "
		 * + "where num >= ? and num <= ?";
		 */

		String sqlShipList = "select * from ship order by shipno desc";

		ArrayList<Ship> shipList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlShipList);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				shipList = new ArrayList<Ship>();

				do {
					Ship ship = new Ship();
					ship.setShipNo(rs.getInt("shipno"));
					ship.setArea(rs.getString("province"));
					ship.setPort(rs.getString("port1"));
					ship.setShipName(rs.getString("shipname"));
					ship.setPrice(rs.getString("price"));
					ship.setMaxPeople(rs.getInt("maxpeople"));
					ship.setMinPeople(rs.getInt("minpeople"));
					ship.setFlatFish(rs.getString("flatfish"));
					ship.setSquid(rs.getString("squid"));
					ship.setRockFish(rs.getString("rockfish"));
					ship.setOctopus(rs.getString("octopus"));
					ship.setWhale(rs.getString("whale"));
					ship.setSeabream(rs.getString("seabream"));
					ship.setMackerel(rs.getString("mackerel"));
					ship.setMullet(rs.getString("mullet"));
					ship.setShark(rs.getString("shark"));
					ship.setGreenling(rs.getString("greenling"));
					ship.setItem1(rs.getString("item1"));
					ship.setItem2(rs.getString("item2"));
					ship.setItem3(rs.getString("item3"));
					ship.setItem4(rs.getString("item4"));
					ship.setFacility1(rs.getString("facility1"));
					ship.setFacility2(rs.getString("facility2"));
					ship.setFacility3(rs.getString("facility3"));
					ship.setFacility4(rs.getString("facility4"));
					ship.setFile1(rs.getString("file1"));
					ship.setFile2(rs.getString("file2"));
					ship.setFile3(rs.getString("file3"));
					ship.setFile4(rs.getString("file4"));
					ship.setSunjuId(rs.getString("sunjuid"));
					ship.setLat(rs.getString("lat"));
					ship.setLng(rs.getString("lng"));

					shipList.add(ship);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return shipList;
	}

	// 게시글 상세보기 시 호출되는 메소드
	public Ship getShip(int shipNo) {
		String boardSql = "SELECT * FROM ship WHERE shipno=?";
		Ship ship = null;

		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(boardSql);

			pstmt.setInt(1, shipNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ship = new Ship();
				ship.setShipNo(rs.getInt("shipno"));
				ship.setArea(rs.getString("province"));
				ship.setPort(rs.getString("port1"));
				ship.setShipName(rs.getString("shipname"));
				ship.setPrice(rs.getString("price"));
				ship.setMaxPeople(rs.getInt("maxpeople"));
				ship.setMinPeople(rs.getInt("minpeople"));
				ship.setFlatFish(rs.getString("flatfish"));
				ship.setSquid(rs.getString("squid"));
				ship.setRockFish(rs.getString("rockfish"));
				ship.setOctopus(rs.getString("octopus"));
				ship.setWhale(rs.getString("whale"));
				ship.setSeabream(rs.getString("seabream"));
				ship.setMackerel(rs.getString("mackerel"));
				ship.setMullet(rs.getString("mullet"));
				ship.setShark(rs.getString("shark"));
				ship.setGreenling(rs.getString("greenling"));
				ship.setItem1(rs.getString("item1"));
				ship.setItem2(rs.getString("item2"));
				ship.setItem3(rs.getString("item3"));
				ship.setItem4(rs.getString("item4"));
				ship.setFacility1(rs.getString("facility1"));
				ship.setFacility2(rs.getString("facility2"));
				ship.setFacility3(rs.getString("facility3"));
				ship.setFacility4(rs.getString("facility4"));
				ship.setFile1(rs.getString("file1"));
				ship.setFile2(rs.getString("file2"));
				ship.setFile3(rs.getString("file3"));
				ship.setFile4(rs.getString("file4"));
				ship.setSunjuId(rs.getString("sunjuid"));
				ship.setLat(rs.getString("lat"));
				ship.setLng(rs.getString("lng"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return ship;
	}

	// 글쓰기 요청 시 호출되는 메소드
	public void insertShip(Ship ship) {

		String sqlInsert = "INSERT INTO ship(shipno, province, port1, shipname, price, maxpeople, minpeople, flatfish, "
				+ "squid, rockfish, octopus, whale, seabream, mackerel, mullet, shark, greenling, item1, item2, item3, item4, "
				+ "facility1, facility2, facility3, facility4, file1, file2, file3, file4, sunjuid, lat, lng)"
				+ " VALUES(ship_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, ship.getArea());
			pstmt.setString(2, ship.getPort());
			pstmt.setString(3, ship.getShipName());
			pstmt.setString(4, ship.getPrice());
			pstmt.setInt(5, ship.getMaxPeople());
			pstmt.setInt(6, ship.getMinPeople());
			pstmt.setString(7, ship.getFlatFish());
			pstmt.setString(8, ship.getSquid());
			pstmt.setString(9, ship.getRockFish());
			pstmt.setString(10, ship.getOctopus());
			pstmt.setString(11, ship.getWhale());
			pstmt.setString(12, ship.getSeabream());
			pstmt.setString(13, ship.getMackerel());
			pstmt.setString(14, ship.getMullet());
			pstmt.setString(15, ship.getShark());
			pstmt.setString(16, ship.getGreenling());
			pstmt.setString(17, ship.getItem1());
			pstmt.setString(18, ship.getItem2());
			pstmt.setString(19, ship.getItem3());
			pstmt.setString(20, ship.getItem4());
			pstmt.setString(21, ship.getFacility1());
			pstmt.setString(22, ship.getFacility2());
			pstmt.setString(23, ship.getFacility3());
			pstmt.setString(24, ship.getFacility4());
			pstmt.setString(25, ship.getFile1());
			pstmt.setString(26, ship.getFile2());
			pstmt.setString(27, ship.getFile3());
			pstmt.setString(28, ship.getFile4());
			pstmt.setString(29, ship.getSunjuId());
			pstmt.setString(30, ship.getLat());
			pstmt.setString(31, ship.getLng());

			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 글 수정 메소드
	public void updateShip(Ship ship, int shipNo, String userId) {

		String sqlFileUpdate = "UPDATE ship set province=?, port1=?, shipname=?, price=?, maxpeople=?, minpeople=?, "
				+ "flatfish=?, squid=?, rockfish=?, octopus=?, whale=?, seabream=?, mackerel=?, mullet=?, shark=?, "
				+ "greenling=?, item1=?, item2=?, item3=?, item4=?, facility1=?, facility2=?, facility3=?, facility4=?, "
				+ "file1=?, file2=?, file3=?, file4=?, lat=?, lng=? WHERE shipno=? and sunjuid = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlFileUpdate);
			pstmt.setString(1, ship.getArea());
			pstmt.setString(2, ship.getPort());
			pstmt.setString(3, ship.getShipName());
			pstmt.setString(4, ship.getPrice());
			pstmt.setInt(5, ship.getMaxPeople());
			pstmt.setInt(6, ship.getMinPeople());
			pstmt.setString(7, ship.getFlatFish());
			pstmt.setString(8, ship.getSquid());
			pstmt.setString(9, ship.getRockFish());
			pstmt.setString(10, ship.getOctopus());
			pstmt.setString(11, ship.getWhale());
			pstmt.setString(12, ship.getSeabream());
			pstmt.setString(13, ship.getMackerel());
			pstmt.setString(14, ship.getMullet());
			pstmt.setString(15, ship.getShark());
			pstmt.setString(16, ship.getGreenling());
			pstmt.setString(17, ship.getItem1());
			pstmt.setString(18, ship.getItem2());
			pstmt.setString(19, ship.getItem3());
			pstmt.setString(20, ship.getItem4());
			pstmt.setString(21, ship.getFacility1());
			pstmt.setString(22, ship.getFacility2());
			pstmt.setString(23, ship.getFacility3());
			pstmt.setString(24, ship.getFacility4());
			pstmt.setString(25, ship.getFile1());
			pstmt.setString(26, ship.getFile2());
			pstmt.setString(27, ship.getFile3());
			pstmt.setString(28, ship.getFile4());
			pstmt.setString(29, ship.getLat());
			pstmt.setString(30, ship.getLng());
			pstmt.setInt(31, shipNo);
			pstmt.setString(32, userId);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 게시글 삭제 메소드
	public void deleteShip(int shipNo, String sunjuId) {

		String sqlDelete = "DELETE FROM ship WHERE shipno=? and sunjuid = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, shipNo);
			pstmt.setString(2, sunjuId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 지역, 항구, 인원 검색 조건에 따른 게시글 리스트 반환하는 메소드
	public ArrayList<Ship> searchList(String province, String port1, int headcount) {

		// 이건 startRow랑 endRow 써서 rownum으로 받는 용. 아까우니 그냥 킵 ㅠㅜ
		/*
		 * String sqlSearchList =
		 * "select * from (select * from (select rownum num, shipno, province, port1, shipname, price, maxpeople, minpeople, flatfish, "
		 * +
		 * "squid, rockfish, octopus, whale, seabream, mackerel, mullet, shart, greenling, item1, item2, item3, item4, "
		 * +
		 * "facility1, facility2, facility3, facility4, file1, file2, file3, file4, sunjuid from  (select * from ship where province = "
		 * + province + ")" + "where port1 = " + port1 +
		 * ") where num>=? and num <=? )where minPeople <= " + headcount +
		 * " and maxPeople >= " + headcount;
		 */

		String sqlSearchList = "SELECT * FROM (SELECT * FROM (SELECT * FROM SHIP WHERE province = ?)"
				+ "where port1 = ?) where minpeople <= ? and maxpeople >= ?";

		ArrayList<Ship> shipList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);
			pstmt.setString(1, province);
			pstmt.setString(2, port1);
			pstmt.setInt(3, headcount);
			pstmt.setInt(4, headcount);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				shipList = new ArrayList<Ship>();

				do {
					Ship ship = new Ship();
					ship.setShipNo(rs.getInt("shipno"));
					ship.setArea(rs.getString("province"));
					ship.setPort(rs.getString("port1"));
					ship.setShipName(rs.getString("shipname"));
					ship.setPrice(rs.getString("price"));
					ship.setMaxPeople(rs.getInt("maxpeople"));
					ship.setMinPeople(rs.getInt("minpeople"));
					ship.setFlatFish(rs.getString("flatfish"));
					ship.setSquid(rs.getString("squid"));
					ship.setRockFish(rs.getString("rockfish"));
					ship.setOctopus(rs.getString("octopus"));
					ship.setWhale(rs.getString("whale"));
					ship.setSeabream(rs.getString("seabream"));
					ship.setMackerel(rs.getString("mackerel"));
					ship.setMullet(rs.getString("mullet"));
					ship.setShark(rs.getString("shark"));
					ship.setGreenling(rs.getString("greenling"));
					ship.setItem1(rs.getString("item1"));
					ship.setItem2(rs.getString("item2"));
					ship.setItem3(rs.getString("item3"));
					ship.setItem4(rs.getString("item4"));
					ship.setFacility1(rs.getString("facility1"));
					ship.setFacility2(rs.getString("facility2"));
					ship.setFacility3(rs.getString("facility3"));
					ship.setFacility4(rs.getString("facility4"));
					ship.setFile1(rs.getString("file1"));
					ship.setFile2(rs.getString("file2"));
					ship.setFile3(rs.getString("file3"));
					ship.setFile4(rs.getString("file4"));
					ship.setSunjuId(rs.getString("sunjuid"));
					ship.setLat(rs.getString("lat"));
					ship.setLng(rs.getString("lng"));

					shipList.add(ship);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return shipList;
	}

	// 지역 검색만 했을 때 검색결과 출력하는 메소드
	public ArrayList<Ship> searchList(String province) {

		String sqlSearchList = "SELECT * FROM SHIP WHERE PROVINCE = ?";

		ArrayList<Ship> shipList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);
			pstmt.setString(1, province);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				shipList = new ArrayList<Ship>();

				do {
					Ship ship = new Ship();
					ship.setShipNo(rs.getInt("shipno"));
					ship.setArea(rs.getString("province"));
					ship.setPort(rs.getString("port1"));
					ship.setShipName(rs.getString("shipname"));
					ship.setPrice(rs.getString("price"));
					ship.setMaxPeople(rs.getInt("maxpeople"));
					ship.setMinPeople(rs.getInt("minpeople"));
					ship.setFlatFish(rs.getString("flatfish"));
					ship.setSquid(rs.getString("squid"));
					ship.setRockFish(rs.getString("rockfish"));
					ship.setOctopus(rs.getString("octopus"));
					ship.setWhale(rs.getString("whale"));
					ship.setSeabream(rs.getString("seabream"));
					ship.setMackerel(rs.getString("mackerel"));
					ship.setMullet(rs.getString("mullet"));
					ship.setShark(rs.getString("shark"));
					ship.setGreenling(rs.getString("greenling"));
					ship.setItem1(rs.getString("item1"));
					ship.setItem2(rs.getString("item2"));
					ship.setItem3(rs.getString("item3"));
					ship.setItem4(rs.getString("item4"));
					ship.setFacility1(rs.getString("facility1"));
					ship.setFacility2(rs.getString("facility2"));
					ship.setFacility3(rs.getString("facility3"));
					ship.setFacility4(rs.getString("facility4"));
					ship.setFile1(rs.getString("file1"));
					ship.setFile2(rs.getString("file2"));
					ship.setFile3(rs.getString("file3"));
					ship.setFile4(rs.getString("file4"));
					ship.setSunjuId(rs.getString("sunjuid"));
					ship.setLat(rs.getString("lat"));
					ship.setLng(rs.getString("lng"));

					shipList.add(ship);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return shipList;
	}

	// 지역 항구 검색만 했을 때 검색결과 출력하는 메소드
	public ArrayList<Ship> searchList(String province, String port1) {

		String sqlSearchList = "select * from (SELECT * FROM SHIP WHERE province = ?) where port1 = ?";

		ArrayList<Ship> shipList = null;

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sqlSearchList);
			pstmt.setString(1, province);
			pstmt.setString(2, port1);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				shipList = new ArrayList<Ship>();

				do {
					Ship ship = new Ship();
					ship.setShipNo(rs.getInt("shipno"));
					ship.setArea(rs.getString("province"));
					ship.setPort(rs.getString("port1"));
					ship.setShipName(rs.getString("shipname"));
					ship.setPrice(rs.getString("price"));
					ship.setMaxPeople(rs.getInt("maxpeople"));
					ship.setMinPeople(rs.getInt("minpeople"));
					ship.setFlatFish(rs.getString("flatfish"));
					ship.setSquid(rs.getString("squid"));
					ship.setRockFish(rs.getString("rockfish"));
					ship.setOctopus(rs.getString("octopus"));
					ship.setWhale(rs.getString("whale"));
					ship.setSeabream(rs.getString("seabream"));
					ship.setMackerel(rs.getString("mackerel"));
					ship.setMullet(rs.getString("mullet"));
					ship.setShark(rs.getString("shark"));
					ship.setGreenling(rs.getString("greenling"));
					ship.setItem1(rs.getString("item1"));
					ship.setItem2(rs.getString("item2"));
					ship.setItem3(rs.getString("item3"));
					ship.setItem4(rs.getString("item4"));
					ship.setFacility1(rs.getString("facility1"));
					ship.setFacility2(rs.getString("facility2"));
					ship.setFacility3(rs.getString("facility3"));
					ship.setFacility4(rs.getString("facility4"));
					ship.setFile1(rs.getString("file1"));
					ship.setFile2(rs.getString("file2"));
					ship.setFile3(rs.getString("file3"));
					ship.setFile4(rs.getString("file4"));
					ship.setSunjuId(rs.getString("sunjuid"));
					ship.setLat(rs.getString("lat"));
					ship.setLng(rs.getString("lng"));

					shipList.add(ship);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return shipList;
	}

	// 인원 검색만 했을 때 검색결과 출력하는 메소드
	public ArrayList<Ship> searchList(int headcount) {

		String sqlSearchList = "SELECT * FROM SHIP where minpeople <= ? and maxpeople >= ?";

		ArrayList<Ship> shipList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);
			pstmt.setInt(1, headcount);
			pstmt.setInt(2, headcount);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				shipList = new ArrayList<Ship>();

				do {
					Ship ship = new Ship();
					ship.setShipNo(rs.getInt("shipno"));
					ship.setArea(rs.getString("province"));
					ship.setPort(rs.getString("port1"));
					ship.setShipName(rs.getString("shipname"));
					ship.setPrice(rs.getString("price"));
					ship.setMaxPeople(rs.getInt("maxpeople"));
					ship.setMinPeople(rs.getInt("minpeople"));
					ship.setFlatFish(rs.getString("flatfish"));
					ship.setSquid(rs.getString("squid"));
					ship.setRockFish(rs.getString("rockfish"));
					ship.setOctopus(rs.getString("octopus"));
					ship.setWhale(rs.getString("whale"));
					ship.setSeabream(rs.getString("seabream"));
					ship.setMackerel(rs.getString("mackerel"));
					ship.setMullet(rs.getString("mullet"));
					ship.setShark(rs.getString("shark"));
					ship.setGreenling(rs.getString("greenling"));
					ship.setItem1(rs.getString("item1"));
					ship.setItem2(rs.getString("item2"));
					ship.setItem3(rs.getString("item3"));
					ship.setItem4(rs.getString("item4"));
					ship.setFacility1(rs.getString("facility1"));
					ship.setFacility2(rs.getString("facility2"));
					ship.setFacility3(rs.getString("facility3"));
					ship.setFacility4(rs.getString("facility4"));
					ship.setFile1(rs.getString("file1"));
					ship.setFile2(rs.getString("file2"));
					ship.setFile3(rs.getString("file3"));
					ship.setFile4(rs.getString("file4"));
					ship.setSunjuId(rs.getString("sunjuid"));
					ship.setLat(rs.getString("lat"));
					ship.setLng(rs.getString("lng"));

					shipList.add(ship);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return shipList;
	}
}
