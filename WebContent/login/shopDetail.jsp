<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int aniNum = Integer.parseInt(request.getParameter("aniNum"));
%>
<div id="body">
	<%@include file="header.jsp"%>
	<%
		AniInfoDao aid = AniInfoDao.getInstance();
		AniInfo ai = aid.selectImg(aniNum);
	%>
	<form action="buy.do">
	<figure align="center">
		<img src="../upload/<%=ai.getAniPic1()%>" width="300">
		<figcaption><%=ai.getAniName1()%></figcaption>
	</figure>
	포켓몬 이름 : <input type="text" name="aniNaming">
	<input type="hidden" name="id" value="<%=id%>">
	<input type="hidden" name="aniNum" value="<%=ai.getAniNum()%>"><br>
	<input type="hidden" name="id" value="<%=id%>">
	가격 :<%=ai.getAniPrice()%>
	 <input type="hidden" name="aniPrice" value="<%=ai.getAniPrice()%>">
	<div align="center">
		<input type="submit" value="구매"  class="btn btn-info btn-block">
	</div>
	</form>
</div>
</body>
</html>