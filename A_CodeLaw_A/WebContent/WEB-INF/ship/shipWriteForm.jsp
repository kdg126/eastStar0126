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
	<form method="post" style="height: 530px;" enctype="multipart/form-data" name="shipWrite" id="shipWrite">
	<p><select id="province1" name="province1"
				style="width: 200px; color: Gray;">
				<option value='0'>--지역을 선택하세요--</option>
				<option>경기도</option>
				<option>전라도</option>
				<option>경상도</option>
				<option>강원도</option>
				<option>제주도</option>
			</select> 
		  <select id="port2" name="port2"
				style="width: 200px; color: Gray;">
				<option value='0'>--항구를 선택하세요--</option>
				</select></p>
	<p>배이름</p>
	<p><input type="text" name="shipName" id="shipName" placeholder="배 이름을 입력해주세요."></p>
	<p>가격</p>
	<p><input type="text" name="price" id="price" placeholder="가격을 입력해주세요."></p>
	<p>인원수</p>
	<p><input type="number" id="minPeople" name="minPeople" size="10" placeholder="최소인원" min="0" max="6" >~
		  <input type="number" id="maxPeople" name="maxPeople" size="10" placeholder="최대인원" min="6" max="50">
	</p>
	<p>낚시 어종</p>
	<p><label><input  type="checkbox" id="checkAll" name="checkAll" />모두선택</label></p>
	<p><label><input  type="checkbox"  id="fish" name="flatFish"  value="광어"  />광어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="squid"  value="오징어"  />오징어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="rockFish"  value="우럭"  />우럭</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="octopus"  value="문어"  />문어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="whale"  value="고래"  />고래</label>&nbsp;&nbsp;</p>
    <p><label><input  type="checkbox"  id="fish" name="seabream"  value="참돔"  />참돔</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="mackerel"  value="고등어"  />고등어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="mullet"  value="숭어"  />숭어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="shark"  value="상어"  />상어</label>&nbsp;&nbsp;
	      <label><input  type="checkbox"  id="fish" name="greenling"  value="노래미"  />노래미</label>&nbsp;&nbsp;
	</p>
	<p>선박 장비</p>
	<p><input type="text" name="item1" placeholder="장비" size="8">
		  <input type="text" name="item2" placeholder="장비" size="8">
		  <input type="text" name="item3" placeholder="장비" size="8">
		  <input type="text" name="item4" placeholder="장비" size="8">
	</p>
	<p>주변 시설</p>
	<p><input type="text" name="facility1" placeholder="시설" size="8">
		  <input type="text" name="facility2" placeholder="시설" size="8">
		  <input type="text" name="facility3" placeholder="시설" size="8">
		  <input type="text" name="facility4" placeholder="시설" size="8">
	</p>
	<p>항구검색</p>
	<div id="locationField">
		<input id="autocomplete" placeholder="항구 이름 입력" size="40" type="text">
	</div>
	<p><input type="text" name="lat" id="lat" placeholder="위도 입력" readonly="readonly" size="19">
		  <input type="text" name="lng" id="lng" placeholder="경도 입력" readonly="readonly" size="19">
	</p>
	<p>배 이미지1 : <input type="file" name="file1" id="image1"/></p>
	<p>배 이미지2 : <input type="file" name="file2" id="image2"/></p>	
	<p>배 이미지3 : <input type="file" name="file3" id="image3"/></p>	
	<p>배 이미지4 : <input type="file" name="file4" id="image4"/></p>		
	<input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 15%;
    height: 8%;" value="등록하기">
	</form>
	


</div>
</article>