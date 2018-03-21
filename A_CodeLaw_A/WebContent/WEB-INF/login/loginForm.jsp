<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#loginForm").on("submit", function(){
			
			 
			if($("#id").val().length<=0){
				alert("아이디를 입력하세요");
				$("#id").focus();
				return false;
			}
			if($("#pass").val().length<=0){
				alert("비밀번호를 입력하세요");
				$("#pass").focus();
				return false;
			}
		}); 
	});
</script>



</head>
<body style="background-color: aliceblue;">
<div class="layer" style="
    margin-left: 43%;
    margin-top: 8%;
    margin-bottom:13%;'"
 >
 <div>
<h2>코드로</h2><br>
<form method="post" action="loginCheck1.mvc" id="loginForm">
<label>
<input type="radio" name="memberCheck" id="memberCheck" value="normal" checked="checked">일반회원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<label><input type="radio" name="memberCheck" id="memberCheck" value="sunju">선주회원</label>
<br><br>
<p>아이디</p>
<p><input type="text" name="id" id="id"></p><br>
<p>비밀번호</p>
<p><input type="password" name="pass" id="pass"></p><br>


 <p><input type="submit" id="btn" class="btn btn-block btn-lg btn-primary" style="width: 14%" value="로그인" ></p> 

<a href="idFind00.mvc" style="color: black; text-decoration: none;">아이디&nbsp;/&nbsp;비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;<a href="join.mvc" style="color: black; text-decoration: none;">회원가입</a>
</form>
</div>
</div>
</body>
</html>