<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<article>
<div class="clear"></div>
<%@ include file="../pageForm/header.jsp" %>


<div class="container">
	<div class="row" style="padding-top: 50px;">
		<h2>최근 등록 선박</h2>
			<hr />
			<div class="row pt-md" style="margin-bottom: 80px;">
			

				<c:forEach var="shipList" items="${shipList }" varStatus="status">
					<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 team">
						<a href="shipDetail.mvc?shipNo=${shipList.shipNo}" class="shipList_tag">
							<div class="img-box">
								<img class="img-fluid" alt="Responsive Team Profiles"
									src="${shipList.file1 }" name="ListMainImg" style="width:347px;height:228px"/>
							</div>
							<h1>${shipList.shipName }</h1>
							<h2>[${shipList.area }] ${shipList.port }</h2>
							<h2>
								<span style="color: red; font-size: 20px">정상가</span><span>&nbsp;&nbsp;${shipList.price }</span>
							</h2>
							<hr />
							<div class="text-justify">
							<c:if test="${not empty shipList.flatFish }">
								<span class="text_04" id="flatFish">${shipList.flatFish}</span>
							</c:if>
							<c:if test="${not empty shipList.squid }"> 
								<span class="text_04" id="squid">${shipList.squid }</span>
							</c:if>
							<c:if test="${not empty shipList.rockFish }"> 
								<span class="text_04" id="rochFish">${shipList.rockFish }</span>
							</c:if>
							<c:if test="${not empty shipList.octopus }"> 
								<span class="text_04" id="octopus">${shipList.octopus }</span>
							</c:if> 
							<c:if test="${not empty shipList.whale }">
								<span class="text_04" id="whale">${shipList.whale }</span>
							</c:if>
							<c:if test="${not empty shipList.seabream }">
				      			 <span class="text_04" id="seabream">${shipList.seabream }</span>
				      		</c:if>
				      		<c:if test="${not empty shipList.mackerel }">
								 <span class="text_04" id="mackerel">${shipList.mackerel }</span>
							</c:if>
							<c:if test="${not empty shipList.mullet }"> 
								<span class="text_04" id="mullet">${shipList.mullet}</span>
							</c:if>
							<c:if test="${not empty shipList.shark}"> 
								<span class="text_04" id="shark">${shipList.shark }</span>
							</c:if>
							<c:if test="${not empty shipList.greenling}"> 
								<span class="text_04" id="greenling">${shipList.greenling }</span>
							</c:if>
							</div>
						</a>
					</div>
				</c:forEach>

				
				
		
				
				
			</div>
		</div>
	</div>
</article>