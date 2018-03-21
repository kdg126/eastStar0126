<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

.button-3{
  width:200px;
  height:50px;
  border:2px solid #34495e;
  float:left;
  text-align:center;
  cursor:pointer;
  position:relative;
  box-sizing:border-box;
  overflow:hidden;
  margin:0 0 40px 50px;
}
.button-3 a{
  font-family:arial;
  font-size:16px;
  color:#34495e;
  text-decoration:none;
  line-height:50px;
  transition:all .5s ease;
  z-index:2;
  position:relative;
}

.button-3:hover .eff-3{
  bottom:0;
}

.idFindForm{
	padding-left: 34%;
    padding-top: 8%;
}
</style>
<body style="background-color: aliceblue;">
<article>
<div class="idFindForm">
	<h2>아이디찾기</h2>
	<br/><br/><br/>
	<span class="button-3">
	 <span class="eff-3">
	</span>
	<a href="idFind.mvc"> 일반회원 아이디 찾기 </a>
	</span>
	
	<span class="button-3">
	 <span class="eff-3"></span>
	<a href="idFind01.mvc"> 선주회원 아이디 찾기</a>
	
	</span>
	
	
	<br/><br/><br/><br/><br/><br/>
	
	<div>
	<h2>비밀번호찾기</h2>
	<br/><br/><br/>
	<span class="button-3">
	 <span class="eff-3">
	</span>
	<a href="passFind.mvc"> 일반회원 비밀번호 찾기 </a>
	</span>
	
	<span class="button-3">
	 <span class="eff-3"></span>
	<a href="passFind01.mvc"> 선주회원 비밀번호 찾기</a>
	
	</span>
	</div>
</div>
</article>

<!-- <p>
	<input type="button" value="일반회원 아이디 찾기" onclick="javascript:location.href='passFind.mvc';">
	<input type="button" value="선주회원 아이디 찾기" onclick="javascript:location.href='passFind01.mvc';">
	</p> -->
<!-- <input type="button" value="일반회원 아이디 찾기" onclick="javascript:location.href='idFind.mvc';">
<input type="button" value="선주회원 아이디 찾기" onclick="javascript:location.href='idFind01.mvc';"> -->
