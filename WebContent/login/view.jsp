<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../login/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function update(reBoardNum,RpageNum,content,boardNum) {
		$.ajax({      
	        type:"POST",  
	        url:"reContent.do",      
	        data:{reBoardNum:reBoardNum,
	        	RpageNum:RpageNum,
	        	content:content,
	        	boardNum:boardNum},	        	
	        success:function(data){
	        	 $("#"+reBoardNum).html(data);
	        }
	    }); 
	}
	function deleteR (reBoardNum,RpageNum,boardNum) {
		$.ajax({      
	        type:"POST",  
	        url:"reDelete.do",      
	        data:{reBoardNum:reBoardNum,
	        	RpageNum:RpageNum,
	        	boardNum:boardNum},	        	
	        success:function(data){
	        	 location.reload(true);
	        }
	    }); 
	}
	function reContent (reBoardNum,RpageNum,boardNum,id) {
		$.ajax({      
	        type:"POST",  
	        url:"Rwrite2.do",      
	        data:{reBoardNum:reBoardNum,
	        	RpageNum:RpageNum,
	        	boardNum:boardNum,
	        	id:id},	        	
	        success:function(data){
	        	$("#"+reBoardNum+"1").html(data);
	        }
	    }); 
	}
	function reportAdd (reBoardNum,boardNum,reporterId,reportedId,content) {
	      window.open("reportAdd.do?reBoardNum="+reBoardNum+"&boardNum="
	    		  +boardNum+"&reporterId="+reporterId+"&reportedId="+reportedId+"&content="+content
	    		  ,"","width='300' height='300'");
	}
</script>
</head>
<body>
<div id="body">
<c:if test="${not empty board}">
	<table border="1" id="table">
	<caption>게시판 상세보기</caption>
		<tr><th>제목</th>	<td>${board.getSubject()}</td></tr>
		<tr><th>작성자</th><td>${board.getId()}</td></tr>
		<tr><th>조회수</th><td>${board.getReadcount()}</td></tr>
		<tr><th>작성일</th><td>${board.getWriteDate()}</td></tr>
		<tr><th>내용</th>	<td><pre>${board.getContent()}</pre></td></tr>
	</table>
	<form action="Rwrite.do" name="frm">
	<input type="hidden" name="boardNum" value="${board.getBoardNum()}">
	<input type="hidden" name="id" value="${id}">
	<table id="table">	
	<c:set var="tot" value="${total }" />
			<c:if test="${list != null }">
				<tr>
					<c:forEach var="brd" items="${list}">
					<c:set var="chk" value="y "/>
						<td>${tot } </td>
						<c:if test="${brd.getRDelChk() eq chk }">
							<td colspan="7" width="700px">삭제된 댓글입니다</td>
						</tr>						
						</c:if>
						<c:if test="${brd.getRDelChk() eq 'w ' }">
							<td colspan="7" width="700px">신고되어 삭제된 댓글입니다</td>
						</tr>						
						</c:if>
			<c:if test="${brd.getRDelChk() ne chk && brd.getRDelChk() ne 'w '}">			
				
				<td style="width:500px;">
				<c:if test="${brd.getRRe_level()>0 }">
						<c:set var="w" value="${ brd.getRRe_level()*20}" />
						<img alt="" src="../images/level.gif" width="${w }" height="10">
						<img alt="" src="../images/re.gif">
					</c:if>
				<div id="${brd.getReBoardNum()}" style="display: inline;">${brd.getContent()}</div>
				<div id="${brd.getReBoardNum()}1"></div>
				<td>${brd.getId()}</td>
				<c:set var="now" value="${brd.getRWriteDate() }" />
				<td><fmt:formatDate type="both" value="${now}" /></td>
				<td class="min"><input type="button" name="reContent2" id="reContent2"  value="답글" onclick="reContent('${brd.getReBoardNum()}',${RpageNum },${board.getBoardNum() },'${id }')"></td><c:if test="${id eq brd.getId() }">									
					<td class="min"><input type="button" name="upContent" id="upContent"  value="수정" onclick="update('${brd.getReBoardNum()}',${RpageNum },'${brd.getContent() }',${board.getBoardNum() })"></td>
					<td class="min"><input type="button" name="delContent" id="delContent"  value="삭제" onclick="deleteR('${brd.getReBoardNum()}',${RpageNum },${board.getBoardNum() })"></td>
				
				<td class="min"><input type="button" name="report" id="report"  value="신고" onclick="reportAdd('${brd.getReBoardNum()}',${board.getBoardNum() },'${id }','${brd.getId() }','${brd.getContent()}')"></td>
				</c:if>
				</tr>
			</c:if>
			<c:set var="tot" value="${tot-1 }" />
			</c:forEach>
			</c:if>			
			<c:if test="${empty list }">
				<tr>
					<td colspan="7">댓글 X</td>
				</tr>
			</c:if>
		<tr>
			<td>댓글 작성</td>
			<td colspan="5"><textarea rows="4" cols="50" name="content"></textarea></td>
			<td><input type="submit" value="확인"></td> 
		</tr>	
	</table>
	</form>
	<div align="center">
			<c:if test="${startPage>pagePerBlock }">
				<a href="view.do?boardNum=${boardNum }&RpageNum=${startPage-pagePerBlock}">[이전]</a>
			</c:if>

			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a href="view.do?boardNum=${boardNum }&RpageNum=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${totalPage > endPage }">
				<a href="view.do?boardNum=${boardNum }&RpageNum=${ startPage+pagePerBlock}">[다음]</a>
			</c:if>
			<br> <br>			
		</div>
</c:if>
<c:if test="${empty board}">	
	데이터가 없습니다.	
</c:if>
<div align="center">
<c:set var="id" value="<%=id %>"/>
<input type="hidden" value="<%=id %>" name="id">
<input type="hidden" value="${pageNum}" name="pageNum">

	<button onclick="location.href='list.do?pageNum=${pageNum}'">게시판 목록</button>
	<c:if test="${board.getId() == id}">	
		<button onclick="location.href='updateForm.do?boardNum=${boardNum}&pageNum=${pageNum}'">수정</button>	
		<button onclick="location.href='deletePro.do?boardNum=${boardNum}&pageNum=${pageNum}'">삭제</button>
		<button onclick="location.href='writeForm.do?boardNum=${boardNum}&pageNum=${pageNum}'">답글</button>
	</c:if>
</div>
</div>
</body>
</html>