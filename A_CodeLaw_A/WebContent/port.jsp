<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	이번에는 응답 내용을
	
	{"items" : [
		{"code" : "0001", "title" : "복사용지"},
		{"code" : "0002", "title" : "복사용지"},
		{"code" : "0003", "title" : "복상용지"}
	]}
	
--%>
<%-- <%
	//	몇번을 요구하는지 받아보자.
	String	strCode = request.getParameter("code");
	int		code = Integer.parseInt(strCode);
	
	System.out.println("code : " + code);
	//	JSTL에서 사용가능하도록 request에 넣어두자
	request.setAttribute("CODE", code);
%> --%>
<%
	System.out.println("port.jsp : " + request.getParameter("code"));
%>
<c:if test="${param.code eq 1}">
	{"items" : [
		{"code" : "0001", "title" : "궁평항"},
		{"code" : "0002", "title" : "평택항"},
		{"code" : "0003", "title" : "오이도항"},
		{"code" : "0004", "title" : "인천항"},
		{"code" : "0005", "title" : "전곡항"},
		{"code" : "0006", "title" : "탄도항"}
	]}
</c:if>
<c:if test="${param.code eq 2}">
	{"items" : [
		{"code" : "0007", "title" : "격포항"},
		{"code" : "0008", "title" : "곰소항"},
		{"code" : "0009", "title" : "군산항"},
		{"code" : "0010", "title" : "비응항"}
	]}
</c:if>
<c:if test="${param.code eq 3}">
	{"items" : [
		{"code" : "0011", "title" : "강구항"},
		{"code" : "0012", "title" : "후포항"},
		{"code" : "0013", "title" : "도동항"},
		{"code" : "0014", "title" : "감포항"},
		{"code" : "0015", "title" : "저동항"}
	]}
</c:if>
<c:if test="${param.code eq 4}">
	{"items" : [
		{"code" : "0016", "title" : "대호항"},
		{"code" : "0017", "title" : "장호항"},
		{"code" : "0018", "title" : "동명항"}
	]}
</c:if>
<c:if test="${param.code eq 5}">
	{"items" : [
		{"code" : "0019", "title" : "도두항"},
		{"code" : "0020", "title" : "제주항"},
		{"code" : "0021", "title" : "모슬 포항"}
	]}
</c:if>