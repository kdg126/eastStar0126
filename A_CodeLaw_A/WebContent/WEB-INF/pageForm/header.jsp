<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<header class="masthead text-center">
		
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto">
					<h1 class="mb-5">Code_Law</h1>
				</div>
				<div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
					<form action="newShipList.mvc" id="searchShip">
						<div class="form-row" style="margin-left: 43px;">
							<div>
								<select id="province" name="province"
									style="width: 180px; height: 40px; font-size: 18px; color: Gray;">
									<option value='0'>지역을 선택하세요--</option>
									<option>경기도</option>
									<option>전라도</option>
									<option>경상도</option>
									<option>강원도</option>
				    				<option>제주도</option>
								</select>
								 <select id="port1" name="port1"
									style="width: 180px; height: 40px; font-size: 18px; color: Gray;">
									<option value='0'>항구를 선택하세요--</option>
								</select>
								 <input type="number" placeholder="인원를 선택하세요--" name="headcount" min="0" max="30"
									style="width: 180px; height: 40px; font-size: 18px; color: Gray; "
									id="headcount">

								<button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</header>



