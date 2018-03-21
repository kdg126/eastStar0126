<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
<!--

//-->
</script>

<style>
.paymentListForm{
	padding-top: 6%;
    padding-left: 13%;
}
</style>

<body style="background-color: aliceblue;">
<article>
<div class="paymentListForm">
	<c:set var="payment" value="${paymentList}"/>
        <h2>예약 정보</h2>
        <br/>
        <table border="2">
            <tr>
                <th width="200">이미지</th>
                <th width="200">주문자</th>
                <th width="200">배이름</th>
                <th width="200">가격</th>
                <th width="200">주문일</th>
            </tr>
          <c:if test="${ not empty paymentList }">
          <c:forEach var="payment" items="${paymentList }" varStatus="status">
            <tr>
            	<td align="center"><img src="${payment.paymentFile}" style="width:150px;height:100px"/></td>
                <td align="center">${payment.userId }</td>
                <td align="center">${payment.productName }</td>
                <td align="center">${payment.price}</td> 
                <td align="center">${payment.regDate}</td>      
            </tr>
            </c:forEach>
          </c:if>
          <c:if test="${empty paymentList }" >
          	<tr>
          	<td colspan="5" style="text-align: center;">구매내역이 존재하지 않습니다.</td>
          	</tr>
          </c:if>
          
        </table>
</div>













</article>
</body>