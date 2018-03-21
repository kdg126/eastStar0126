<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script type="text/javascript">
	var placeSearch, autocomplete;
	function initAutocomplete() {
		// Create the autocomplete object, restricting the search to geographical
		// location types.
		autocomplete = new google.maps.places.Autocomplete((document
				.getElementById('autocomplete')), {
			types : [ 'geocode' ]
		});
		// When the user selects an address from the dropdown, populate the address
		// fields in the form.
		autocomplete.addListener('place_changed', fillInAddress);
	}
	function fillInAddress() {
		// Get the place details from the autocomplete object.
		var place = autocomplete.getPlace();
		document.getElementById("lat").value = place.geometry.location.lat();
		document.getElementById("lng").value = place.geometry.location.lng();
	}
</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key
=AIzaSyDOZwg8ZpncbYBXwCGut13UtU284Q_Y2P4&libraries=places&callback=initAutocomplete"
		async defer>		
	</script>


<article>
<div class="layer" style="
    margin-left: 40%;

    margin-top: 5%;
    margin-bottom: 10%;"
 >
	<h2>배 정보 입력</h2>
	<form action="shipUpdate.mvc" method="post" style="height: 530px;" enctype="multipart/form-data" name="u-shipUpdate" id="u-shipUpdate">
	<p><select id="uProvince1" name="province1"
				style="width: 200px; color: Gray;">
				<option value='0'>--지역을 선택하세요--</option>
				<option ${ ship.area eq "경기도" ? "selected": "" }>경기도</option>
				<option ${ ship.area eq "전라도" ? "selected": "" }>전라도</option>
				<option ${ ship.area eq "경상도" ? "selected": "" }>경상도</option>
				<option ${ ship.area eq "강원도" ? "selected": "" }>강원도</option>
				<option ${ ship.area eq "제주도" ? "selected": "" }>제주도</option>
			</select> 
		  <select id="uPort2" name="port2"
				style="width: 200px; color: Gray;">
				<option value='0'>--항구를 선택하세요--</option>
				</select></p>
	<p>배이름</p>
	<p><input type="text" name="uShipName" id="uShipName" value="${ship.shipName }"></p>
	<p>가격</p>
	<p><input type="text" name="uPrice" id="uPrice" value="${ship.price }"></p>
	<p>인원수</p>
	<p><input type="number" id="uMinPeople" name="uMinPeople" min="0" max="6" value="${ship.minPeople }">~
		  <input type="number" id="uMaxPeople" name="uMaxPeople" min="6" max="50" value="${ship.maxPeople }">
	</p>
	<p>낚시 어종</p>
	<p><label><input  type="checkbox" id="checkAll1" name="checkAll1" />모두선택</label></p>
	<p><label><input  type="checkbox" id="fish1" name="flatFish"  value="광어" ${ship.flatFish eq "광어" ? "checked" : ""} />광어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="squid"  value="오징어"  ${ship.squid eq "오징어" ? "checked" : ""}/>오징어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="rockFish"  value="우럭"  ${ship.rockFish eq "우럭" ? "checked" : ""}/>우럭</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="octopus"  value="문어" ${ship.octopus eq "문어" ? "checked" : ""} />문어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="whale"  value="고래"  ${ship.whale eq "고래" ? "checked" : ""}/>고래</label>&nbsp;&nbsp;</p>
    <p><label><input  type="checkbox" id="fish1" name="seabream"  value="참돔"  ${ship.seabream eq "참돔" ? "checked" : ""}/>참돔</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="mackerel"  value="고등어" ${ship.mackerel eq "고등어" ? "checked" : ""} />고등어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="mullet"  value="숭어"  ${ship.mullet eq "숭어" ? "checked" : ""}/>숭어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="shark"  value="상어" ${ship.shark eq "상어" ? "checked" : ""} />상어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox" id="fish1" name="greenling"  value="노래미"  ${ship.greenling eq "노래미" ? "checked" : ""}/>노래미</label>&nbsp;&nbsp;
	</p>
	<p>선박 장비</p>
	<p><input type="text" name="uItem1" size="8" value="${ ship.item1}">
		  <input type="text" name="uItem2" size="8" value="${ ship.item2}">
		  <input type="text" name="uItem3" size="8" value="${ ship.item3}">
		  <input type="text" name="uItem4" size="8" value="${ ship.item4}">
	</p>
	<p>주변 시설</p>
	<p><input type="text" name="uFacility1" size="8" value="${ship.facility1 }">
		  <input type="text" name="uFacility2" size="8" value="${ship.facility2 }">
		  <input type="text" name="uFacility3" size="8" value="${ship.facility3 }">
		  <input type="text" name="uFacility4" size="8" value="${ship.facility4 }">
	</p>
	<p>항구검색</p>
	<div id="locationField">
		<input id="autocomplete" placeholder="항구 이름 입력" size="40" type="text">
	</div>
	<p><input type="text" name="ulat" id="lat" value="${ ship.lat }" placeholder="위도 입력" size="19" readonly="readonly">
		  <input type="text" name="ulng" id="lng" value="${ ship.lng }" placeholder="경도 입력" size="19" readonly="readonly">
	</p>
	<p>배 이미지1 : <input type="file" name="uFile1" id="uImage1"/></p>
	<p>배 이미지2 : <input type="file" name="uFile2" id="uImage2"/></p>	
	<p>배 이미지3 : <input type="file" name="uFile3" id="uImage3"/></p>	
	<p>배 이미지4 : <input type="file" name="uFile4" id="uImage4"/></p>	
	<input type="hidden" name="updateShipNo" value="${ship.shipNo }"/>	
	<input type="submit" id="updateSubmit" value="수정하기">
	</form>
		


</div>
</article>