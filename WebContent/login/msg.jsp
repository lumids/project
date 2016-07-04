<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">	
	function readMsg (msgNum,id) {
	      window.open("readMsg.do?msgNum="+msgNum+"&id="+id
	    		  ,"","width='300' height='300'");
	}
	$(document).ready(function() {
		$('#all_check').change(function(event) {
			if (this.checked) {
				$('.child').children().prop('checked', this.checked);
				$('#child').children().attr('checked', 'checked');
			} else {
				$('#child').children().removeAttr('checked');
			}
		});
		$('#all_check2').change(function(event) {
			if (this.checked) {
				$('.child2').children().prop('checked', this.checked);
				$('#child2').children().attr('checked', 'checked');
			} else {
				$('#child2').children().removeAttr('checked');
			}
		});
	});
	function delMsg(delChk) {
		var msgNum = new Array();
		$('input:checkbox[class="check2"]:checked').each(function() {
			msgNum+=$(this).val()+",";			
		});
		$('input:checkbox[class="check1"]:checked').each(function() {
			msgNum+=$(this).val()+",";			
		});
		 $.ajax({			 
		        type:"POST",  
		        url:"delMsg.do",      
		        data:{msgNum:msgNum,
		        	delChk:delChk},      
		        success:function(data){
		        	alert("삭제 되었습니다.");
		            location.reload(true);
		        }
		    }); 
	}
	function sendMsg (sendId) {
	      window.open("msgPop.do?sendId="+sendId,"", 'width=' + 400 + 'height=' + 400);
	}
</script>
</head>
<body>
	<div id="body">
	<div id="tab" class="btn-group" data-toggle="buttons-radio"	style="margin: 0 auto;">
		<ul class="nav nav-pills nav-justified"
			style="background-color: white;">
			<li class="active"><a href="#contact2" data-toggle="tab">받은 	쪽지함</a></li>
			<li><a href="#prices2" data-toggle="tab">보낸 쪽지함</a></li>
		</ul>
	</div>
	<input type="button" value="쪽지보내기" onclick="sendMsg('${id}')">
	<div class="tab-content">
		<div class="tab-pane fade active in" id="contact2" align="center">
			<br>
			<div id="regForm" class="box">
				<h3>받은 쪽지 확인</h3>
				<input type="button" value="삭제" onclick="delMsg('receiveDelChk')">
				<table class="table">
				<tr>
					<th><input type="checkbox" id="all_check" /></th>
					<th>쪽지 번호</th>
					<th>보낸 사람</th>
					<th>보낸 시간</th>
					<th>쪽지 제목</th>
					<th>상세</th>
					<th>삭제</th>
					<th>수신 확인</th>
				</tr>
				<c:set var="tot" value="${total2 }"/>
				<c:forEach items="${list2 }" var="list">							
					<tr>
						<td>
							<div id="child" class="child"><input type="checkbox"  value="${list.getMsgNum() }" class="check1"/></div>
						</td>
						<td>${tot }</td>
						<td>${list.getSendId() }</td>
						<td>${list.getSendTime() }</td>
						<td>${list.getMsgSubject() }</td>
						<td><input type="button" value="읽기" onclick="readMsg(${list.getMsgNum() },'${id}')"></td>
						<td><input type="button" value="삭제"></td>
						<td>
							<c:if test="${list.getReceiveChk() eq 'y ' }">
								읽음
							</c:if>
							<c:if test="${list.getReceiveChk() ne 'y ' }">
								안 읽음
							</c:if>
						</td>
					</tr>
					<c:set var="tot" value="${tot-1 }"/>
				</c:forEach>				
				</table>
				<c:if test="${list2=='[]' }">
					<h3 style="text-align: center">받은 쪽지가 없습니다.</h3>
				</c:if>
			</div>
		</div>
		<div class="tab-pane fade in" id="prices2" align="center">
			<br>
			<h3>보낸 쪽지 확인</h3>
			<input type="button" value="삭제" onclick="delMsg('sendDelChk')">
			<table class="table">
				<tr>
					<th><input type="checkbox" id="all_check2"/></th>
					<th>쪽지 번호</th>
					<th>받은 사람</th>
					<th>보낸 시간</th>
					<th>쪽지 제목</th>
					<th>상세</th>
					<th>삭제</th>
					<th>수신 확인</th>
				</tr>
				<c:set var="tot2" value="${total }"/>
				<c:forEach items="${list }" var="list">
					<tr id="row">
						<td>
							<div id="child2" class="child2"><input type="checkbox" class="check2" value="${list.getMsgNum() }" /></div>
						</td>
						<td>${tot2}</td>
						<td>${list.getReceiveId() }</td>
						<td>${list.getSendTime() }</td>
						<td>${list.getMsgSubject() }</td>
						<td><input type="button" value="읽기" onclick="readMsg(${list.getMsgNum() })"></td>
						<td><input type="button" value="삭제"></td>
						<td>
							<c:if test="${list.getReceiveChk() eq 'y ' }">
								읽음
							</c:if>
							<c:if test="${list.getReceiveChk() ne 'y ' }">
								안 읽음
							</c:if>
						</td>
					</tr>
					<c:set var="tot2" value="${tot2-1 }"/>
				</c:forEach>
				</table>
		</div>
	</div>
	</div>
</body>
</html>