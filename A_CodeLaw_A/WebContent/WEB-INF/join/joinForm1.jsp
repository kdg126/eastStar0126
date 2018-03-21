<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>약관 동의</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn1").on("click", function(){
		$("#agreeForm").attr("action", "join1.mvc");
		$("#agreeForm").submit();
	});
	$("#btn2").on("click", function(){
		$("#agreeForm").attr("action", "join2.mvc");
		$("#agreeForm").submit();
	});
});
</script>

</head>
<body style="background-color: aliceblue;">
<div class="layer" style="
    margin-left: 36%;

    margin-top: 7%;
    margin-bottom: 10%;"
 >
<h2>약관동의</h2>
<br/><br/><br/>
<form id="agreeForm">
	<h3>코드로 약관</h3>
<div>
	아래 코드로의 약관을 읽으시고 동의하셔야 회원으로 가입하실 수 있습니다.<br/> 
		------------------------------------------------------------------------<br/>
		<p>1. 코드로 회원 정보는 개인정보 보호방침에 따라 철저히 관리되며<br/>
			코드로의 운영을 위해서만 사용됩니다.</p>
		<p>2. 코드로의 정상적인 운영을 방해하는 아래와 같은 행위를 하는<br/>
			회원은 경고 3회 조치후 강제 탈퇴 될 수 있습니다.</p>
		<p>- 코드로의 다른 회원을 비방하는 게시 글을 올린 회원<br/>
		- 코드로에서 판매되고 있는 도서를 근거 없이 비방하는 회원<br/>
		- 기타 코드로의 정상적인 운영에 방해가 되는 행위를 하는 회원</p> 
		------------------------------------------------------------------------<br/>
		<p>위 약관에 동의 합니까?<br/>
	<input type="radio" name="agree" value="true" checked="checked">동의합니다.
	<input type="radio" name="agree" value="false">동의하지 않습니다.
</div>
<br/><br/><br/>

	<p><input type="button" class="btn btn-block btn-lg btn-primary" style="width: 32%;
    height: 8%; display: inline;" value="일반회원으로 가입하기" id="btn1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" class="btn btn-block btn-lg btn-primary" style="width: 32%;
    height: 8%; display: inline;
    margin-top: 0px;" value="선주회원으로 가입하기" id="btn2">
	</p>
</form>
</div>
<!-- 모달버튼 -->
<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Small modal</button>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <h2>가입하시겠습니까??</h2>
       <div class="modal-footer">
        
        <button type="button" class="btn btn-primary">가입하기</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
  </div> -->


</body>
</html>