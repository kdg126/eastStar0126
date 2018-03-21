
<%@page import="com.project.vo.Sunju"%>
<%@page import="com.project.dao.SunjuDao"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
	SunjuDao dao = new SunjuDao();
    ArrayList<Sunju> sunjuList = dao.allSunju();
%>
<c:set var="sunju" value="<%=sunjuList %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선주회원 관리페이지</title>
</head>
<body>
<h2>선주회원 관리페이지</h2>
<div id="sunjuManage">
	<table border="1">
		<tr>
			<th id="idTh">아이디</th>
			<th id="nameTh">이름</th>
			<th id="sunju_codeTh">사업자번호</th>
			<th id="phone">휴대전화</th>
		</tr>
		<c:forEach var="s" items="${sunju }">
		<tr>
			<td id="idTd">${s.id }</td>
			<td id="nameTd">${s.name }</td>
			<td id="sunju_codeTd">${s.sunju_code }</td>
			<td id="phoneTd">${s.phone }</td>
		</tr>
		</c:forEach>
	</table>
</div>

	

</body>
</html>