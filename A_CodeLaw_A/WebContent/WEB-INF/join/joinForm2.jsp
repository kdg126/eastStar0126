<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반회원 기본 정보 입력</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">






	$(function(){
		$("#idCheck").on("click", function(){
			var id = $("#id").val();
			if(id.length == 0){
				alert("아이디를 입력해주세요.");
				$("#id").focus();
				return false;
			}else{
				url="overlapIdCheck.mvc?id="+id;
				window.open(url, "idCheck", "toolbar=no, location=no, status=no, menubar=no, width=400, height=200");
			}
			
		});
		 $("#emailCheck").on("click", function(){
			 var email1 = $("#email1").val();
			 var email2 = $("#email2").val();
			 if(email1.length <= 0 || email2.length <= 0){
				 alert("이메일이 입력되지않았습니다.");
				 return false;
			 }else{
				 var url = "emailCheck.mvc?email1="+email1+"&email2="+email2;
				 window.open(url, "emailCheck", "toolbar=no, location=no, status=no, menubar=no, width=400, height=200");
			 }
			 
			 
		 });
		/*  $("#idCheck").on("click", function(){
			if($("#id").val().length<=0){
				alert("아이디를 입력해주세요.");
				$("#id").focus();
			}else{
				
			$.ajax({
				type:'post',
				url:"../join/idCheckForm.jsp",
				data:{"id":$("#id").val()},
				dataType:'html',
				success:function(data){
					
				if(data == 0){
					alert("사용가능");
					$("#id").attr("readonly", "readonly");
					$("#name").focus();
				}	
						if(data == 1){
							alert("사용불가");
							$("#id").val("");
							$("#id").focus();
						}
				
				
				},
				error:function(){
					alert("안돼");
				}
			});
			}
		 }); */
		
		$("#joinForm").on("submit", function(){
			if($("#id").val().length<=0){
				alert("아이디를 입력해주세요");
				$("#id").focus();
				return false;
			}
			if($("#name").val().length<=0){
				alert("이름을 입력해주세요");
				$("#name").focus();
				return false;
			}
			if($("#pass").val().length<6 || $("#pass").val().length>15){
				alert("비밀번호는 6~15자 사이로 입력해주세요");
				$("#pass").focus();
				return false;
			}
			if($("#pass").val() != $("#passCheck").val()){
				alert("비밀번호가 같지않습니다.");
				return false;
			}
			if($("#phone").val().length<=0){
				alert("연락처를 입력해주세요");
				$("#phone").focus();
				return false;
			}
			if($("#email1").val().length<=0){
				alert("이메일을 입력해주세요");
				$("#email1").focus();
				return false;
			}
			if($("#email2").val().length<=0){
				alert("이메일을 입력해주세요");
				$("#email2").focus();
				return false;
			}
			if($("#idCheckConfirm").val()=='false'){
				alert("아이디 중복체크를 해주세요");
				return false;
			}
			if($("#authNumCheck").val()=='false'){
				alert("이메일 인증을 해주세요.");
				return false;
			}
		});
		 $("#selectEmail").on("change", function(){
			 var email = $("#selectEmail").val();
			 if(email == "직접입력"){
				 $("#email2").val("");
				 $("#email2").prop("readonly", false);
			 }else if(email == "네이버"){
				 $("#email2").val("naver.com");
				 $("#email2").prop("readonly", true);
			 }else if(email == "구글"){
				 $("#email2").val("gmail.com");
				 $("#email2").prop("readonly", true);
			 }else if(email == "다음"){
				 $("#email2").val("hanmail.net");
				 $("#email2").prop("readonly", true);
			 }
		 });
		 
		
		 
		 
	});
	
</script>

</head>
<body style="background-color: aliceblue;">
	<div class="layer" style="
    margin-left: 40%;

    margin-top: 4%;
    margin-bottom: 4%;"
 >
	<h2>일반회원 기본 정보 입력</h2>
	<br>
	<form action="joinInfo.mvc" method="post" name="joinForm" id="joinForm">
	<p>아이디</p>
	<p><input type="text" name="id" id="id" placeholder="아이디를 입력해주세요.">
		<input type="button" value="중복체크" id="idCheck">
		<input type="hidden" name="idCheckConfirm" id="idCheckConfirm" value="false">
	</p>
	<p>이름</p>
	<p><input type="text" name="name" id="name" placeholder="이름을 입력해주세요."></p>
	<p>비밀번호</p>
	<p><input type="password" name="pass" id="pass" placeholder="영문+숫자 6~15자리"></p>
	<p>비밀번호 확인</p>
	<p><input type="password" name="passCheck" id="passCheck" placeholder="입력된 비밀번호를 확인합니다."></p>
	<p>연락처</p>
	<p><input type="number" name="phone" id="phone" placeholder="숫자만 입력해주세요." maxlength="11"></p>
	<p>이메일</p>
	<p>
	<input type="text" name="email1" id="email1" placeholder="이메일을 입력해주세요.">@
	<input type="text" name="email2" id="email2">
	<select id="selectEmail">
		
		<option>직접입력</option>
		<option>네이버</option>
		<option>구글</option>
		<option>다음</option>
	</select>&nbsp;&nbsp;
	<span><input type="button" name="emailCheck" id="emailCheck"
   value="인증요청"></span>
	<input type="hidden" id="authNumCheck" value="false">
	</p>
	<br/><br/>
	<p>
		<input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 15%" value="회원가입">
	</p>
	</form>
	</div>
</body>
</html>
