<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function reportAdd() {
		$.ajax({      
	        type:"POST",  
	        url:"reportAdd2.do",      
	        data:{reporterId:frm.reporterId.value,
	        	reportedId:frm.reportedId.value,
	        	reportReaNum:frm.reportReaNum.value,
	        	content:frm.content.value,
	        	reBoardNum:frm.reBoardNum.value},	        	
	        success:function(data){
	        	alert("신고 접수 되었습니다.");
	        	window.close();
	        }
	    }); 
	}
</script>
</head>
<body>
<form name="frm">
<input type="hidden" name="reporterId" value="${reporterId }">
<input type="hidden" name="reportedId" value="${reportedId }">
<input type="hidden" name="content" value="${content }">
<input type="hidden" name="reBoardNum" value="${reBoardNum }">

신고 하는 사람  : ${reporterId }<br>
신고 당하는 사람 :  ${reportedId }<br>
신고 내용 : ${content }<br>
신고 사유 :<br>
<c:forEach items="${list }" var="re">
	<input type="radio" name="reportReaNum" value="${re.getReportReaNum() }" id="${re.getReportReaNum() }">
	<label for="${re.getReportReaNum() }">${re.getReportReaVal() }</label><p>
</c:forEach>
<input type="button" value="신고" onclick="reportAdd()">
</form>
</body>
</html>