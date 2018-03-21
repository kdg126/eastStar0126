<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	/* $(function(){
		$("#authForm").on("submit", function(){
			var form = $("#authNum").val();
			var authNum = ${authNum};
			if(form.length <= 0){
				alert("인증번호를 입력하세요.");
				return false;
			}else if(form != authNum){
				alert("인증번호가 틀립니다.");
				$("#authNum").val("");
				$("#authNum").focus();
				return false;
			}else if(form == authNum){
				alert("인증완료");
				opener.document.joinForm.authNumCheck.value=true;
				window.close();
			}
		});
		
	}); */
	function check(){
		var form = document.authForm;
		var authNum = document.authForm.num.value;
		
		if(! form.authNum.value){
			alert("인증번호를 입력하세요");
			return false;
		}
		if(form.authNum.value != authNum){
			alert("인증번호가 틀립니다.");
			form.authNum.value="";
			return false;
		}
		if(form.authNum.value == authNum){
			alert("인증완료");
			opener.document.joinForm.authNumCheck.value=true;
			self.close();
		}
	}
</script>

</head>
<body>
<h3>인증번호를 입력하세요</h3>
<br/>
<div>
	<form method="post" name="authForm" id="authForm" onsubmit="return check();">
		<input type="text" name="authNum" id="authNum" placeholder="인증번호를 입력해주세요.">
		<input type="hidden" name="num" id="num" value="${authNum }">
		<input type="submit" class="btn btn-block btn-lg btn-primary" value="인증하기" id="btn_Auth">
	</form>
</div>
</body>
</html>