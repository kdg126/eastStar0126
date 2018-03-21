package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.vo.Faq;

public class FaqDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static FaqDao instance = new FaqDao();
	
	private FaqDao() {}
	
	public static FaqDao getInstance() {
		return instance;
	}
	
	public ArrayList<Faq> getFaqList() {
		
		ArrayList<Faq> faqList = null;
		String query = "SELECT * FROM faqbbs";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);			
			rs = pstmt.executeQuery();
			faqList = new ArrayList<Faq>();
			while(rs.next()) {
				Faq faq = new Faq();
				faq.setNo(rs.getInt("no"));
				faq.setTitle(rs.getString("title"));
				faq.setContent(rs.getString("content"));
				faq.setReadCount(rs.getInt("read_count"));
				faq.setPass(rs.getString("pass"));
				
				faqList.add(faq);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
				
			}catch (SQLException e) {  e.printStackTrace(); }
		}
		
		return faqList;		
	}
	
	
	public boolean isPassCheck(int no, String pass) {
		boolean isPass = false;
		String sqlPass = "SELECT pass FROM faqbbs WHERE no=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlPass);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isPass = rs.getString(1).equals(pass);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
				
			}catch (SQLException e) {
				
			}
		}
			return isPass;
		}
	
	
	public Faq getFaq(int no, boolean state) {
		
		String faqSql = "SELECT * FROM faqbbs WHERE no=?";
		String countSql = "UPDATE faqbbs set read_count = "
				+ "read_count + 1 WHERE no=?";
		
		Faq faq = null;  //faq 라는 이름의 변수
		
		try {
			
			conn = DBManager.getConnection();
			// 활성화된  Connection에 트랜잭션을 시작한다.
			DBManager.setAutoCommit( conn, false);
			
			if(state) {
				
				pstmt = conn.prepareStatement(countSql);
				
				pstmt.setInt(1, no);
				
				pstmt.executeUpdate();
			}
			
			pstmt = conn.prepareStatement(faqSql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				faq = new Faq();
				
				faq.setNo(rs.getInt("no"));
				faq.setTitle(rs.getString("title"));
				faq.setContent(rs.getString("content"));
				faq.setReadCount(rs.getInt("read_ount"));
				faq.setPass(rs.getString("pass"));
				
				DBManager.commit(conn);
			}
				
			}catch (Exception e) {
				DBManager.rollback(conn);
				
				System.out.println("FaqDao - getFaq(no, state)");
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return faq;
		}
	
	public void insertFaq(Faq faq) {
		
		String sqlInsert = "INSERT INTO faqbbs(no, title, content, read_count, pass)"
				+ "VALUES(faqbbs_seq.NEXTVAL, ?,?,0,?)";
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, faq.getTitle());
			pstmt.setString(2, faq.getContent());
			pstmt.setString(3, faq.getPass());
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (SQLException se) {
				
			}
		}
								
	}
	
	public void updateFaq(Faq faq) {
		
		String sqlUpdate = "UPDATE faqbbs set title=?, content=? WHERE no=?";
		
		try {
			conn = DBManager.getConnection();
		
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, faq.getTitle());
			pstmt.setString(2, faq.getContent());		
			pstmt.setInt(3, faq.getNo());
		
		    pstmt.executeUpdate();
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(conn != null)conn.close();
		}catch (SQLException e) {

		}
	}
	
  }
	public void deleteFaq(int no) {
		
		String sqlDelete = "DELETE FROM faqbbs WHERE no=?";
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (SQLException e) {
				
			}
		}
	}
}

