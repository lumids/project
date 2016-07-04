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
function sendMsg (receiveId,sendId) {
    window.open("msgPop.do?sendId="+sendId+"&receiveId="
  		  +receiveId,"", 'width=' + 400 + 'height=' + 400);
    opener.location.reload(true);
}
function closeMsg() {
	window.close();
}
</script>
</head>
<body>
<table>
	<tr>
		<td>보낸 시간</td>
		<td>${msg.getSendTime() }</td>
	</tr>
	<tr>
		<td>읽은 시간</td>
		<c:if test="${msg.getReceiveTime() != null}">
			<td>${msg.getReceiveTime() }</td>
		</c:if>
		<c:if test="${msg.getReceiveTime() == null}">
			<td>아직 안 읽음</td>
		</c:if>
	</tr>
	<tr>
		<td>받는 사람</td>
		<td><input type="text" name="receiveId" value="${msg.getReceiveId() }" readonly="readonly"></td>
	</tr>
	<tr>
		<td>보내는 사람</td>
		<td><input type="text" name="sendId" value="${msg.getSendId() }" readonly="readonly"></td>
	</tr>
	<tr>
		<td>쪽지 제목</td>
		<td><input type="text" name="msgSubject" value="${msg.getMsgSubject() }" readonly="readonly"></td>
	</tr>
	<tr>
		<td>쪽지 내용</td>
		<td><textarea rows="7" cols="40" name="msgContent" readonly="readonly">${msg.getMsgContent() }</textarea></td>
	</tr>
	<tr>
	<c:if test="${id ne msg.getSendId()}">
		<td><input type="button" value="답장" onclick="sendMsg('${msg.getSendId() }','${msg.getReceiveId() }')"></td>
	</c:if>
	<c:if test="${id eq msg.getSendId()}">
		<td><input type="button" value="답장" onclick="sendMsg('${msg.getReceiveId() }','${msg.getSendId() }')"></td>
	</c:if>
		<td><input type="button" value="확인" onclick="closeMsg()"></td>
	</tr>
</table>	
</body>
</html>