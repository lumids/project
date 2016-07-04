<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="js/jquery.js"></script>
<script src="http://code.jquery.com/ui/1.12.0-rc.1/jquery-ui.js"></script>
<style type="text/css">
#droppable {
	width: 150px;
	height: 150px;
	padding: 0.5em;
	float: right;
	margin: 10px;
	border: 1px solid red;
}
</style>
<script>
	$(document).ready(
			function() {
				$('.draggable').draggable({ containment: "#fImg", scroll: false });
				$("#droppable").droppable(
				{
					drop : function(event, ui) {
						$(this).addClass("ui-state-highlight");
						
					}
				});
	});
	function updateHello(id) {
		$.ajax({      
	        type:"POST",  
	        url:"updateHello.do",      
	        data:{id:id,
	        	farmHello:$('#farmHello').val()},      
	        success:function(data){   
	            location.reload(true);
	        }
	    }); 
	}
	function updatePop(farmNum,id) {
		$.ajax({      
	        type:"POST",  
	        url:"updatePop.do",      
	        data:{id:id,
	        	farmNum:farmNum},      
	        success:function(data){   
	            location.reload(true);
	        }
	    }); 
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="body">
		${owner }님 농장
		<c:if test="${fi.getFarmNum() == 0 }">
			<br>
			<h3 style="text-align: center">아직 농장이 개설되기 전입니다.</h3>
		</c:if>
		<c:if test="${fi.getFarmNum() >0 }">
			<div id="fContent"
				style="background: ${fi.getFarmBGCol()};height:500px;padding:10px">
				<div id="droppable" class="ui-widget-header">
					<p>여기로</p>
				</div>
				<div id="fImg"
					style="height: 350px; border: 1px solid gray; background:white;">
					
					<c:forEach items="${list }" var="ao">					
					<c:set value="${aid.selectOne(ao.getAniNum())}" var="a" />
						<div class="draggable" class="ui-widget-content"
							style="width: 0; display: inline">
							<figure>
								<figure>
								
									<c:if
										test="${ao.getNowStat1()>=a.getAniMaxStat1() && ao.getNowStat2()>=a.getAniMaxStat2()}">
										<a
											href="myPageAni.do?ownNum=${ao.getOwnNum() }&owner=${ao.getId()}"><img
											src="../upload/${a.getAniPic2()}" width="100"></a>
										<figcaption>${ao.getAniNaming()}</figcaption>
									</c:if>
									<c:if
										test="${ao.getNowStat1()<a.getAniMaxStat1() ||ao.getNowStat2()<a.getAniMaxStat2() }">
										<a
											href="myPageAni.do?ownNum=${ao.getOwnNum() }&owner=${ao.getId()}"><img
											src="../upload/${a.getAniPic1()}" width="100"></a>
										<figcaption>${ao.getAniNaming()}</figcaption>
									</c:if>
									<c:if test="${ao.leadChk=='y ' }">
										<div class="bold">리더</div>
									</c:if>
								</figure>
							</figure>
						</div>
					</c:forEach>					
				</div>
				<div style="background: white;">추천수 : ${fi.getFarmPop() } 농장
					조회수 : ${fi.getFarmCount() }</div>
				<c:if test="${owner != id }">
					<input type="button" value="추천하기">
				</c:if>
				<div style="width: 100%; background: white">
					농장인사말:
					<c:if test="${owner == id }">
						<input type="button" value="수정하기" class="btn btn-info btn-sm"
							style="float: right" onclick="updateHello('${owner}')">
					</c:if>
					<c:if test="${owner != id }">
						<input type="button" value="추천하기" class="btn btn-info btn-sm"
							style="float: right" onclick="updatePop(${fi.getFarmNum()},'${owner}')">
					</c:if>
					<br>
					<textarea id="farmHello" name="farmHello" style="width: 100%" class="form-control">${fi.getFarmHello() }</textarea>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>