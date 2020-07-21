<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style >


</style>
</head>
<body>
<%-- ${page.olist} --%>
<%-- ${orderList} --%>
<sec:authentication property="principal.username" var="username"/>
<div>
<div class="container">
   <div style="padding: 40px 0;">
      <h3><i class="fas fa-shopping-cart">&nbsp;주문내역</i></h3>
   </div>
		<table class="table table-hover">
			<colgroup>
				<col width="10%">
				<col width="30%">
				<col width="20%">
				<col width="20%">
				<col width="10%">
				<col width="10%">
				
			</colgroup>
			<thead>
				<tr class="table-danger">
					<th>주문번호</th>
					<th>주문메뉴</th>
					<th>총금액</th>
					<th>주문시간</th>
					<th>사용자아이디</th>
					<th>주문확인여부</th>
				</tr>
			</thead> 
			<tbody id="list">
			<c:forEach items="${page.olist}" var="manager">
				<tr>
					<td>${manager.ONo}</td>
					<td><a href="/cheatingday/manager/order_read?oNo=${manager.ONo}">${manager.orderName}</a></td>
					<td>${manager.OTotal} 원</td>
					<td>${manager.OOrderTimeStr}</td>
					<td>${manager.UUsername}</td>
					<td>
					<c:choose>
						<c:when test="${manager.OCheck==false}">
							<p>접수 대기중</p>					
						</c:when>
					</c:choose>
					</td>
				</tr>
			</c:forEach> 
			</tbody>
		</table>
	</div>
	</div>
	<div style="text-align:center;">
		<ul class="pagination">
			<c:if test="${page.prev==true}">
				<li><a href="/cheatingday/manager/order_list?pageno=${page.startPage-1}&mUsername=${username}">이전</a></li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
				<c:choose>
					<c:when test="${page.pageno eq i }">
						<li class="active" >
							<a style= "background-color:#E74D44; border: 1px solid;" href="/cheatingday/manager/order_list?pageno=${i}&mUsername=${username}">${i}</a>
						</li>
					</c:when>     
					<c:otherwise>
						<li><a href="/cheatingday/manager/order_list?pageno=${i}&mUsername=${username}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.next==true}">
				<li><a href="/cheatingday/manager/order_list?pageno=${page.endPage+1}&mUsername=${username}">다음</a></li>
			</c:if>
		</ul>
	</div>  
	
</body>
</html>