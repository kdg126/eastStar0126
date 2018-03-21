<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.joinProcessForm{
	padding-left: 43%;
    padding-top: 12%;
}
</style>


</head>
<body style="background-color: aliceblue;">
<div class="joinProcessForm">
	<h3>선주회원 정보 입력 확인</h3>
	<br/><br/>
	<c:set var="sunju" value="${requestScope.sunju }" scope="session" />
	<div>
		<span>아이디 : </span>
		<span>${sunju.id }</span>
	</div>
	<br/>
	<div>
		<span>이름 :</span>
		<span>${sunju.name }</span>
	</div>
	<br/>
	<div>
		<span>사업자번호 :</span>
		<span>${sunju.sunju_code }</span>
	</div>
	<br/>
	<div>
		<span>연락처 :</span>
		<span>${sunju.phone }</span>
	</div>
	<br/>
	<div>
		<span>이메일 :</span>
		<span>${sunju.email }</span>
	</div>
	<br/><br/>
	<div>
		<input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 16%" value="가입완료" onclick="document.location.href='joinResult1.mvc'"> 
	</div>
	</div>
</body>
</html>