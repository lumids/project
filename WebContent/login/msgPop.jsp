<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function sendMsg() {
		$.ajax({      
	        type:"POST",  
	        url:"sendMsg.do",      
	        data:{receiveId:frm.receiveId.value,
	        	sendId:frm.sendId.value,
	        	msgSubject:frm.msgSubject.value,
	        	msgContent:frm.msgContent.value},	        	
	        success:function(data){
	        	alert("쪽지가 전송 되었습니다.");
	        	window.close();
	        }
	    }); 
	}
</script>
</head>
<body>
<form name="frm">
<table>
	<tr>
		<td>받는 사람</td>
		<c:if test="${receiveId != null}">
			<td><input type="text" name="receiveId" value="${receiveId }" required="required"></td>
		</c:if>
		<c:if test="${receiveId == null}">
			<td><input type="text" name="receiveId" required="required"></td>
		</c:if>
		<td></td>
	</tr>
	<tr>
		<td>보내는 사람</td>
		<td><input type="text" name="sendId" value="${sendId }" readonly="readonly"></td>
	</tr>
	<tr>
		<td>쪽지 제목</td>
		<td><input type="text" name="msgSubject"></td>
	</tr>
	<tr>
		<td>쪽지 내용</td>
		<td><textarea rows="7" cols="40" name="msgContent"></textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" value="전송" onclick="sendMsg()"></td>
	</tr>
</table>
</form>
</body>
</html>