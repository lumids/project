<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="../assets/bootstrap-3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="../style.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<title>Insert title here</title>
</head>
<body>
<%
	if(!id.equals("master")||id==null){%>
	<script type="text/javascript">
		alert('권한이 없습니다.');
	</script>
	<%
		response.sendRedirect("../login/main.jsp");
	}
%>
	
	<div id="body">
	<a href="list.action">자료실</a>	
		<div align="center">
			<form action="upload.action" enctype="multipart/form-data"
				method="post">
				<table border="1">
					<tr>
						<th>포켓몬 입력</th>
					<tr>
						<td>포켓몬 1차 이미지</td>
						<td>

							<div id="form">
								<ul>
									<li style="margin-top: 28px; margin-left: -32px;"><input
										type="file" id="file1" name="aniFileName1"
										onchange="readURL(this,'UploadImg1');"
										style="position: absolute; width: 100px; height: 100px; opacity: 0; cursor: pointer;" />
										<input type="hidden" id="hfile1" name="aniFileName1">
										<img id="UploadImg1"
										src="http://dummyimage.com/600x400/ddd/fff" width="100px"
										height="100px"></li>
								</ul>
							</div>
						</td>

						<td>포켓몬 2차 이미지</td>
						<td>
							<div id="form" style="margin-left: -112px; margin-top: 130px;">
								<ul>
									<li style="margin-top: -100px; margin-left: 80px;"><input
										type="file" id="file2" name="aniFileName2"
										onchange="readURL(this,'UploadImg2');"
										style="position: absolute; width: 100px; height: 100px; opacity: 0; cursor: pointer;" />
										<input type="hidden" id="hfile2" name="aniFileName2">
										<img id="UploadImg2"
										src="http://dummyimage.com/600x400/ddd/fff" width="100px"
										height="100px"></li>


								</ul>
							</div>

						</td>
					</tr>
					<tr>
						<td>포켓몬 1차 이름</td>
						<td><input type="text" name="aniName1" class="form-control"></td>
						<td>포켓몬 2차 이름</td>
						<td><input type="text" name="aniName2" class="form-control"></td>
					</tr>
					<tr>
						<td>포켓몬 인사말</td>
						<td><input type="text" name="aniHello1" class="form-control"></td>
						<td><input type="text" name="aniHello2" class="form-control"></td>
						<td><input type="text" name="aniHello3" class="form-control"></td>
					</tr>
					<tr>
						<td>포켓몬 최대 스탯</td>
						<td><input type="number" name="aniMaxStat1"
							class="form-control"></td>
						<td><input type="number" name="aniMaxStat2"
							class="form-control"></td>
					</tr>
					<tr>
						<td>포켓몬 가격</td>
						<td><input type="number" name="aniPrice"
							class="form-control"></td>
						<td>레어 포켓몬 체크</td>
						<td><input type="radio" name="speChk" value="y">예
						<input type="radio" name="speChk" value="n">아니오</td>
					</tr>
					<tr>
						<td align="center" colspan="4"><input type="submit"
							value="확인" class="btn btn-primary"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<script>
	function readURL(input, id) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#' + id).attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
</html>