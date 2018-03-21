$(document).ready(function() {
	
	$("#searchShip").on("submit", function() {
		if($("#province").val == 0) {
			alert("지역을 선택해 주세요");
			return false;
		}
		if($("#port1").val == 0) {
			alert("지역을 선택해 주세요");
			return false;
		}
		if($("#port1").val == 0) {
			alert("지역을 선택해 주세요");
			return false;
		}		
	});

	//배등록 유효성
	$("#shipWrite").on("submit", function() {
		if($("#province1").val() == 0) {
			alert("지역을 선택해 주세요");
			return false;
		}
		if($("#port2").val() == 0) {
			alert("항구를 선택해 주세요");
			return false;
		}
		if($("#shipName").val().length <= 0) {
			alert("배 이름을 등록해 주세요");
			$("#shipName").focus();
			return false;
		}
		if($("#price").val().length <= 0) {
			alert("가격을 등록해 주세요");
			$("#price").focus();
			return false;
		}
		if($("#minPeople").val().length <= 0) {
			alert("최소 인원을 등록해 주세요");
			$("#minPeople").focus();
			return false;
		} 
		if($("#maxPeople").val().length <= 0) {
			alert("최대 인원을 등록해 주세요");
			$("#maxPeople").focus();
			return false;
		} 
		if($("#fish:checked").length >= 6) {
			alert("물고기 종류는 5개 이하만 선택해 주세요");
			return false;
		}
		if($("#fish:checked").length == 0) {
			alert("물고기 종류를 체크해 주세요");
			return false;
		}
		if($("#image1").val().length <= 0) {
			alert("사진을 1장 이상 등록해 주세요");
			return false;
		}
		if($("#lat").val().length <= 0) {
			alert("위도를 입력해주세요");
			return false;
		}
		if($("#lng").val().length <= 0) {
			alert("경도를 입력해주세요");
			return false;
		}		
		
		var shipWriteConfirm = confirm("등록하시겠습니까?");
		if(shipWriteConfirm) {
			$("#shipWrite").attr("action", "shipWrite.mvc");
		} else {
			return false;
		}
	});
	
	
	
	//배수정 유효성
	$("#updateSubmit").on("click", function() {
		if($("#uProvince1").val() == 0) {
			alert("지역을 선택해 주세요");
			return false;
		}
		if($("#uPort2").val() == 0) {
			alert("항구를 선택해 주세요");
			return false;
		}
		if($("#uShipName").val().length <= 0) {
			alert("배 이름을 등록해 주세요");
			$("#uShipName").focus();
			return false;
		}
		if($("#uPrice").val().length <= 0) {
			alert("가격을 등록해 주세요");
			$("#uPrice").focus();
			return false;
		}
		if($("#uMinPeople").val().length <= 0) {
			alert("최소 인원을 등록해 주세요");
			$("#uMinPeople").focus();
			return false;
		} 
		if($("#uMaxPeople").val().length <= 0) {
			alert("최대 인원을 등록해 주세요");
			$("#uMaxPeople").focus();
			return false;
		} 
		if($("#fish1:checked").length >= 6) {
			alert("물고기 종류는 5개 이하만 선택해 주세요");
			return false;
		}
		if($("#fish1:checked").length == 0) {
			alert("물고기 종류를 체크해 주세요");
			return false;
		}
		if($("#uImage1").val().length <= 0) {
			alert("사진을 1장 이상 등록해 주세요");
			return false;
		}
		if($("#lat").val().length <= 0) {
			alert("위도를 입력해주세요");
			return false;
		}
		if($("#lng").val().length <= 0) {
			alert("경도를 입력해주세요");
			return false;
		}		
		
		var shipUpdateConfirm = confirm("수정하시겠습니까?");
		if(shipUpdateConfirm) {
			$("#updateSubmit").attr("action", "shipUpdate.mvc");
			$("#updateSubmit").attr("method", "post");
			$("#updateSubmit").submit();
		} else {
			return false;
		}
	});

	
	
	//배 수정 폼 체크박스 모두 선택
	$("#checkAll1").click(function() {
		if($("#checkAll1").prop("checked")){
			$("input[id=fish1]").prop("checked", true);
		} else {
			$("input[id=fish1]").prop("checked", false);
		}
	});
	
	
	//배 입력 폼 체크박스 모두 선택
	$("#checkAll").click(function() {
		if($("#checkAll").prop("checked")){
			$("input[id=fish]").prop("checked", true);
		} else {
			$("input[id=fish]").prop("checked", false);
		}
	});
	
	
	$("#updateButton").on("click", function() {
		
		$("#detailForm").attr("action", "shipUpdateForm.mvc");
		$("#detailForm").attr("method", "post");
		$("#detailForm").submit();	
	});
	
$("#deleteButton").on("click", function() {
		var deleteConfirm = confirm("해당 글을 삭제하시겠습니까?");
		if(deleteConfirm) {
			$("#detailForm").attr("action", "shipDelete.mvc");
			$("#detailForm").attr("method", "post");
			$("#detailForm").submit();
		}
	});
	
	
	
	//검색창 조건
	$("#province").change(function() {
		var item = $("#province option:selected").val();
		console.log("province changed");
		
		if (item == '0') {alert("제대로 선택하세요");return;}
		if(item == '경기도'){
			item = '1';
		} else if (item == '전라도') {
			item = '2';
		} else if (item == '경상도') {
			item = '3';
		} else if (item == '강원도') {
			item = '4';
		} else if (item == '제주도') {
			item = '5';
		}
		
		$.ajax({
			url : 'port.jsp', 
			data : "code=" + item,
			dataType : 'json',
			type : 'get',
			success : function(data) {
				console.log("data : " + data.items[0].code);
				$("#port1").empty();
				$("#port1").append("<option value='0'>--항구를 선택하세요1--</option>");
				var item = data.items;				
				$.each(item,	function(index, temp) {
					var code = temp.code;
					var name = temp.title;
					$("#port1").append("<option value='"+name+"'>"+ name+ "</option>");
				});
			}, 	error : function() {
				console.log("Ajax 에러 - " + status);
			}});
	});
	
	//등록창 조건
	$("#province1").change(function() {
		var item = $("#province1 option:selected").val();
		console.log("province1 changed");
		
		if (item == '0') {alert("제대로 선택하세요");return;}
		if(item == '경기도'){
			item = '1';
		} else if (item == '전라도') {
			item = '2';
		} else if (item == '경상도') {
			item = '3';
		} else if (item == '강원도') {
			item = '4';
		} else if (item == '제주도') {
			item = '5';
		}
		
		$.ajax({
			url : 'port.jsp', 
			data : "code=" + item,
			dataType : 'json',
			type : 'get',
			success : function(data) {
				console.log("data : " + data.items[0].code);
				$("#port2").empty();
				$("#port2").append("<option value='0'>--항구를 선택하세요1--</option>");
				var item = data.items;				
				$.each(item,	function(index, temp) {
					var code = temp.code;
					var name = temp.title;
					$("#port2").append("<option value='"+name+"'>"+ name+ "</option>");
				});
			}, 	error : function() {
				console.log("Ajax 에러 - " + status);
			}});
	});
	
	
	//수정창 조건
	$("#uProvince1").change(function() {
		var item = $("#uProvince1 option:selected").val();
		console.log("uProvince1 changed");
		
		if (item == '0') {alert("제대로 선택하세요");return;}
		if(item == '경기도'){
			item = '1';
		} else if (item == '전라도') {
			item = '2';
		} else if (item == '경상도') {
			item = '3';
		} else if (item == '강원도') {
			item = '4';
		} else if (item == '제주도') {
			item = '5';
		}
		
		$.ajax({
			url : 'port.jsp', 
			data : "code=" + item,
			dataType : 'json',
			type : 'get',
			success : function(data) {
				console.log("data : " + data.items[0].code);
				$("#uPort2").empty();
				$("#uPort2").append("<option value='0'>--항구를 선택하세요1--</option>");
				var item = data.items;				
				$.each(item,	function(index, temp) {
					var code = temp.code;
					var name = temp.title;
					$("#uPort2").append("<option value='"+name+"'>"+ name+ "</option>");
				});
			}, 	error : function(xhr, status, error) {
				console.log("Ajax 에러 - " + status);
			}});
	});

	
	$("#detail_basket").on("click", function() {
		
		if($("#testDatepicker").val().length <= 0) {
			alert("이용일이 선택되지 않았습니다.\n이용일을 선택해 주세요");
			return false;
		} else {
			var confirm2 = confirm("해당 배를 예약하시겠습니까?");
			if(confirm2) {
				$("#detailForm").attr("action", "basketWrite.mvc");
				$("#detailForm").attr("method", "post");
				$("#detailForm").submit();
			}
		}
	});
	
	$("#uProvince1").trigger("change");
});


