<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result>0}">
   <script type="text/javascript">
      alert("입력성공"); 
      location.href="view.do?boardNum=${boardNum}&RpageNum=${RpageNum}";   
   </script>
</c:if>
<c:if test="${result==-5}">
   <script type="text/javascript">
      alert("경고횟수가 누적되어 댓글을 입력할 수 없습니다."); 
      location.href="view.do?boardNum=${boardNum}&pageNum=${pageNum}"; 
   </script>
</c:if>
</body>
</html>