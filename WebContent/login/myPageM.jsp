<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../style.css">

<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function processRep(reportReaNum,reporterId,reportedId,content,reBoardNum) {
			 window.open("processRep.do?reportReaNum="+reportReaNum+"&reporterId="
	    		  +reporterId+"&reportedId="+reportedId+"&content="+content+"&reBoardNum="+reBoardNum
	    		  ,"","width='400' height='400'");
	}
</script>
</head>
<body>
	<div id="body">
		
		<div id="tab" class="btn-group" data-toggle="buttons-radio"
			style="margin: 0 auto;">
			<ul class="nav nav-pills nav-justified"
				style="background-color: white;">
				<li class="active"><a href="#contact2" data-toggle="tab">포켓몬
						입력</a></li>
				<li><a href="#prices2" data-toggle="tab">아이템 입력</a></li>
				<li><a href="#requests2" data-toggle="tab">신고 사유</a></li>
				<li><a href="#requests3" data-toggle="tab">신고 처리</a></li>
				<li><a href="#requests4" data-toggle="tab">이벤트 관리</a></li>
			</ul>
		</div>
		<div class="tab-content">
			<div class="tab-pane fade active in" id="contact2">
				<br>
				<div id="regForm" class="box" align="center">
					<h2>포켓몬 입력</h2>
					<input type="button" value="입력" class="btn btn-primary"
						onclick="location.href='../pds/insertForm.jsp'">
				</div>
			</div>
			<div class="tab-pane fade in" id="prices2">
				<br>
				<div align="center">
				<h2>아이템 입력</h2>
				<input type="button" value="입력" class="btn btn-primary"
						onclick="location.href='../pds/insertItemForm.jsp'">
				</div>
			</div>
			<div class="tab-pane fade in" id="features2">
				<br>
				<div align="center">
					<h2>포인트 내역</h2>
					<table class="table table-striped">
						<tr>
							<td>변화 포인트</td>
							<td>사유</td>
							<td>시간</td>
						</tr>

					</table>
				</div>
			</div>
			<div class="tab-pane fade in" id="requests2">
				<br>
				<div>
					<h2>신고</h2>
					<table class="table table-striped">
						<tr>
							<td>신고 번호</td>
							<td>신고 사유</td>	
							<td></td>					
						</tr>
					<c:if test="${list eq '[]' }">
						신고 사유가 없습니다.
					</c:if>
					<c:if test="${list ne '[]'}">					
						<c:forEach items="${ list}" var="report">
							<tr>
								<td>${report.getReportReaNum() }</td>
								<td>${report.getReportReaVal() }</td>
								<td><input type="button" value="삭제"></td>
							</tr>
						</c:forEach>
					</c:if>
					<form action="addReason.do">
					<tr>					
						<td>신고 사유 추가</td>
						<td><input type="text" name="reportReaVal"></td>
						<td><input type="submit" value="추가"></td>					
					</tr>
					</form>
					</table>
				</div>
			</div>
			<div class="tab-pane fade in" id="requests3">
				<br>
				<div>
					<h2>신고 처리</h2>
					<table class="table table-striped">						
					<c:if test="${list2 eq '[]' }">
						<h2>처리 안한 신고가 없습니다.</h2>
					</c:if>
					<c:if test="${list2 ne '[]'}">
						<tr>
							<td>신고 처리 번호</td>
							<td>신고 사유</td>
							<td>신고한 사람</td>
							<td>신고당한 사람</td>
							<td>신고한 날짜</td>
							<td>상세</td>					
						</tr>					
						<c:forEach items="${ list2}" var="report">						
						<c:set var="r" value="${rrd.getValue(report.getReportReaNum()) }"/>
						
							<tr>
								<td>${report.getRepReaNum() }</td>
								<td>${r }</td>
								<td>${report.getReporterId() }</td>
								<td>${report.getReportedId() }</td>
								<td>${report.getRepDate() }</td>
								<td><input type="button" value="상세" onclick="processRep(
									${report.getReportReaNum() },'${report.getReporterId() }','${report.getReportedId() }','${report.getContent() }',${report.getReBoardNum() })"></td>
							</tr>
						</c:forEach>
					</c:if>					
					</table>
				</div>
			</div>
			<div class="tab-pane fade in" id="requests4">
				<br>
				<div>
					<h2>이벤트 처리</h2>
					<table class="table table-striped">		
					<c:if test="${list2 ne '[]'}">
						<tr>
							<td>이벤트 번호</td>
							<td>아이템 사진</td>
							<td>아이템 가격</td>
							<td>진행 여부</td>
							<td>개최/중지</td>					
						</tr>					
						<c:forEach items="${ list2}" var="report">						
						<c:set var="r" value="${rrd.getValue(report.getReportReaNum()) }"/>
						
							<tr>
								<td>${report.getRepReaNum() }</td>
								<td>${r }</td>
								<td>${report.getReporterId() }</td>
								<td>${report.getReportedId() }</td>
								<td>${report.getRepDate() }</td>								
							</tr>
						</c:forEach>						
					</c:if>			
					<input type="button" value="이벤트 열기" onclick="location.href='../pds/openEventForm.jsp'" class="btn btn-lg">		
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>