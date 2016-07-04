<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이용후기 입력폼</title>

<script src="./assets/js/jquery-1.11.0.min.js"></script>
<script src="./assets/js/jquery.form.min.js"></script>
<script src="./assets/js/jquery.MetaData.js"></script>
<script src="./assets/js/jquery.MultiFile.js"></script>
<script src="./assets/js/jquery.blockUI.js"></script>
<script src="multiupload.js"></script>



<script src="./assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="./assets/bootstrap-3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="./assets/font-awesome-4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="style.css">


<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

</head>

<body>

	

	<section id="review_write_Form_sec">
		<%
			String strV = "";
			try {
		%>
		
		<div class="r_w_f_container">
			<form method="post" name="review_write_Form"
				action="review_writePro.jsp" enctype="multipart/form-data">

				<!-- onsubmit="return review_writeSave()" -->
				<div class="r_w_f_txt">
					<h3>
						<b>이용후기</b>
					</h3>
				</div>
				<br>
				<table class="r_w_f_ta">
					<colgroup>
						<col width="130px;" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>키즈카페명</th>
							<td><select name="cafe_name" id="cafe_name"
								onChange="myFunction(this)">
									<option value="">키즈카페명을 선택해주세요</option>
									<option value="딸기가 좋아">딸기가 좋아</option>
									<option value="키즈팡팡">키즈팡팡</option>
									<option value="키즈와">키즈와</option>
									<option value="어린왕자">어린왕자</option>
									<option value="릴리펏">릴리펏</option>
									<option value="조이랜드">조이랜드</option>
							</select></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input id="writer" type="text" class="input_box"
								name="writer" /></td>
						</tr>
						<tr>
							<th>제목</th>
							<td>
								<%
									if (request.getParameter("req") == null)
											strV = "";
										else
											strV = "[답변]";
								%><input id="title" type="text" name="subject"
								class="input_box_name" value="<%=strV%>"
								style="ime-mode: active;">
						</tr>
						<tr>
							<th>평점</th>
							<td><select name="grademark" id="grademark"
								onChange="myFunction(this)">
									<option value="">평점을 선택해주세요</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
							</select></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="r_w_f_textarea" name="content"
									id="content"></textarea></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input id="reviewpasswd" type="password" name="passwd"
								class="input_box" /><span> 수정 또는 삭제시 반드시 필요 합니다.</span></td>
						</tr>

						<tr>
							<th>첨부파일</th>
							<td>

								<div id="form">
									<ul>
										<li style="margin-top:28px; margin-left: -32px;"><input type="file"
											id="file1" name="file1" onchange="readURL(this,'UploadImg1');"
											style="position: absolute; width: 100px; height: 100px; opacity: 0; cursor: pointer;" />
											<input type="hidden" id="hfile1" name="hfile1"> <img
											id="UploadImg1" src="http://dummyimage.com/600x400/ddd/fff"
											width="100px" height="100px"></li>

									 <li style="margin-top:-100px; margin-left:80px;"><input type="file"
											id="file2" name="file2" onchange="readURL(this,'UploadImg2');"
											style="position: absolute; width: 100px; height: 100px; opacity: 0; cursor: pointer;" />
											<input type="hidden" id="hfile2" name="hfile2"> <img
											id="UploadImg2" src="http://dummyimage.com/600x400/ddd/fff"
											width="100px" height="100px"></li>

										 <li style="margin-top:-100px; margin-left: 193px;"><input type="file"
											id="file3" name="file3" onchange="readURL(this,'UploadImg3');"
											style="position: absolute; width: 100px; height: 100px; opacity: 0; cursor: pointer;" />
											<input type="hidden" id="hfile3" name="hfile3"> <img
											id="UploadImg3" src="http://dummyimage.com/600x400/ddd/fff"
											width="100px" height="100px"></li>  
									</ul>
								</div>
							</td>
						</tr>
					</tbody>
				</table>

				<br>
				<div class="r_w_f_btn">
					<input type="submit" id="upPro1" class="btn btn-default" value="확인">
					<button id="btn" onclick="reviewForm.jsp" ontype="button" class="btn btn-default">목록</button>

				</div>

				<script src="script.js"></script>
				<%
					} catch (Exception e) {
					}
				%>
			
		</div>
	</section>


</body>

<script>
function readURL(input,id) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#'+id).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

</script>

</html>