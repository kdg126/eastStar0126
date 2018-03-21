<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	

  	
  	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" >
		<div class="container">
			<a class="navbar-brand" href="*.mvc">코드로</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation" style="color:black">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					
					<li class="nav-item active"><a class="nav-link" href="*.mvc">메인페이지
							<span class="sr-only">(current)</span>
					</a></li>
					
					
					<c:if test="${not isLogin_N }">
					<c:if test="${not isLogin_S }">
					<li class="nav-item"><a class="nav-link" href="join.mvc">회원가입</a></li>
					</c:if>
					</c:if>
					
					
					
					<c:if test="${ isLogin_S }">
					<li class="nav-item"><a class="nav-link" href="shipWriteForm.mvc">
						배 등록
					</a>
					</li>
					</c:if>
					
					<c:if test="${isLogin_N }">
					<li class="nav-item"><a class="nav-link" href="memberInfo.mvc">
						내 정보
					</a>
					</li>
					</c:if>
					
					<c:if test="${isLogin_N }">
					<li class="nav-item"><a class="nav-link" href="memberPass.mvc">
						비밀번호 변경
					</a>
					</li>
					</c:if>
					
					<c:if test="${isLogin_S }">
					<li class="nav-item"><a class="nav-link" href="memberInfo1.mvc">
						개인정보 변경
					</a>
					</li>
					</c:if>
					
					<c:if test="${isLogin_S }">
					<li class="nav-item"><a class="nav-link" href="memberPass1.mvc">
						비밀번호 변경
					</a>
					</li>
					</c:if>
					
					<li class="nav-item"><a class="nav-link" href="faqList.mvc">
						FAQ
					</a>
					</li>
					
					<c:if test="${not isLogin_N }">
					<c:if test="${not isLogin_S }">
					<li class="nav-item"><a class="nav-link" href="login.mvc">
						로그인
					</a>
					</li>
					</c:if>
					</c:if>
					
					<c:if test="${isLogin_N or isLogin_S }">
					<li class="nav-item"><a class="nav-link" href="logout.mvc">
						로그아웃
					</a>
					</li>
					</c:if>
					
					
					<li class="nav-item"><a class="nav-link"><c:if test="${isLogin_N or isLogin_S }">
					${id }님
					</c:if>
					</a>
					</li>
					
					<c:if test="${isLogin_N }">
					<li class="nav-item"><a class="nav-link" href="basketList.mvc">
						장바구니
					</a>
					</li>
					</c:if>
					
					<c:if test="${isLogin_N }">
					<li class="nav-item"><a class="nav-link" href="paymentList.mvc">
						구매내역
					</a>
					</li>
					</c:if>
					
				</ul>
			</div>
		</div>
	</nav>