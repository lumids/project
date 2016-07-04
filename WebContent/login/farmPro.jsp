<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="body">
	<c:if test="${result ==-1 }">
	<script type="text/javascript">
		alert("아직 농장이 개설되기 전입니다.");
		location.href="readyFarm.jsp?id=${id}";
	</script>
	</c:if>
	<c:if test="${result !=-1 }">
	<script type="text/javascript">
		location.href="farm.do?id=${id}";
	</script>
	</c:if>
</div>
</body>
</html>