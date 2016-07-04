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
function processRep(id,reportReaNum,chk,reBoardNum) {
	$.ajax({      
        type:"POST",  
        url:"processRep2.do",      
        data:{id:id,
        	reportReaNum:reportReaNum,
        	chk:chk,
        	reBoardNum:reBoardNum},	        	
        success:function(data){
        	alert("신고 처리 되었습니다.");
        	location.reload(true);
        	window.close();
        }
    }); 
}
</script>
</head>
<body>
<c:set var="a" value="${rrd.getValue(reportReaNum) }"/>
<c:set var="warn1" value="${md.getWarn(reporterId) }"/>
<c:set var="warn2" value="${md.getWarn(reportedId) }"/>
신고 하는 사람  : ${reporterId }<br>
경고횟수 :${warn1 }<br><br>
신고 당하는 사람 :  ${reportedId }<br>
경고횟수 :${warn2 }<br><br>
신고 내용 : ${content }<br>
신고 사유 :${a }<br>
<input type="button" value="신고한 사람 처리" onclick="processRep('${reporterId }',${reportReaNum },'n',${reBoardNum })">
<input type="button" value="신고 당한 사람 처리" onclick="processRep('${reportedId }',${reportReaNum },'y',${reBoardNum })">
</body>
</html>