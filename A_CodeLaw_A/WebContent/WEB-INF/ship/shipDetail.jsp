<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<style>
	#portMap{
		float:left;
		width:500px; height: 510px;	
		border: 1px solid #ccc;
	}
	#weather {
		width:450px;
		float: right;
	}
	#weather td {
		border-left: 1px solid #ccc;
		border-top: 1px solid #ccc;
		vertical-align: middle;
		padding: 0px;
		padding-left: 15px;
	}
	.ptop {
		margin-top: 10px;
	}
	#sunrise {
		height: 52px;
		text-align: center;	
	}
</style>

<!-- js 폴더에서 아래 3개 자바스크립트 포함 -->
<script src="js/jquery-1.12.1.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="js/jquery.xml2json.js"></script>
<script src="js/jquery.xdomainajax.js"></script>

<script>
$(function() {
	$("#testDatepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		nextText : '다음달',
		prevText : '이전달',
		showButtonPanel : true,
		currentText : '오늘날짜',
		closeText : '닫기',	
		minDate : "0D",	
		maxDate : "+3M",
		altField : ".selecter",
		altFormat : "yyyy-mm-dd"
	});
	
	$(".small-img").on("click", function (e) {
		
		var sImg = $(this).attr("src");
		var mImg = $("#main-img").attr("src");
		$("#main-img").attr("src", sImg);
		$(this).attr("src", mImg);
		return false;
	});
});

</script>
<script>

var port = "${ship.port}";

/* 지도 출력 함수 */
function initMap() {
	var location = {
		lat : ${ ship.lat},
		lng : ${ ship.lng }
	};
	console.log(location);
	
	var map = new google.maps.Map(document
			.getElementById('portMap'), {
		zoom : 15,
		center : location
	});
	var marker = new google.maps.Marker({
		position : location,
		map : map
	});
}


/* OpenWeather API */
var url = "http://api.openweathermap.org/data/2.5/forecast";
var lat = ${ ship.lat};
var lon = ${ ship.lng };
var APPID = "5a8ac8854efe55425ac16dd18fc1eb9e";
var mode = "xml"; // 반드시 소문자

var reqData = {
		lat: lat,
		lon: lon,
		APPID: APPID,
		mode: mode
}
var imgUrl = "http://openweathermap.org/img/w/";

/*  */
function getWeather(callback) {	
	$.ajax({
		url: url,
		type: "GET",
		data: reqData,
		dataType:"XML",
		success: callback,
		error: function(xhr, statusText, error) {
			console.log("error : " + statusText)
		}
	});
}


$(function() {
	getWeather(function (data, statusText, xhr){
		console.log('XML weather data received');
		
		// xml로 응답 받은 데이터를 텍스트 형식으로 읽어온다.
		var myXML = data.responseText;
		
		// xml을 파싱해서 읽어온 데이터를 json 형식으로 변환한다.
		var parseJSON = $.xml2json(myXML);
		console.log(parseJSON)
		var strSunrise = parseJSON.sun.rise;
		var strSunset  = parseJSON.sun.set;
		console.log("sunrise : " + strSunrise + ", sunset : " + strSunset);
		
		/* 오늘의 일출과 일몰 시간을 가져와서 Date 객체로 변환하고
		  * 한국 시간에 맞게 GMT에서 9시간을 더 한다. 
		  **/
		var sunrise = new Date(strSunrise);
		var sunset = new Date(strSunset);
		sunrise.setHours(sunrise.getHours() + 9)		
		sunset.setHours(sunset.getHours() + 9)
		
		var strTag = "<tr>"
					+ "<td colspan='2' id='sunrise'>오늘의 일출시간 : " + sunrise.getHours() + "시 " 
					+ 	sunrise.getMinutes() + "분 &nbsp&nbsp"
					+	"일몰시간 : " + sunset.getHours() + "시 " + sunset.getMinutes() + "분" + "</td>"
				+ "</tr>";
		$("#weather").append(strTag);
		
		var time = parseJSON.forecast.time;		
		$.each(time, function(index, value) {
			//console.log(value.from);
			/* 자바스크립트 Date 객체 참고 사이트
			 * https://blog.naver.com/agensoft/221079713905
			 **/
			 /* 현재 value의 시작 시간을 가져와 Date 객체로 변환하고
			  * 한국 시간에 맞게 GMT에서 9시간을 더 한다. 
			  **/			
			
			var d = new Date(value.from);
			d.setHours(d.getHours() + 9);
			if(d.getHours() == 12) {
				
				// 절대 온도를 섭씨 온도로 변환
				var sTemp = value.temperature.min - 273;
				
				strTag = "<tr>"
					+ "<td>" + d.getFullYear() + "년 " + (d.getMonth() + 1) + "월 " + d.getDate() + "일"
					+ 	"<img src='http://openweathermap.org/img/w/" +  value.symbol.var + ".png' />"
					+ "</td>"
					+ "<td>"
					+	"<p class='ptop'>온도 : " + Math.round(sTemp * 10) / 10 + "°C</p>"
					+	"<p>풍속 : " + value.windSpeed.mps + "m/s</p>"
					+ "</td>"
				+ "</tr>";
				$("#weather").append(strTag);			
			}			
		});
	});
});

