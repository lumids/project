<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function update() {
		$.ajax({      
	        type:"POST",  
	        url:"Rwrite.do",      
	        data:{RpageNum:document.getElementById("RpageNum").value,
	        	reBoardNum:document.getElementById("reBoardNum").value,
	        	boardNum:document.getElementById("boardNum").value,
	        	content:document.getElementById("content").value,
	        	id:document.getElementById("id").value},	        	
	        success:function(data){
	        	location.reload(true);
	        }
	    }); 
	}
</script>
<form name="frm">
<input type="hidden" name="RpageNum" id="RpageNum" value="${RpageNum}">
<input type="hidden" name="reBoardNum" id="reBoardNum" value="${reBoardNum}">
<input type="hidden" name="boardNum" id="boardNum" value="${boardNum}">
<input type="hidden" name="id" id="id" value="${id }">

	<input type="text" name="content" id="content">
	<input type="button" value="입력" onclick="update()">

</form>

