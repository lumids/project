<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dao.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function mapSelect() {
		window.open("mapChk.html", "", "width='500' height='500'");
	}
</script>
</head>
</head>
<body>
	<div id="body">

		<%
			if (id != null && id.equals("master")) {
				response.sendRedirect("myPageM.do");
			}
		%>
		<div id="tab" class="btn-group" data-toggle="buttons-radio"
			style="margin: 0 auto;">
			<ul class="nav nav-pills nav-justified"
				style="background-color: white;">
				<li class="active"><a href="#contact2" data-toggle="tab">포켓몬
						확인</a></li>
				<c:if test="${owner==id}">
					<li><a href="#prices2" data-toggle="tab">포켓몬 구매</a></li>
					<li><a href="#features2" data-toggle="tab">포인트</a></li>
					<li><a href="#requests2" data-toggle="tab">아이템 확인</a></li>
					<li><a href="#requests3" data-toggle="tab">좌표 변경</a></li>
				</c:if>
			</ul>
		</div>
		<div class="tab-content">
			<div class="tab-pane fade active in" id="contact2">
				<br>
				<div id="regForm" class="box">
					<h2>포켓몬 확인</h2>

					<c:if test="${list!=null }">
						<c:forEach var="ao" items="${list}">
							<div style="float: left; text-align: center;">
								<c:set var="a" value="${aid.selectImg(ao.getAniNum()) }" />

								<a href="myPageAni.do?ownNum=${ao.getOwnNum()}&owner=${owner}">


									<figure>
										<c:if
											test="${ao.getNowStat1()>=a.getAniMaxStat1() && ao.getNowStat2()>=a.getAniMaxStat2()}">
											<img src="../upload/${a.getAniPic2()}" width="100">
											<figcaption>${ao.getAniNaming()}</figcaption>
										</c:if>
										<c:if
											test="${ao.getNowStat1()<a.getAniMaxStat1() ||ao.getNowStat2()<a.getAniMaxStat2() }">
											<img src="../upload/${a.getAniPic1()}" width="100">
											<figcaption>${ao.getAniNaming()}</figcaption>
										</c:if>
										<c:if test="${ao.leadChk=='y ' }">
											<div class="bold">리더</div>
										</c:if>
									</figure>
								</a>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${list eq '[]' }">
					보유중인 포켓몬이 없습니다.
					</c:if>
				</div>
			</div>
			<div class="tab-pane fade in" id="prices2" align="center">
				<br>

				<h2>포켓몬 구매</h2>
				<input type="button" value="구매" onclick="location.href='shop.jsp'"
					class="btn">
				<h2>아이템 구매</h2>
				<input type="button" value="구매"
					onclick="location.href='shopItem.jsp'" class="btn">
			</div>
			<div class="tab-pane fade in" id="features2">
				<br>
				<div>
					<h2>포인트 내역</h2>
					<table class="table table-striped">
						<tr>
							<td colspan="3" align='right'>경고횟수 ${warn }</td>
						</tr>
						<tr>
							<td colspan="3" align='right'>총포인트 ${m.getPointSum() }</td>
						</tr>
						<tr>
							<td>사유</td>
							<td>변화 포인트</td>
							<td>시간</td>
						</tr>
						<c:forEach var="point" items="${list2}">
							<tr>
								<td>${point.getPointGetReason() }</td>
								<td>${point.getGetPoint() }</td>
								<td>${point.getPointGetTime() }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="tab-pane fade in" id="requests2">
				<br>
				<div>
					<h2>아이템 확인</h2>
					<br>
					<table class="table table-striped">
					<c:forEach items="${list5 }" var="list">
					<c:if test="${list5!='[]' }">
						<h4>이벤트 아이템</h4>
					</c:if>
					<c:set var="item" value="${mapItem.selectOne2(list.getMapItemNum()) }"/>					
						<figure>
						<a href="legendItem.do?mapItemNum=${list.getMapItemNum() }&id=${id}">
						<img src="../upload/${item.getMapItemPic() }">
						</a>
						<figcaption>${item.getMapItemName() }</figcaption>
						</figure>										
					</c:forEach>					
						<c:if test="${list4 !='[]' }">
							<c:forEach items="${list4 }" var="IDetail">
								<c:set var="a" value="${idd.selectOne(IDetail.getItemNum()) }" />
								<div style="float: left; text-align: center;">
									<a
										href="myPageItem.do?itemNum=${IDetail.getItemNum()}&itemDetailNum=${IDetail.getItemDetailNum()}">
										<figure>
											<img src="../upload/${a.getItemPic() }">
											<figcaption>${a.getItemName() }</figcaption>
										</figure>
									</a>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${list4=='[]' }">
							<tr>
								<td colspan="2" align="center">보유 아이템이 없습니다.
									<h2>아이템 구매</h2> <input type="button" value="구매"
									onclick="location.href='shopItem.jsp'" class="btn">
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
			<div class="tab-pane fade in" id="requests3">
				<br>
				<div>
					<h2>지도 변경</h2>
					<form name="frm" action="updateMap.do">
						<input type="hidden" name="id" value="${id }"> <input
							type="text" name="x" id="x"> <input type="text" name="y"
							id="y"> <input type="button" onclick="mapSelect()"
							value="선택"> <input type="submit" value="변경">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>