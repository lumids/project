<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">	
	function rename(aniNaming,ownNum) {
		 $.ajax({      
	        type:"POST",  
	        url:"rename.do",      
	        data:{aniNaming:aniNaming,
	        	ownNum:ownNum},      
	        success:function(data){   
	            $("#result").html(data);
	        }
	    }); 
	}
</script>
<body>

	<div id="body">
		<div id="stat">
			<c:set var="a" value="${aid.selectImg(ao.getAniNum()) }" />
			<c:if
				test="${ao.getNowStat1()>=a.getAniMaxStat1() && ao.getNowStat2()>=a.getAniMaxStat2()}">
				<img src="../upload/${a.getAniPic2()}" width="100">
			</c:if>
			<c:if
				test="${ao.getNowStat1()<a.getAniMaxStat1() ||ao.getNowStat2()<a.getAniMaxStat2() }">
				<img src="../upload/${a.getAniPic1()}" width="100">
			</c:if>

			<%-- <img src="../upload/${ai.getAniPic1() }" id="pic"> --%>
			<span style="border: 1px solid black; padding: 10px">${hello}</span>
			<br> 포켓몬 명 : ${ai.getAniName1()}<br>
			<c:if test="${ao.getAniNaming()==null }">
				<c:if test="${owner==id }">
			이름을 지어주세요.<input type="button" name="rename" id="rename"
						value="이름 짓기"
						onclick="rename('${ao.getAniNaming()}',${ao.getOwnNum() })">

					<div id="result"></div>
				</c:if>
			</c:if>
			<c:if test="${owner==id }">
				<c:set var="chk" value='n ' />
				<c:if test="${ao.getLeadChk() eq chk }">
					<input type="button" value="리더 선택"
						onclick="location.href='leader.do?ownNum=${ao.ownNum}&id=${id }&leadChk=${ao.getLeadChk()}'">
					<br>
				</c:if>
				<c:if test="${ao.getLeadChk() ne chk }">
					<div class="bold">리더</div>
					<input type="button" value="리더 해제"
						onclick="location.href='leader.do?ownNum=${ao.ownNum}&id=${id }&leadChk=${ao.getLeadChk()}'">
					<br>
				</c:if>
			</c:if>
			<c:if test="${ao.getAniNaming()!=null }">
			이름 : ${ao.getAniNaming() }
			<c:if test="${owner==id }">
			<input type="button" name="rename" id="rename2" value="이름 변경"
					onclick="rename('${ao.getAniNaming()}',${ao.getOwnNum() })">
				<br>
				<div id="result"></div>
			</c:if>
			</c:if>
			<c:if test="${owner==id }">
				<input type="button" value="분양하기" onclick="location.href='goCenter.do?ownNum=${ao.getOwnNum()}&id=${id }'">
			</c:if>
			<br> 스탯1 :
			<progress max=${ai.getAniMaxStat1() } value=${ao.getNowStat1()}></progress>${ao.getNowStat1()}<br>
			<c:if test="${owner==id }">
				<input type="button" value="쓰다듬기"
					onclick="location.href='statUp.do?ownNum=${ao.getOwnNum()}&stat=1'">
				<br>
			</c:if>
			스탯2 :
			<progress max=${ai.getAniMaxStat2() } value=${ao.getNowStat2()}></progress>${ao.getNowStat2()}<br>
			<c:if test="${owner!=id }">
				<input type="button" value="쓰다듬기"
					onclick="location.href='statUp.do?ownNum=${ao.getOwnNum()}&stat=2'">
				<br>
			</c:if>
			<input type="button" value="목록"
				onclick="location.href='myPage.do?id=${owner}'">
		</div>
	</div>
</body>
</html>