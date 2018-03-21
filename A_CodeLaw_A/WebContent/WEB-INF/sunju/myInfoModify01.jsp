
<%@page import="com.project.vo.Sunju"%>
<%@page import="com.project.dao.SunjuDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본정보 변경</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modify").on("click", function(){
			if($("#name").val().length<=0){
				alert("이름을 입력해주세요");
				$("#name").focus();
				return false;
			}
			if($("#phone").val().length<=0){
				alert("연락처를 입력해주세요");
				$("#phone").focus();
				return false;
			}
			if($("#email").val().length <= 0){
				alert("이메일을 입력해주세요");
				return false;
			}
			$("#myInfoModify").attr("action", "myInfoModify01.mvc");
			$("#myInfoModify").attr("method", "post");
			var modify = confirm("수정하시겠습니까?");
			if(modify){
				$("#myInfoModify").submit();
			}
			return false;
		});
		$("#cancel").on("click", function(){
			var cancel = confirm("기본정보 수정을 취소하시겠습니까?");
			if(cancel){
				history.back();
			}
			return false;
		});
	});
</script>

<style type="text/css">
.myInfoModifyForm{
	padding-left: 45%;
    padding-top: 8%;
}
</style>

</head>
<body style="background-color: aliceblue;">
<div class="myInfoModifyForm">
	<form id="myInfoModify">
		<h2>기본정보 변경</h2>
		<br/>
		<p>아이디</p>
		<p><input type="text" id="id" name="id" readonly="readonly" value="${ sunju.id }"></p>
		<p>이름</p>
		<p><input type="text" id="name" name="name" value="${ sunju.name }"></p>
		<p>사업자번호</p>
		<p><input type="text" id="sunju_code" name="sunju_code" readonly="readonly" value="${ sunju.sunju_code }"></p>
		<p>연락처</p>
		<p><input type="text" id="phone" name="phone" value="${ sunju.phone }" maxlength="11"></p>
		<p>이메일</p>
		<p><input type="text" id="email" name="email" value="${ sunju.email }" maxlength="11"></p>
		<br/>
		<p>
		<input type="button" class="btn btn-block btn-lg btn-primary" style="width: 16%;
    height: 8%; display: inline;" value="수정하기" id="modify">&nbsp;&nbsp;
		<input type="button" class="btn btn-block btn-lg btn-primary" style="width: 16%;
    height: 8%; display: inline; margin-top: 0px;" value="취소하기" id="cancel">
		</p>
	</form>
	</div>
</body>
</html>