
<%@page import="com.project.vo.Sunju"%>
<%@page import="com.project.dao.SunjuDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선주회원 비밀번호 찾기 결과</title>

<style type="text/css">
.passResultForm{
padding-left: 44%;
    padding-top: 15%;
}
</style>

</head>
<body style="background-color: aliceblue;">

<div class="passResultForm">
	<h2>당신의 비밀번호는</h2>
	<br/><br/>
	<p>${ result }입니다.</p>
	<br/><br/>
	<p>
		<input type="button" class="btn btn-block btn-lg btn-primary" style="width: 25%" value="로그인 하러가기"
			onclick="javascript:location.href='login.mvc';">
	</p>
</div>


</body>
</html>