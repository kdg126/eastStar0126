<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반회원 아이디 찾기</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#idFindForm").on("submit", function(){
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
		});
	});
</script>

<style type="text/css">
.idFindForm1{
	    padding-left: 43%;
    padding-top: 12%;
}
</style>

</head>
<body style="background-color: aliceblue;">

<div class="idFindForm1">
<form method="post" action="idFindResult.mvc" id="idFindForm">
<h2>일반회원 아이디 찾기</h2>
<br/><br/>
<p>이름</p>
<p><input type="text" name="name" id="name"></p>
<p>연락처</p>
<p><input type="text" name="phone" id="phone" maxlength="11"></p>
<br/><br/>
<input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 19%" value="아이디 찾기">
</form>
</div>

</body>
</html>