<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../login/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="body">
<a href="insertItemForm.jsp">입력폼</a>
<table border="1" id="table">
	<caption>자료실</caption>
	<tr>
		<th>아이템 번호</th>
		<th>아이템 이름</th>
		<th>아이템 사진</th>
		<th>아이템 스탯</th>
		<th>아이템 가격</th>
		<th>아이템 보기</th>
	</tr>
<c:forEach var="pi" items="${list}">
	<tr>
		<td>${pi.itemNum}</td>
		<td>${pi.itemName}</td>
		<td>${pi.itemPic}</td>
		<td>${pi.itemStat}</td>
		<td>${pi.itemPrice}</td>
		<td><a href="../upload/${pi.itemPic }">보기</a></td>
	</tr>
</c:forEach>
</table>
</div>
</body>
</html>