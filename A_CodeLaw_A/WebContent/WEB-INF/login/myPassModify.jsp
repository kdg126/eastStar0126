<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#cancel").on("click", function(){
			var cancel = confirm("비밀번호 변경을 취소하시겠습니까?");
			if(cancel){
				location.href="index.mvc";
			}
			return false;
	});
	
	$("#passModifyForm").on("submit", function(){
		
		if($("#pass").val().length >15 || $("#pass").val().length < 6){
			alert("변경 비밀번호를 6~15자 사이로 입력해주세요.");
			$("#pass").focus();
			return false;
		}
		if($("#pass1").val().length <=0){
			alert("현재 비밀번호를 입력해주세요.");
			$("#pass1").focus();
			return false;
		}
		if($("#passCheck").val().length <=0){
			alert("변경할 비밀번호 확인를 입력해주세요.");
			$("#passCheck").focus();
			return false;
		}
		
		if($("#pass1").val() == $("#pass").val()){
			alert("기존의 비밀번호와 같습니다.");
			return false;
		}
		if($("#pass").val() != $("#passCheck").val()){
			alert("변경할 비밀번호가 같지않습니다.");
			return false;
		}
		
		var submit = confirm("비밀번호를 변경하시겠습니까?");
		if(submit){
			return true;
		}
		return false;
	});
});
</script>

<style type="text/css">
.passModifyForm1{
 padding-left: 45%;
    padding-top: 8%;
}
</style>

</head>
<body style="background-color: aliceblue;">
<div class="passModifyForm1">
<form method="post" id="passModifyForm" name="passModifyForm" action="memberPassModify.mvc">
<h2>비밀번호 변경</h2>
<br/><br/>
<p>현재 비밀번호</p>
<p><input type="password" name="pass1" id="pass1"></p>
<p>변경 비밀번호</p>
<p><input type="password" name="pass" id="pass"></p>
<p>변경할 비밀번호 확인</p>
<p><input type="password" name="passCheck" id="passCheck"></p>
<br/><br/>
<p>
<input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 16%;
    height: 8%; display: inline;" value="변경하기" id="passModify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" class="btn btn-block btn-lg btn-primary" style="width: 16%;
    height: 8%; display: inline; margin-top: 0px;" value="취소하기" id="cancel">
</p>
</form>
</div>
</body>
</html>