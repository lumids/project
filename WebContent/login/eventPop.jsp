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
	function init() {
		setZoomable(false);
	}
	function clearEvent(mapItemNum) {
		$.ajax({      
	        type:"POST",  
	        url:"clearEvent.do",      
	        data:{mapItemNum:mapItemNum},	        	
	        success:function(data){
	        	alert('아이템을 획득하셨습니다.');
	        	window.close();
	        }
		});
	}
</script>
</head>
<body onload="init()">
<form name="frm">
<input type="hidden" name="mapItemNum" id="mapItemNum" value="${mi2.getMapItemNum() }">
<input type="hidden" name="mapItemName" id="mapItemName" value="${mi2.getMapItemName() }">
<input type="hidden" name="mapItemPic" id="mapItemPic" value="${mi2.getMapItemPic() }">
<input type="hidden" name="x" id="x" value="${x }">
<input type="hidden" name="y" id="y" value="${y }">
<c:set var="info" value='${info }'/>
<input type="hidden" name="xx" id="xx" value="${info.getX() }">
<input type="hidden" name="yy" id="yy" value="${info.getY() }">
</form>
	<c:if test="${result ==-1 }">
		<script type="text/javascript">
		alert("이미 참여하셨습니다");
		window.close();
		</script>
	</c:if>
	<c:if test="${result !=-1 }">
		<div id="map" style="wdith: 100%; height: 500px"></div>
		<div id="result"></div>
		<div id="result2"></div>
		<div id="result3"></div>
	</c:if>
	<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=ade4d09489216ea20f3334dec9dda931"></script>
	<script type="text/javascript">
	var XX = $('#xx').val();
	var YY =  $('#yy').val();
	var marker = new daum.maps.Marker({
		position : new daum.maps.LatLng(XX, YY)
	});
	marker.setMap(map);
		var container = document.getElementById('map');
		var options = {
			center : new daum.maps.LatLng(XX, YY),
			level : 3
		};
		var map = new daum.maps.Map(container, options);
		var marker = new daum.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		});
		var a = parseFloat(33.450701);
		var b = parseFloat($('#x').val());
		var x = a+b;
		
		var e = parseFloat(126.570667);
		var d = parseFloat($('#y').val());
		var y = e+d;
		var marker2 = new daum.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: new daum.maps.LatLng(x, y) 
		}); 
		// 지도에 마커를 표시합니다
		marker.setMap(map);
		marker2.setMap(map);

		function setZoomable(a) {
		    map.setZoomable(a);    
		}
daum.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    
		    // 마커 위치를 클릭한 위치로 옮깁니다
		    marker.setPosition(latlng);
		    
		    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
		    message += '경도는 ' + latlng.getLng() + ' 입니다';
		    var message2 = '마커의 위도는 ' +x + ' 이고, ';
		    message2 += '경도는 ' +y + ' 입니다';
		    
		    
		    var x2 = parseFloat(latlng.getLat());
		    var x3 = x-x2;
		    
		    var y2 = parseFloat(latlng.getLng());
		    var y3 = y-y2;
		    var message3 = '동쪽으로 '+x3
		    +'만큼 북쪽으로 '+y3;
		    
		    var resultDiv = document.getElementById('result'); 
		    resultDiv.innerHTML = message;
		    var resultDiv2 = document.getElementById('result2'); 
		    resultDiv2.innerHTML = message2;
		    var resultDiv3 = document.getElementById('result3'); 
		    resultDiv3.innerHTML = message3;
		    
		    
});
var mapItemPic = $('#mapItemPic').val();
var mapItemName= $('#mapItemName').val();
var mapItemNum= $('#mapItemNum').val();
var locations = [
	 				[ 33.4507011, 126.5706678,
	 						"<img src='../upload/"+mapItemPic+"'>"+mapItemName+"</b>" ]];
daum.maps.event.addListener(marker2,'click',(function(marker2, i) {return function() {
	var infowindow = new daum.maps.InfoWindow({
				content : '<div style="width:280px; height:100px;"><p style="padding:10px; font-size:12px;">'
						+ locations[0][2]
						+ '</p><div style="width: 88px; height: 30px; border: 1px solid #555; padding: 5px; margin-left: 15px; margin-top: -120px;"><a href="javascript:clearEvent('+mapItemNum+')">아이템 획득</a></div></div>',
			
				removable : true
			});
	infowindow.open(map, marker2);
}
})(marker2));
	</script>
</body>
</html>