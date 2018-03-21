package com.project.dao;


	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.project.vo.Sunju;

	public class SunjuDao {
		DataSource ds;
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		//연결
		public SunjuDao() {
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
				conn = ds.getConnection();
			} catch (NamingException e) {
				System.out.println("NamingException");
				e.printStackTrace();
				
			}catch (SQLException e) {
				System.out.println("SQLException");
				e.printStackTrace();
				
			}
			
		}
		//회원가입
		public void insertSunju(Sunju sunju) {
			String sql = "insert into sunju values(sunju_seq.nextval, ?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sunju.getId());
				pstmt.setString(2, sunju.getName());
				pstmt.setString(3, sunju.getSunju_code());
				pstmt.setString(4, sunju.getPass());
				pstmt.setString(5, sunju.getPhone());
				pstmt.setString(6, sunju.getEmail());
				
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("insertProduct-Exception");
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				try {
					
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					System.out.println("insertProduct-finally");
					e.printStackTrace();
					// TODO: handle exception
				}
			}
			}
		//로그인 체크
		public String loginCheck(String id, String pass) {
			String check = "select id from sunju where id=? and pass=?";
			
			String result = "";
			try {
				PreparedStatement pstmt = conn.prepareStatement(check);
				pstmt.setString(1, id);
				pstmt.setString(2, pass);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					
					
					result = rs.getString("id");
					
				}
				//11.23일
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
			
			return result;
		}
		//아이디 찾기
		public String idFind(String name, String phone) {
			String sql = "select id from sunju where name=? and phone=?";
			Sunju sunju = null;
			String result = "";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, name);
				pstmt.setString(2, phone);
				ResultSet rs = pstmt.executeQuery();
				
				
				if (rs.next()) {
					
					 sunju = new Sunju();
					
					sunju.setId(rs.getString("id"));
					
					result = sunju.getId();
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
			
			return result;
		}
		//비밀번호찾기
		public String passFind(String id, String name) {
			String sql = "select pass from sunju where id=? and name=?";
			Sunju sunju=null;
			String result="";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				
				
				if (rs.next()) {
					
					 sunju = new Sunju();
					
					
					sunju.setPass(rs.getString("pass"));
					result = sunju.getPass();
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
			
			return result;
		}
		//회원정보가져오기
		public Sunju getUserInfo(String id) {
			String sql = "select id, name, sunju_code, phone, email from sunju where id=?";
			Sunju sunju = null;
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
				
				
				if (rs.next()) {
					
					 sunju = new Sunju();
					
					 sunju.setId(rs.getString("id"));
					sunju.setName(rs.getString("name"));
					sunju.setSunju_code(rs.getString("sunju_code"));
					sunju.setPhone(rs.getString("phone"));
					sunju.setEmail(rs.getString("email"));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
			
			return sunju;
		}
		//회원 정보 수정하기
		public void myInfoModify(String id, String name, String phone, String email) {
			String modify = "update sunju set name=?, phone=?, email=? where id=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(modify);
				
				pstmt.setString(1, name);
				pstmt.setString(2, phone);
				pstmt.setString(3, email);
				pstmt.setString(4, id);
				
				pstmt.executeUpdate();
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
		}
		//비밀번호 체크하기
		public int passCheck(String id, String pass) {
			String passCheckSql = "select pass from sunju where id=? and pass=?";
			int result = -1;
			String password = "";
			try {
				PreparedStatement pstmt = conn.prepareStatement(passCheckSql);
				pstmt.setString(1, id);
				pstmt.setString(2, pass);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					password = rs.getString("pass");
				}
				if (password.equals(pass)) {
					result = 0;
				}else if (password.equals("")) {
					result = 1;
				}
			} catch (Exception e) {
				System.out.println("passCheck Dao");
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
			
			return result;
		}
		//비밀번호 수정하기
		public void passModify(String id, String pass) {
			String passModify = "update sunju set pass=? where id=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(passModify);
				
				pstmt.setString(1, pass);
				pstmt.setString(2, id);
				
				
				pstmt.executeUpdate();
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
		}
		//아이디 중복체크
		public boolean idCheck(String id) {
			String idCheck="select id from sunju where id=?";
			boolean result = false;
			try {
				PreparedStatement pstmt = conn.prepareStatement(idCheck);
				
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					result = true;
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
			
			return result;
		}
		//선주 회원 정보 가져오기
		public ArrayList<Sunju> allSunju() {
			String allSunju = "select id, name, sunju_code, phone from sunju order by no desc";
			ArrayList<Sunju> sunjuList = null;
			try {
				PreparedStatement pstmt = conn.prepareStatement(allSunju);
				ResultSet rs = pstmt.executeQuery();
				sunjuList = new ArrayList<Sunju>();
				while (rs.next()) {
					Sunju sunju = new Sunju();
					
					sunju.setId(rs.getString("id"));
					sunju.setName(rs.getString("name"));
					sunju.setSunju_code(rs.getString("sunju_code"));
					sunju.setPhone(rs.getString("phone"));
					
					sunjuList.add(sunju);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {			
				try{
					
					// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
					// 10. 커넥션 풀로 Connection 객체를 반환한다.
					if(conn != null) conn.close();
					
				} catch(SQLException e) { }
			}
			return sunjuList;
		}
		
		
		
		
		
}
