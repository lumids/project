<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="body">
		<%@include file="header.jsp"%>
		<h2>포켓몬 구매</h2>
		<%
			AniInfoDao aid = AniInfoDao.getInstance();
			List<AniInfo> list = aid.selectAll("all");
			if (list != null) {
			for (AniInfo ai : list) {%>
			<a href="shopDetail.jsp?aniNum=<%=ai.getAniNum()%>">
			<div style="float:left; margin:'200px;'">
			<figure>
				<img src="../upload/<%= ai.getAniPic1()%>">
				<figcaption><%=ai.getAniName1()%></figcaption>
			</figure>
			</div>
			</a>	
		<%}
		}%>
	</div>
</body>
</html>