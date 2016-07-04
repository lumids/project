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
		<h2>아이템 구매</h2>
		<%
			ItemInfoDao iid = ItemInfoDao.getInstance();
			List<ItemInfo> list = iid.selectAll();
			if (list != null) {
			for (ItemInfo ii : list) {%>
			<a href="shopItemDetail.jsp?itemNum=<%=ii.getItemNum()%>">
			<div style="float:left; text-align:center;margin:'200px;'">
			<figure>
				<img src="../upload/<%= ii.getItemPic()%>">
				<figcaption><%=ii.getItemName()%> </figcaption>
			</figure>
			</div>
			</a>	
		<%}
		}%>
	</div>
</body>
</html>