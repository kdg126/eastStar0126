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

import com.project.vo.Member;

public class MemberDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 연결
	public MemberDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println("NamingException");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();

		}

	}

	// 회원가입
	public void insertMember(com.project.vo.Member member) {
		String sql = "insert into member2 values(member2_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPass());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertProduct-Exception");
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("insertProduct-finally");
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	// 로그인 체크
	public String loginCheck(String id, String pass) {
		String check = "select id from member2 where id=? and pass=?";

		String result = "";
		try {
			PreparedStatement pstmt = conn.prepareStatement(check);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				result = rs.getString("id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}

		return result;
	}

	// 아이디 찾기
	public String idFind(String name, String phone) {
		String sql = "select id from member2 where name=? and phone=?";
		Member member = null;
		String result = "";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				member = new Member();

				member.setId(rs.getString("id"));

				result = member.getId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}

		return result;
	}

	// 비밀번호찾기
	public String passFind(String id, String name) {
		String sql = "select pass from member2 where id=? and name=?";
		com.project.vo.Member member = null;
		String result = "";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, name);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				member = new com.project.vo.Member();

				member.setPass(rs.getString("pass"));
				result = member.getPass();

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}

		return result;
	}

	// 회원정보가져오기
	public Member getUserInfo(String id) {
		String sql = "select id, name, phone, email from member2 where id=?";
		com.project.vo.Member member = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				member = new com.project.vo.Member();

				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));

				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}

		return member;
	}

	// 회원 정보 수정하기
	public void myInfoModify(String id, String name, String phone, String email) {
		String modify = "update member2 set name=?, phone=?, email=? where id=?";
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
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}
	}

	// 비밀번호 확인
	public int passCheck(String id, String pass) {
		String passCheck = "select pass from member2 where id=? and pass=?";
		int result = -1;
		String password = "";
		try {
			PreparedStatement pstmt = conn.prepareStatement(passCheck);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString("pass");
			}

			if (password.equals(pass)) {
				result = 0;
			} else if (password.equals("")) {
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("passCheck Dao");
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}

		return result;
	}

	// 비밀번호 수정하기
	public void passModify(String id, String pass) {
		String passModify = "update member2 set pass=? where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(passModify);

			pstmt.setString(1, pass);
			pstmt.setString(2, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}
	}

	// 아이디 중복체크
	public boolean idCheck(String id) {
		String idCheck = "select id from member2 where id=?";
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
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}

		return result;
	}

	// 일반 회원 정보 가져오기
	public ArrayList<Member> allMember() {
		String allMember = "select id, name, phone from member2 order by no desc";
		ArrayList<com.project.vo.Member> memberList = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(allMember);
			ResultSet rs = pstmt.executeQuery();
			memberList = new ArrayList<Member>();
			while (rs.next()) {
				Member member = new Member();

				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));

				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

				// 9. 사용한 ResultSet과 PreparedStatement 객체를 닫는다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();

				// 10. 커넥션 풀로 Connection 객체를 반환한다.
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
			}
		}

		return memberList;
	}

}
