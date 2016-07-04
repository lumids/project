<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="sessionChk.jsp"  %>
<link rel="stylesheet" href="../style.css" type="text/css">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="../assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="../assets/bootstrap-3.3.5/css/bootstrap.min.css">
	
<script src="../assets/js/jquery-2.1.4.min.js"></script>

<div style="background-color: pink; height:60px;">
<div id="body">
<a href="<%=request.getContextPath() %>/login/main.jsp">메인</a>
<%
	if(id!=null){%>
	<div align="right">
		<a href="myFarm.do?id=<%=id%>">내 농장</a>
		<a href="shopItem.jsp">아이템 상점</a>
		<a href="shop.jsp">동물 상점</a>		
		<a href="event.do">이벤트</a>
		<a href="aniCenter.do">분양소</a>
		<a href="msg.do?id=<%=id%>">쪽지</a>	
		<a href="list.do">게시판</a>
		<%=id %>님 안녕하세요~<a href="myPage.do?id=<%=id %>">▼</a>	
		<input type="button" value="로그아웃" class="btn-sm" onclick="location.href='<%=request.getContextPath()%>/login/logout.jsp'">	
	</div>
	<%}else{ %>
	<div>
		<div align="right">
			<a href="loginForm.jsp">로그인</a>
			<a href="joinForm.jsp">회원가입</a>
		</div>
	</div>
	<%} %>
</div>
</div>