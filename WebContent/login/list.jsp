<%@page import="com.sun.jmx.snmp.Timestamp"%>
<%@page import="java.util.*"%>
<%@page import="dao.*"%>
<%@page import="com.sun.corba.se.impl.oa.toa.TOA"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../login/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="style.css">
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">	
	function refesh(a) {
		location.href="list.do?aniNum="+a.value;	
	    init(a.value);
	}
	function init(aniNum) {
		$('#aniNum option[value='+aniNum+']').attr('selected', 'selected');
	}
</script>

</head>
<body>
	<div id="body">
		<table border="1" id="table">
		<caption>게시판</caption>
			<th>포켓몬 분류</th>
			<td>
			<form name="frm">
			<select name="aniNum" onchange="refesh(this)">
			<option value="0">전체</option>
			<option value="0">전체</option>
			<c:forEach items="${list3}" var="ani">
				<option value="${ani.getAniNum() }">${ani.getAniName1() }</option>				
			</c:forEach>
			</select>
			</form>			
			<tr>
				<th>번호</th>
				<th>분류</th>
				<th style="width:500px">제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
			<c:set var="tot" value="${total }" />
			<c:set var="tot2" value="${total2 }" />
			
			
			<c:forEach var="notice" items="${list2}">				
			<div style="text-decoration: underline;">	
			<tr style="border: 3px solid gray; background: skyblue">		
			<td>${tot2 }</td>
			<td>공지</td>
				<td><c:if test="${brd.re_level>0 }">
						<c:set var="w" value="brd.re_level*10" />

						<img alt="" src="../images/level.gif" width="${w }" height="10">
						<img alt="" src="../images/re.gif">
					</c:if>
					<a href="view.do?boardNum=${notice.boardNum }&pageNum=${nowPage}">${notice.getSubject()}</a>
					<c:set var="R" value="${rbd.selectR(notice.boardNum )}"/>
					<span style="margin-left:10px; color:red; text-decoration: underline;">${R }</span></td>

				<td>${notice.getId()}</td>
				<td>${notice.getReadcount()}</td>
				<td>${notice.getWriteDate() }</td>	
			<c:set var="tot2" value="${tot2-1 }" />
			</tr>
			</div>
			</c:forEach>
			
			
			<c:if test="${list != null }">
				<tr>
					<c:forEach var="brd" items="${list}">
					<c:set var="chk" value="y "/>
						<td>${tot } </td>
						<c:if test="${brd.delChk eq chk }">
							<td colspan="7">삭제된 글입니다</td>
						</tr>
			</c:if>		
			<c:if test="${brd.delChk ne chk }">
			<c:set var="a" value="${aid.selectName(brd.getAniNum()) }"/>
				<td>${a}</td>
				<td><c:if test="${brd.re_level>0 }">
						<c:set var="w" value="brd.re_level*10" />

						<img alt="" src="../images/level.gif" width="${w }" height="10">
						<img alt="" src="../images/re.gif">
					</c:if>
					<a href="view.do?boardNum=${brd.boardNum }&pageNum=${nowPage}">${brd.getSubject()}</a>
					<c:set var="R" value="${rbd.selectR(brd.boardNum )}"/>
					<span style="margin-left:10px; color:red; text-decoration: underline;">${R }</span></td>

				
				<td>${brd.getId()}</td>
				<td>${brd.getReadcount()}</td>
				<td>${brd.getWriteDate() }</td>
				</tr>
			</c:if>
			<c:set var="tot" value="${tot-1 }" />
			</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="7">데이터가 업서오</td>
				</tr>
			</c:if>
		</table>
		<form action="list.do">
		<div align="center">
			<select name="keyword" style="vertical-align: bottom">
				<option value="all">제목+내용</option>
				<option value="con">내용</option>				
			</select>
			<input type="hidden" name="aniNum" value="${aniNum }">
			<input type="text" name="search">
			<input type="submit" value="검색">
		</div>
		</form>
		<div align="center">
			<c:if test="${startPage>pagePerBlock }">
				<a href="list.do?pageNum=${startPage-pagePerBlock}">[이전]</a>
			</c:if>

			<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${aniNum==null && search==null}">
				<a href="list.do?pageNum=${i}">[${i}]</a>
			</c:if>
			<c:if test="${aniNum!=null && search==null}">
				<a href="list.do?pageNum=${i}&aniNum=${aniNum}">[${i}]</a>
			</c:if>
			<c:if test="${aniNum!=null && search!=null}">
				<a href="list.do?pageNum=${i}&keyword=${keyword}&search=${search}&aniNum=${aniNum}">[${i}]</a>
			</c:if>				
			</c:forEach>
			<c:if test="${totalPage > endPage }">
				<a href="list.do?pageNum=${ startPage+pagePerBlock}">[다음]</a>

			</c:if>
			<br> <br>
			<button onclick="location.href='writeForm.do?pageNum=${pageNum}'">글쓰기</button>
		</div>
	</div>
</body>
</html>