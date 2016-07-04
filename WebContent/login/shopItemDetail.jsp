<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int itemNum = Integer.parseInt(request.getParameter("itemNum"));
%>
<div id="body">
	<%@include file="header.jsp"%>
	<%
		ItemInfoDao iid = ItemInfoDao.getInstance();
		ItemInfo ii = iid.selectImg(itemNum);
	%>
	<form action="buy2.do">
	<figure align="center">
		<img src="../upload/<%=ii.getItemPic()%>" width="300">
		<figcaption>
		아이템 이름 : <%=ii.getItemName()%><br>
		아이템 가격 : <%=ii.getItemPrice()%><br>
		아이템 스탯 : <%=ii.getItemStat()%><br>
		</figcaption>		
	</figure>	
	<input type="hidden" name="id" value="<%=id%>">
	<input type="hidden" name="itemNum" value="<%=ii.getItemNum()%>">
	<input type="hidden" name="itemPrice" value="<%=ii.getItemPrice()%>">
	<div align="center">
		<input type="submit" value="구매"  class="btn btn-info btn-block">
	</div>
	</form>
</div>
</body>
</html>