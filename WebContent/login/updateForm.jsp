<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="comm.css" rel="stylesheet" type="text/css">
</head>
<body>

<form action="updatePro.do" method="post">
	<input type="hidden" name="boardNum" value="${ board.getBoardNum()}">
	<input type="hidden" name="pageNum" value="${pageNum}">
<div id="body">
<table border="1" class="table">
<caption>게시판 수정</caption>
	<tr>
		<th>제목</th>
		<td>
			<input type="Text" name="subject" required="required"
			 autofocus="autofocus" value="${board.getSubject()}" >
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="Text" name="writer" readonly="readonly"  value="${board.getId()}">
		</td>
	</tr>	
	<tr>
		<th>내용</th>
		<td>
			<pre><textarea rows="5" cols="50" name="content" required="required">${board.getContent()}</textarea></pre>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="확인">
		</td>
	</tr>
</table>
</div>
</form>
</body>
</html>