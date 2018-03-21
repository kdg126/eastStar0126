<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
   
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script src="js/jquery-1.11.3.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body style="padding-top: 0px; background-color: aliceblue;">
<article style="
    width: 700px;
    margin-left: 30%;
    margin-top: 50px;
    
">

<h1>FAQ 게시판</h1>

<div class="panel-group" id="accordion" role="tablist"
 aria-multiselectable="true">
 <c:forEach var="faq" items="${ faqList }" varStatus="status">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="heading0$
    { status.count }">
      <h4 class="panel-title">
        <a style="text-decoration: none;" data-toggle="collapse" 
        data-parent="#accordion" href="#collapse0${ status.count }"
         aria-expanded="false" aria-controls="collapse0${ status.count }">
          ${ faq.title }
        </a>
      </h4>
    </div>
    <div id="collapse0${ status.count }" class="panel-collapse 
    collapse" role="tabpanel" aria-labelledby="heading0${ status.count }">
      <div class="panel-body" style="    color: blue;">
        ${ faq.content }
      </div>
    </div>
  </div>
  </c:forEach>
 </div>
 
 <input type="button" class="btn btn-block btn-lg btn-primary"  value="메인으로" onclick="javascript:location.href='index.mvc';">
 
</article>
</body>