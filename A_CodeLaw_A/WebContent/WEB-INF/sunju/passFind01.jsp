<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선주회원 비밀번호 찾기</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#passFindForm").on("submit", function(){
		if($("#id").val().length<=0){
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}
		if($("#name").val().length<=0){
			alert("아이디를 입력해주세요");
			$("#name").focus();
			return false;
		}
	});
});
</script>

<style type="text/css">
.passFindForm1{
	    padding-left: 43%;
    padding-top: 12%;
}
</style>

</head>
<body style="background-color: aliceblue;">
<div class="passFindForm1">
<form method="post" action="passFindResult01.mvc" id="passFindForm">
<h2>선주회원 비밀번호 찾기</h2>
<br/><br/>
<p>아이디</p>
<p><input type="text" name="id" id="id"></p>
<p>이름</p>
<p><input type="text" name="name" id="name"></p>
<br/><br/>
<p><input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 19%" value="비밀번호 찾기"></p>
</form>
</div>
</body>
</html>