<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="tot" value="8" />
		<c:forEach items="${list }" var="list">
			<c:if test="${tot>0 }">
				<div style="float:left; vertical-align: bottom; height:100px; margin:10px; padding:1px;border:1px solid blue;">
				<c:set var="farm" value="${fif.selectOne(list.getId()) }" />
				${farm.getFarmName() }<br><br>
				추천수 : ${farm.getFarmPop() }<br><br>
				<input type="button" value="농장 방문" onclick="location.href='farm.do?id=${list.getId()}'">
				</div>
			</c:if>
		</c:forEach>
</body>
</html>