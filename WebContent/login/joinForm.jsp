<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<link rel="stylesheet"
	href="../assets/font-awesome-4.4.0/css/font-awesome.min.css">
	
<script src="../assets/js/jquery-2.1.4.min.js"></script>
<script src="../assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
function idchk() {
	window.open("idchk.jsp?id="+frm.id.value,"","width='300' height='300'");
}
function chk() {
	if(frm.hide.value==0){
		alert("아이디 중복 체크하세요.")
		return false;
	}
	if(frm.aniNum.value==0){
		alert("스타트 동물 고르세요.")
		return false;
	}
	return true;
}
function aniSelect() {
	window.open("aniSelect.jsp","","width='300' height='300'");
}
function mapSelect() {
	window.open("mapChk.html","","width='300' height='300'");
}
</script>
</head>
<body>
<div id="body">
	<div align="center">
	<form name="frm" action="join.do" method="post" onsubmit="return chk()">
	<table border="" class="table">
		<tr>		
			<th>아이디</th>
			<td><input type="text" name="id" required="required">
				<input type="button" onclick="idchk()" value="중복 체크">
				 <input type="hidden" name="hide" value="0">
			</td>
		</tr>
		<tr>		
			<th>비밀번호</th>
			<td><input type="password" name="passwd" required="required"></td>
		</tr>
		<tr>		
			<th>닉네임</th>
			<td><input type="text" name="nickname" required="required">				
			</td>
		</tr>
		<tr>
			<th>포켓몬 선택</th>
			<td>				
				<input type="text" name="aniNaming" value="포켓몬 이름을 지어주세요">
				<input type="button" onclick="aniSelect()" value="선택">
				<input type="hidden" name="aniNum" value="0">
				<img alt="" src="" name="aniPic1">
			</td>
		</tr>
		<tr>
			<th>시작 지역 선택</th>
			<td>				
				<input type="text" name="x" id="x">
				<input type="text" name="y" id="y">
				<input type="button" onclick="mapSelect()" value="선택">
				<input type="hidden" name="mapChk" value="1">
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="회원가입" class="btn"></td>			
		</tr>
	</table>
	</form>	
	</div>
</div>
</body>
</html>