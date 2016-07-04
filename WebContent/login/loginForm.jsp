<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../style.css">
<link rel="stylesheet"
	href="../assets/bootstrap-3.3.5/css/bootstrap.min.css">
</head>
<body>
<div id="body">
<div align="center">
<form action="login.do">
<table>
	<tr>
		<td>아이디</td>		
		<td><input type="text" name="id" class="form-control"></td>
	</tr>
	<tr>
		<td>비밀번호</td>		
		<td><input type="password" name="passwd" class="form-control"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="로그인" class="btn btn-primary">
		<input type="button" value="회원가입" class="btn btn-primary" onclick="location.href='joinForm.jsp'"></td>
	</tr>
</table>
</form>
</div>
</div>
</body>
</html>