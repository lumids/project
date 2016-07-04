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
<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("자기 동물은 못 데려가");
		history.go(-1);
	</script>
</c:if>
<c:if test="${result != -1 }">
	<script type="text/javascript">
		alert("데려왔습니다.");
		location.href="myPage.do?id=${id}"
	</script>
</c:if>
</body>
</html>