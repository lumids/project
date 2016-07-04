<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dao.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Daum 지도 시작하기</title>
<link rel="stylesheet" href="../style.css" type="text/css">

<script type="text/javascript" src="../js/jquery.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript">
	function a(id,x,y,pic) {
		var locations = [
			 				[ x, y,
			 						"<img src='../upload/"+pic+"'><b>"+id+"님 농장</b>" ]];
			 var moveLatLon = new daum.maps.LatLng(x, y);         
		     // 지도 중심을 이동 시킵니다
		     map.setCenter(moveLatLon);
		     var marker = new daum.maps.Marker({
					position : new daum.maps.LatLng(x, y)
				});
				marker.setMap(map);
		daum.maps.event.addListener(marker,'click',(function(marker, i) {return function() {
								var infowindow = new daum.maps.InfoWindow({
											content : '<div style="width:280px; height:100px;"><p style="padding:10px; font-size:12px;">'
													+ locations[0][2]
													+ '</p><div style="width: 78px; height: 30px; border: 1px solid #555; padding: 5px; margin-left: 195px; margin-top: -20px;"><a href="farm.do?id='+id+'">농장가기</a></div></div>',
										
											removable : true
										});
								infowindow.open(map, marker);
							}
						})(marker));

	
	}
	function sendMsg(receiveId, sendId) {
		window.open("msgPop.do?sendId=" + sendId + "&receiveId=" + receiveId,
				"", 'width=' + 400 + 'height=' + 400);
	}
</script>
</head>
<body>
	<div id="map" style="width: 100%; height: 400px;">
		<div id="con" style="overflow: auto;">
		
				<br>
				<%-- <c:forEach items="${ list}" var="m"> --%>
				<%
				 MemberDao md = MemberDao.getInstance();
				List<Member> list = md.selectAll();
				for(Member m :list){
				MapInfoDao mid = MapInfoDao.getInstance();
				MapInfo mi = mid.selectOne(m.getId());
				AniOwnDao aod = AniOwnDao.getInstance();
				int aniNum = 0;
				List<AniOwn> ani = aod.selectOne2(m.getId());				
				AniInfo ai = null;
				String id = (String)session.getAttribute("id");
				
				for(AniOwn ao : ani){
					if(ao.getLeadChk().equals("y ")){
						aniNum = ao.getAniNum();
					}
				}
					AniInfoDao aid = AniInfoDao.getInstance();
					ai =  aid.selectImg(aniNum);
				%>				
				<a href="javascript:a('<%= m.getId()%>',<%=mi.getX()%>,<%=mi.getY()%>,'<%=ai.getAniPic1()%>')">				
					<tr>
					<div class="wrap">
						<div class="width"><td class="px"><%= m.getId()%></td></div>						
						<div class="width"><td><a href="myPage.do?id=<%= m.getId()%>">My</a></td></div>
						<div class="width"><td><a href="javascript:sendMsg('<%= m.getId()%>','<%=id%>')">쪽지</a></td></div>
					</div>
					</tr>					
					<br>				
				</a>	
				<%} %>
		</div>		
	</div>
	<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=ade4d09489216ea20f3334dec9dda931"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center : new daum.maps.LatLng(33.450701, 126.570667),
			level : 3
		};

		var map = new daum.maps.Map(container, options);
		var markerPosition = new daum.maps.LatLng(33.450701, 126.570667);

	</script>
</body>
</html>