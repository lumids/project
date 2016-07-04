<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../login/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="body">
<form action="write.do" method="post">
<input type="hidden" name="boardNum" value="${ boardNum}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="re_step" value="${re_step}">
<input type="hidden" name="re_level" value="${re_level}">
<input type="hidden" name="pageNum" value="${pageNum}">
<table border="1" class="table">
<caption>게시판 작성</caption>
	<tr>
		<th>제목</th>
		<td>
			<input type="Text" name="subject" required="required" autofocus="autofocus">
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="Text" name="id" readonly="readonly" value="<%=id%>">
		</td>
	</tr>
	<tr>
		<th>포켓몬 분류</th>
		<td>
			<select name="aniNum">
			<c:forEach items="${list}" var="ani">
				<option value="${ani.getAniNum() }">${ani.getAniName1() }</option>				
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea rows="5" cols="50" name="content" required="required"></textarea>
		</td>
	</tr>
	<c:if test="${id eq 'master' }">
	<tr>
		<th>공지</th>
		<td>
			<input type="radio" name="noticeChk" value="y" id="c1"><label for="c1">예</label>
			<input type="radio" name="noticeChk" value="n" id="c2"><label for="c2">아니오</label>
		</td>
	</tr>
	</c:if>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="확인">
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>