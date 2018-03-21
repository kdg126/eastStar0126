<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.basketListForm{
	    padding-top: 6%;
    padding-left: 4%;
}
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
var IMP = window.IMP; // 생략가능

function paymentCall() { 
	IMP.init('imp38449265');  // 가맹점 식별 코드
	
	IMP.request_pay({
			pg : 'html5_inicis', // 결제방식
		    pay_method : 'card',	// 결제 수단
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : 'ship_${ orderNo }',	// order 테이블에 들어갈 주문명 혹은 주문 번호
		    amount : '100',	// 결제 금액
		    buyer_email : 'break4181@gmail.com',	// 구매자 email - 고정
		    buyer_name : '${sessionScope.id}',	// 구매자 이름
		    buyer_tel :  '01056247680',	// 구매자 전화번호 - 고정
		    buyer_addr :  '서울 구로구 구로중앙로34길 33-4',	// 구매자 주소
		    buyer_postcode :  '08292',	// 구매자 우편번호
		    m_redirect_url : 'paymentFinish.mvc?order_No${orderNo}'	// 결제 완료 후 보낼 컨트롤러의 메소드명
		 }, function(rsp) {
			if (rsp.success ) { // 성공시
			  var msg = '결제가 완료되었습니다.\n';
			  msg += '\n결제 금액 : ' + rsp.paid_amount;
			  msg += '\n카드 승인번호 : ' + rsp.apply_num;
			  alert(msg);
			  location.href='paymentFinish.mvc?orderNo${orderNo}';		  
			} else { // 실패시
			  var msg = '결제에 실패하였습니다.';
			  msg += '에러내용 : ' + rsp.error_msg;
			  console.log(msg);	
			}
	});
}
</script>


<body style="background-color: aliceblue;">
<article>
<div class="basketListForm">
<c:set var="basketList" value="${ basketList}"/>

        <h2>장바구니</h2>
        <br/>
        <table border="2px">
            <tr>
                <th width="200">이미지</th>
                <th width="200">날짜</th>
                <th width="200">유저</th>
                <th width="200">배이름</th>
                <th width="200">가격</th>
                <th width="200">삭제</th>

            </tr>
		<c:if test="${ not empty basketList }">
          <c:forEach var="basket" items="${basketList }" >
            <tr>
            	<td align="center"><img class="img-fluid" alt="Responsive Team Profiles"
									src="${basket.basketFile }" style="width:150px;height:100px"/></td>
               <td align="center">${basket.regDate}</td>
                <td align="center">${basket.userId}</td>
                <td align="center">${basket.productName }</td>
                <td align="center">${basket.price}</td>
     			<td align="center">
     				<form method="post" id="basket_delete" action="basketDelete.mvc">
     					<input type="hidden" value="${basket.shipNo}" data-shipno="${ basket.shipNo }" class="shipno"/>
	     				<input type="hidden" name="hidden_userId" value="${basket.userId }"/>
						<input type="hidden" name="hidden_basketNo" value="${ basket.basketNo}" 
							data-basketno="${ basket.basketNo }" class="basketno"/>
						<input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 59%;
    height: 8%; display: inline;" value="delete" />
					 </form>
				</td>	
            </tr>
            </c:forEach>
			</c:if>
			
		<c:if test="${ empty basketList }">
            <tr>
            	<td align="center" colspan="6">예약된 배가 존재하지 않습니다</td>      
            </tr>
		</c:if>
        </table>
        <br/><br/>
        <input type="submit" class="btn btn-block btn-lg btn-primary" style="width: 9%;
    height: 8%; display: inline;" value="payment" id="btnPay"/>      
        </div> 	
</article>
</body>