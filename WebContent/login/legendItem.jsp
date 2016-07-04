<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="body">
	<div id="stat">
		<figure>			
				<img src="../upload/${item.getMapItemPic() }">			
			<figcaption>아이템 이름 : ${item.getMapItemName() }<br>
			가격 : ${item.getMapItemPrice() }</figcaption>
			<input type="button" value="팔기" class="btn btn-info"
			onclick="location.href='sellItem.do?mapItemNum=${item.getMapItemNum() }&id=${id}'">
			<input type="button" value="목록" class="btn btn-primary"
				onclick="location.href='myPage.do?id=${id}'">		
		</figure>
		</div>		
	</div>
</body>
</html>