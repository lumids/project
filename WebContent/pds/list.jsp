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
<a href="insertForm.jsp">입력폼</a>
<table border="1">
	<caption>자료실</caption>
	<tr>
		<th>동물 번호</th>
		<th>1차 동물 이름</th>
		<th>2차 동물 이름</th>
		<th>1차 동물 사진</th>
		<th>2차 동물 사진</th>
		<th>동물 인사말 1</th>
		<th>동물 인사말 2</th>
		<th>동물 인사말 3</th>
		<th>동물 최대 스탯 1</th>
		<th>동물 최대 스탯 2</th>
		<th>1차 사진 보기</th>
		<th>2차 사진 보기</th>
	</tr>
<c:forEach var="pi" items="${list}">
	<tr>
		<td>${pi.aniNum}</td>
		<td>${pi.aniName1}</td>
		<td>${pi.aniName2}</td>
		<td>${pi.aniPic1}</td>
		<td>${pi.aniPic2}</td>
		<td>${pi.aniHello1}</td>
		<td>${pi.aniHello2}</td>
		<td>${pi.aniHello3}</td>
		<td>${pi.aniMaxStat1}</td>
		<td>${pi.aniMaxStat2}</td>
		<td><a href="../upload/${pi.aniPic1 }">보기</a></td>
		<td><a href="../upload/${pi.aniPic2 }">보기</a></td>
	</tr>
</c:forEach>
</table>
</div>
</body>
</html>