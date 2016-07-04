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
	<form action="makeFarm.do">
	<input type="hidden" name="id" value="${id }">
	<table class="table">
		<tr>
			<td>농장 이름</td>
			<td><input type="text" name="farmName" value="${id }님의 농장"></td>
		</tr>
		<tr>
			<td>농장 인사말</td>
			<td>
				<textarea rows="5" cols="40" name="farmHello"></textarea>
			</td>
		</tr>
		<tr>
			<td>농장 배경색</td>
			<td>
				<select name="farmBGCol">
					<option value="skyblue">하늘</option>
					<option value="yellow">노랑</option>
					<option value="maroon">빨강</option>
					<option value="pink">분홍</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="농장 개설"></td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>