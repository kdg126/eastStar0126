<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
var IMP = window.IMP; // 생략가능
 IMP.init('imp38449265');  // 가맹점 식별 코드

 IMP.request_pay({
			pg : 'html5_inicis', // 결제방식
		    pay_method : 'card',	// 결제 수단
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '${ orderNo }',	// order 테이블에 들어갈 주문명 혹은 주문 번호
		    amount : '100',	// 결제 금액
		    buyer_email : 'break4181@gmail.com',	// 구매자 email - 고정
		    buyer_name : '${sessionScope.id}',	// 구매자 이름
		    buyer_tel :  '01056247680',	// 구매자 전화번호 - 고정
		    buyer_addr :  '서울 구로구 구로중앙로34길 33-4',	// 구매자 주소
		    buyer_postcode :  '08292',	// 구매자 우편번호
		    m_redirect_url : 'paymentFinish.mvc?order_No${orderNo}'	// 결제 완료 후 보낼 컨트롤러의 메소드명
		 }, function(rsp) {
			if ( rsp.success ) { // 성공시
			  var msg = '결제가 완료되었습니다.\n';
			  msg += '고유ID : ' + rsp.imp_uid;
			  msg += '\n상점 거래ID : ' + rsp.merchant_uid;
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
</script>