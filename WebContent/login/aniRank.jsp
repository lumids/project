<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="tot" value="7" />
		<c:forEach items="${list }" var="list">
			<c:if test="${tot>0 }">
				<div style="float:left; vertical-align: bottom; height:230px;">
				<c:set var="name" value="${aid.selectOne(list.getAniNum()) }" />
				<figure style="text-align: center">
				<c:if
					test="${list.getNowStat1()>=name.getAniMaxStat1() && list.getNowStat2()>=name.getAniMaxStat2()}">
					<a href="myPageAni.do?ownNum=${list.getOwnNum() }&owner=${list.getId()}"><img src="../upload/${name.getAniPic2()}" width="100"></a>				
					<figcaption>${list.getAniNaming() }</figcaption>
				</c:if>
				<c:if
					test="${list.getNowStat1()<name.getAniMaxStat1() ||list.getNowStat2()<name.getAniMaxStat2() }">
					<img src="../upload/${name.getAniPic1()}" width="100">
					<figcaption>${list.getAniNaming() }</figcaption>
				</c:if>
				</figure>
				<br>
				스탯1
				<progress max=${name.getAniMaxStat1() } style="width: 70px"
					value=${list.getNowStat1()}></progress>${list.getNowStat1()}
				<br>				
				스탯2
				<progress max=${name.getAniMaxStat2() } style="width: 70px"
					value=${list.getNowStat2()}></progress>${list.getNowStat2()}				
				<c:set var="tot" value="${tot-1 }" />
				</div>
			</c:if>
		</c:forEach>
</body>
</html>