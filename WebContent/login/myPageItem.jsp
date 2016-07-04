<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function pop() {
		window.open("itemUse.do?itemDetailNum="+frm.itemDetailNum.value+"&id="+
				frm.id.value+"&itemNum="+frm.itemNum.value,"","width='300' height='300'");
	}
</script>
</head>
<body>
<form name="frm">
<div id="body">
<div id="stat">
<input type="hidden" name="itemDetailNum" value="${itemDetailNum }">
<input type="hidden" name="itemNum" value="${itemNum }">
<input type="hidden" name="id" value="<%=id%>">
	<img src="../upload/${iif.getItemPic() }" id="pic">
	<br>
	아이템 이름 : ${iif.getItemName()}<br>
	<br>
	스탯 : ${iif.getItemStat() }<br>
	<input type="button" value="사용하기" onclick="pop()" class="btn">
</div>	
</div>
</div>
</form>
</body>
</html>