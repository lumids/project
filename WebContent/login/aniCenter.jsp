<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="body">
		<h2>동물 분양소</h2>
		<table class="table">
			<c:forEach items="${list }" var="list">
				<c:set var="name" value="${aid.selectOne(list.getAniNum()) }" />
				<tr><td colspan="2"><c:if test="${list.getNowStat1()>=name.getAniMaxStat1() && list.getNowStat2()>=name.getAniMaxStat2()}">
							<img src="../upload/${name.getAniPic2()}" width="100">
						</c:if> <c:if test="${list.getNowStat1()<name.getAniMaxStat1() ||list.getNowStat2()<name.getAniMaxStat2() }">
							<img src="../upload/${name.getAniPic1()}" width="100">
						</c:if></td>
					<td>포켓몬명</td>
					<td>${name.getAniName1()}</td>
					<td>이름</td>
					<td>${list.getAniNaming() }</td>
					<td>스탯1</td>
					<td><progress max=${name.getAniMaxStat1() } style="width: 100px" value=${list.getNowStat1()}></progress>${list.getNowStat1()}</td>
					<td>스탯2</td>
					<td><progress max=${name.getAniMaxStat2() }style="width: 100px" value=${list.getNowStat2()}></progress>${list.getNowStat2()}</td>
					<td><input type="button" value="데려가기" onclick="location.href='pickAni.do?id=${id}&ownNum=${list.getOwnNum() }'"></td></tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>