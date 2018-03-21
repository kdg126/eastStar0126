package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.vo.Basket;
import com.project.vo.Payment;

public class PaymentDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static PaymentDao instance = new PaymentDao();

	private PaymentDao() {
	}

	public static PaymentDao getInstance() {
		return instance;
	}

	// 모든 결제 내역 보기
	public ArrayList<Payment> paymentList(String userId) {
		ArrayList<Payment> paymentList = null;
		String sql = "select * from payment where userId = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				paymentList = new ArrayList<Payment>();

				do {
					Payment payment = new Payment();
					payment.setPaymentNo(rs.getInt("paymentNo"));
					payment.setUserId(rs.getString("userId"));
					payment.setProductNo(rs.getInt("productNo"));
					payment.setPrice(rs.getString("price"));
					payment.setProductName(rs.getString("productName"));
					payment.setPaymentFile(rs.getString("paymentfile"));
					payment.setRegDate(rs.getDate("regdate"));

					paymentList.add(payment);

				} while (rs.next());

			}
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return paymentList;
	}

	// 결제 리스트에서 하나 보는 창(detail)
	public Payment getPayment(int productNo) {
		String sql = "select * from payment where productno = ?";
		Payment payment = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				payment = new Payment();
				payment.setPaymentNo(rs.getInt("paymentNo"));
				payment.setUserId(rs.getString("userId"));
				payment.setProductNo(rs.getInt("productNo"));
				payment.setPrice(rs.getString("price"));
				payment.setProductName(rs.getString("productName"));
				payment.setPaymentFile(rs.getString("paymentfile"));
				payment.setRegDate(rs.getDate("regdate"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return payment;
	}

	// 결제 승인 창
	public void addPayment(ArrayList<Basket> basketList) {
		String sql = "insert into payment values(payment_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		String allDelete = "DELETE FROM basket WHERE userId = ?";

		try {
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);

			// 주문정보 추가
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < basketList.size(); i++) {
				pstmt.setString(1, basketList.get(i).getUserId());
				pstmt.setInt(2, basketList.get(i).getShipNo());
				pstmt.setString(3, basketList.get(i).getPrice());
				pstmt.setString(4, null);
				pstmt.setString(5, basketList.get(i).getProductName());
				pstmt.setString(6, basketList.get(i).getBasketFile());
				pstmt.setDate(7, basketList.get(i).getRegDate());

				pstmt.executeUpdate();
			}

			// 장바구니 비우기
			pstmt = conn.prepareStatement(allDelete);
			pstmt.setString(1, basketList.get(0).getUserId());

			pstmt.executeUpdate();

			DBManager.commit(conn);
		} catch (Exception e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public ArrayList<Basket> basketList(String userId) {
		ArrayList<Basket> basketList = null;
		Basket basket = null;
		String sql = "select * from basket where userId = ?";
		try {
			conn = DBManager.getConnection();

			DBManager.setAutoCommit(conn, false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			basketList = new ArrayList<Basket>();
			while (rs.next()) {
				basket = new Basket();
				basket.setBasketNo(rs.getInt("basketNo"));
				basket.setUserId(rs.getString("userId"));
				basket.setShipNo(rs.getInt("shipno"));
				basket.setProductName(rs.getString("productName"));
				basket.setPrice(rs.getString("price"));
				basket.setBasketFile(rs.getString("basketFile"));
				basket.setRegDate(rs.getDate("regdate"));

				basketList.add(basket);
			}
			DBManager.commit(conn);
		} catch (Exception e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		System.out.println("basket dao : " + basketList.size());
		return basketList;
	}

	public void basketAdd(Basket basket) {
		String sql = "insert into basket(basketNo, userId, shipno, productName, price, basketfile, regdate)"
				+ " values(basket_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basket.getUserId());
			pstmt.setInt(2, basket.getShipNo());
			pstmt.setString(3, basket.getProductName());
			pstmt.setString(4, basket.getPrice());
			pstmt.setString(5, basket.getBasketFile());
			pstmt.setDate(6, basket.getRegDate());

			pstmt.executeUpdate();
			DBManager.commit(conn);
		} catch (Exception e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void basketDelete(String userId, int basketNo, boolean isAll) {

		String sql = "delete from basket where userId = ? and basketNo = ?";
		String allDelete = "DELETE FROM basket WHERE userId = ?";

		try {
			conn = DBManager.getConnection();

			if (isAll) {
				pstmt = conn.prepareStatement(allDelete);
				pstmt.setString(1, userId);

			} else {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				pstmt.setInt(2, basketNo);
			}

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void paymentDelete(String userId, int paymentNo) {

		String sql = "delete from basket where userId = ? and basketNo = ?";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, paymentNo);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
}
