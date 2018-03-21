
<%@page import="com.project.vo.Member"%>
<%@page import="com.project.dao.MemberDao"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberDao dao = new MemberDao();
	ArrayList<Member> memberList = dao.allMember();
%>
<c:set var="member" value="<%=memberList%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반회원 관리페이지</title>

</head>
<body>
	<h2>일반회원 관리페이지</h2>
	<div id="memberManage">
		<table id="memberTable" border="1">
			<tr>
				<th id="idTh">아이디</th>
				<th id="nameTh">이름</th>
				<th id="phoneTh">휴대전화</th>
			</tr>
			<c:forEach var="m" items="${member }">
				<tr>
					<td id="idTd">${m.id }</td>
					<td id="nameTd">${m.name }</td>
					<td id="phoneTd">${m.phone }</td>
				</tr>
			</c:forEach>
		</table>
	</div>



</body>
</html>