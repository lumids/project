<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../style.css">
<script type="text/javascript">
	  function init() {
		/*  $.ajax({      
		        type:"POST",  
		        url:"map.do",
		        success:function(data){  
		            $("#mapRes").html(data);
		        }
		    });  */
		  $.ajax({      
		        type:"POST",  
		        url:"aniRank.do",
		        success:function(data){  
		            $("#aniRankRes").html(data);
		        }
		    }); 
		  $.ajax({      
		        type:"POST",  
		        url:"farmRank.do",
		        success:function(data){  
		            $("#farmRankRes").html(data);
		        }
		    });
	} 
</script>
</head>
<body onload="init()">
	<div id="body">
		<div style="min-height: 500px">
			<jsp:include page="map.jsp" flush="false" />
			<!-- <div id="mapRes"></div> -->
			<br><br>
			<h4>스텟 높은 포켓몬</h4>
			<div id="aniRankRes" style="min-height:300px"></div>
			<h4>추천수 높은 농장</h4>
			<div id="farmRankRes" style="min-height:150px"></div>
		</div>		
		<jsp:include page="footer.jsp" flush="false" />
	</div>
</body>
</html>