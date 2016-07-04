<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<c:forEach items="${ list}" var="AniOwn">
	<div style="float: left; text-align: center;">
			<c:set var="a" value="${aid.selectOwn(AniOwn.getAniNum()) }" />
			<a href="itemUseAni.do?itemDetailNum=${itemDetailNum }&ownNum=${AniOwn.getOwnNum()}&itemNum=${itemNum}&id={id}">			
			<c:set var="ao" value="${aod.selectOne(AniOwn.getOwnNum()) }"/>			
			<c:if test="${ao.getNowStat1()>=a.getAniMaxStat1() && ao.getNowStat2()>=a.getAniMaxStat2()}">
			<figure> <img src="../upload/${a.getAniPic2() }"> <figcaption>${a.getAniName2() }</figcaption></figure>
			</c:if>
			<c:if test="${ao.getNowStat1()<a.getAniMaxStat1() && ao.getNowStat2()<a.getAniMaxStat2()}">
			<figure> <img src="../upload/${a.getAniPic1() }"> <figcaption>${a.getAniName1() }</figcaption></figure>
			</c:if>
			</a>
			</figure>
	</div>
	</c:forEach>	
</body>
</html>