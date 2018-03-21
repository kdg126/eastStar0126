<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#idCheckForm").on("submit", function(){
			var id = $("#checkId").val();
			if(id.length == 0){
				alert("아이디를 입력해주세요.");
				return false;
			}
					});
		
		$("#btnIdCheckClose").on("click", function(){
			var id = $(this).attr("data-id-value");
			opener.document.joinForm.idCheckConfirm.value=true;
			opener.document.joinForm.id.value = id;
			
			
			window.close();
		});
		
	});
</script>

</head>
<body>

 <c:if test="${result == false }">
	<h3>사용할 수 있는 아이디</h3>
	<div>입력하신 ${id }는 사용할 수 있는 아이디 입니다.</div>
	<br/>
	<div>
		<input type="button" value="${id }을(를) 아이디로 사용하기" 
		id="btnIdCheckClose" data-id-value="${id }" class="btn btn-block btn-lg btn-primary">
	</div>
</c:if>

<c:if test="${result == true }">
<h3>사용할 수 없는 아이디</h3>
<div>입력하신 ${id }는 이미 사용 중인 아이디입니다.</div>
<div>다른 아이디를 선택해주세요.</div>
<br/>
<form action="overlapIdCheck.mvc" name="idCheckForm" method="post" id="idCheckForm">
	<span>아이디 : </span>
	<input type="text" name="id" id="checkId" size="15">
	<input type="submit" value="중복확인">
</form>
</c:if> 
</body>
</html>