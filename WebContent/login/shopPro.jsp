<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="body">
		<%@include file="header.jsp"%>
		<c:if test="${result==55 }">
			<script type="text/javascript">
				alert("돈이 부족해 현재포인트 : ${param.point}");
				history.go(-1);
			</script>
		</c:if>
		<c:if test="${result>0}">
			<input type="button" value="포켓몬 더보기" class="btn" onclick="location.href='shop.jsp'">
			<br>
			<input type="button" value="구매 포켓몬 확인" class="btn" onclick="location.href='../login/myPage.do?id=<%=id %>'">			
		</c:if>
		<c:if test="${result<=0 }">
			<script type="text/javascript">
				alert("잘해");
				history.go(-1);
			</script>
		</c:if>
	</div>
</body>
</html>