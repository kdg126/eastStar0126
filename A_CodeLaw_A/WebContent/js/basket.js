$(function(){
	
	$("#basket_delete").on("submit", function() {		
		var confirmDelete = confirm("해당 품목을 장바구니에서 삭제하시겠습니까?");
		if(! confirmDelete) {
			return false;
		} 
	});
	
	$("#btnPay").on("click", function() {
		var confirmPayment = confirm("해당 품목을 결제하시겠습니까?");
		if(! confirmPayment) {
			return false;
		}
		paymentCall();
	});
	
	
	$("#payment_delete").on("submit", function() {		
		var confirmDelete = confirm("해당 품목을 장바구니에서 삭제하시겠습니까?");
		if(! confirmDelete) {
			return false;
		} 
	});
});





