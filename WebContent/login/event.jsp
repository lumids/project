<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function eventPop(mapItemNum) {
		window.open("eventPop.do?mapItemNum="+mapItemNum,"","width='300' height='300'");
	}
</script>
</head>
<body>
<div id="body">
<c:set var="tot" value='${total }'/>
<c:forEach items="${list }" var="list">
<table class="table">
<tr>
	<th>이벤트 번호</th>
	<th>이벤트 아이템</th>
	<th>아이템 이름</th>
	<th>아이템 가격</th>
	<th>참여하기</th>
<tr>
<tr>
	<c:if test="${list!=null }">	
		<td>${tot }</td>
		<td><img src="../upload/${list.getMapItemPic() }"></td>
		<td>${list.getMapItemName() }</td>
		<td>${list.getMapItemPrice() }</td>
		<td><input type="button" value="참여하기" onclick="eventPop(${list.getMapItemNum()})"></td>
	<c:set var="tot" value='${tot-1 }'/>			
	</c:if>
	</tr>
</table>
	<c:if test="${list==null }">
		개최중인 이벤트가 없습니다.
	</c:if>
</c:forEach>
</div>
</body>
</html>