</script>

<!-- 구글 맵 API 스크립트 참조 -->
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBHsQ4G5zhODJh5VPDVYy9OoPi0DkX8L8k&callback=initMap"></script>

<body style="background-color: aliceblue;">
<article>
	<!-- Page Content -->
	<form name="detailForm" id="detailForm">
	<div class="container" style="padding-left: 80px; padding-right: 80px; margin-bottom: 50px;">
	<c:set var="ship" value="${ship }"/>

		<br> <br> <br>

		<div class="row">

			<div class="col-md-8">
				<img class="img-fluid"
					src="${ship.file1 }" id="main-img"
					alt="ship01" style="width:600px;height:400px">
			</div>
			
			
			
			<!-- 여기 아래 div에다가 padding left든 뭐든 줘서 오른쪽으로 밀어 -->
			<div>
			<h3 class="my-3" >${ship.shipName }</h3>
				<div class="my-3">
					<b>${ship.area}</b>&nbsp;<b>${ship.port }</b>
					
				</div>
				

				<h4 class="my-4">
					정상가 : <small>${ship.price }</small>
				</h4>
				
				<c:if test="${not isLogin_S }">
				<h3 class="my-3">예약</h3>
				
					<input type="text" name="detail-regDate" id="testDatepicker" placeholder="이용일을 입력해주세요"
						size=19 readonly /> <br> <br> 
					<select id="date_select">
						<option value="(주간) 1항차 (06:00~15:00)">주간 1항차
							(06:00~15:00)</option>
						<option value="(야간) 1항차 (18:00~24:00)">야간 1항차
							(18:00~24:00)</option>
					</select><br /> <br /> 
					
						<input type="button" class="btn btn-block btn-lg btn-primary" name="basket" id="detail_basket" value="예약하기"/> 
						</c:if>
						<c:if test="${isLogin_S }">
						<br><br>
						<input type="button" class="btn btn-block btn-lg btn-primary" name="updateButton" id="updateButton" value="수정하기"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn btn-block btn-lg btn-primary" name="deleteButton" id="deleteButton" value="삭제하기"/>
					</c:if>
					</div>
				
		</div>
		<div class="row">
			<c:if test="${not empty ship.file2 }">
			<div class="col-md-3 col-sm-6 mb-4">
				<img class="img-fluid small-img"
					src="${ship.file2 }" id="sub-img1"
					alt="" style="height:150px">
				
			</div>
			</c:if>
			<c:if test="${not empty ship.file3 }">
			<div class="col-md-3 col-sm-6 mb-4">
				 <img class="img-fluid small-img" id="sub-img2"
					src="${ship.file3 }" alt="" style="height:150px">
				
			</div>
			</c:if>
			<c:if test="${not empty ship.file4 }">
			<div class="col-md-3 col-sm-6 mb-4">
				<img class="img-fluid small-img" id="sub-img3"
					src="${ship.file4 }" alt="" style="height:150px">
				
			</div>
			</c:if>
			<div id="bottom2">
				<table class="type066">
					<tr class="type066">
						<th scope="row">설명</th>
						<td>1.. 본 상품은 8월7일~11월15일까지 주중 예약일을 기준으로 할인적용 되며, 해당 기간이 아닌
							예약건의 경우 결제는 자동으로 취소됩니다. <br> 2. 본 상품은 예약 확정상품이 아닙니다. 반드시 예약
							확정문자를 받아보셔야 이용 가능합니다. <br> 3. 출항 시간은 상황에따라 다소 변경 될 수 있으며, 이
							경우 별도의 안내전화 또는 문자를 드립니다.<br> 4. 주말예약 관련 문의는
							마도로스고객센터(1544-4991)로 해주시면 친절히 안내드리겠습니다.
						</td>
					</tr>
					<tr class="type066">
						<th scope="row">탑승 인원</th>
						<td><span class="text_02">최소 ${ship.minPeople }명</span><span class="text_02">최대
								${ship.maxPeople }명</span></td>
					</tr>
					<tr class="type066">
						<th scope="row">주요어종</th>
						<td>
						<c:if test="${not empty ship.flatFish }">
								<span class="text_01">${ship.flatFish}</span>
							</c:if>
							<c:if test="${not empty ship.squid }"> 
								<span class="text_01">${ship.squid }</span>
							</c:if>
							<c:if test="${not empty ship.rockFish }"> 
								<span class="text_01">${ship.rockFish }</span>
							</c:if>
							<c:if test="${not empty ship.octopus }"> 
								<span class="text_01">${ship.octopus }</span>
							</c:if> 
							<c:if test="${not empty ship.whale }">
								<span class="text_01">${ship.whale }</span>
							</c:if>
							<c:if test="${not empty ship.seabream }">
				      			 <span class="text_01">${ship.seabream }</span>
				      		</c:if>
				      		<c:if test="${not empty ship.mackerel }">
								 <span class="text_01">${ship.mackerel }</span>
							</c:if>
							<c:if test="${not empty ship.mullet }"> 
								<span class="text_01">${ship.mullet}</span>
							</c:if>
							<c:if test="${not empty ship.shark}"> 
								<span class="text_01">${ship.shark }</span>
							</c:if>
							<c:if test="${not empty ship.greenling}"> 
								<span class="text_01">${ship.greenling }</span>
							</c:if>
						</td>
					</tr>
					<tr class="type066">
						<th scope="row">선박장비</th>
						<td><c:if test="${not empty ship.item1}"> 
								<span class="text_01">${ship.item1 }</span>
							</c:if>
							<c:if test="${not empty ship.item2}"> 
								<span class="text_01">${ship.item2}</span>
							</c:if>
							<c:if test="${not empty ship.item3}"> 
								<span class="text_01">${ship.item3}</span>
							</c:if>
							<c:if test="${not empty ship.item4}"> 
								<span class="text_01">${ship.item4}</span>
							</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row">주변시설</th>
						<td>
							<c:if test="${not empty ship.facility1}"> 
								<span class="text_01">${ship.facility1}</span>
							</c:if>
							<c:if test="${not empty ship.facility2}"> 
								<span class="text_01">${ship.facility2}</span>
							</c:if>
							<c:if test="${not empty ship.facility3}"> 
								<span class="text_01">${ship.facility3}</span>
							</c:if>
							<c:if test="${not empty ship.facility4}"> 
								<span class="text_01">${ship.facility4}</span>
							</c:if>
						</td>
					</tr>
					<tr class="type066">
						<th scope="row">출조 시간</th>
						<td><span class="text_01">06:00~15:00</span> <span
							class="text_01">18:00~24:00</span></td>
					</tr>
					<tr class="type066">
						<td colspan="2">
							<div id="portMap"></div>
							<table id="weather"></table> 
						</td>
					</tr>
				</table>
			</div>
		</div>

	</div>
	<input type="hidden" name="detail_shipName" id="detail_shipName" value="${ship.shipName }"/>
	<input type="hidden" name="detail_price" id="detail_price" value="${ship.price }"/>
	<input type="hidden" name="detail_img" id="detail_img" value="${ ship.file1 }"/>
	<input type="hidden" name="detail_shipNo" id="detail_shipNo" value="${ship.shipNo }"/>
	<input type="hidden" name="detail_userId" id="detail_userId" value="${ship.sunjuId }"/>
	</form>
	
	

</article>
</body>
