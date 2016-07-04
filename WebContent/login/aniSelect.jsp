<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<%
	AniInfoDao aid = AniInfoDao.getInstance();
	List<AniInfo> list = aid.selectAll("n");
	for(AniInfo a : list){%>	
		<tr>			
			<td><img src="../upload/<%=a.getAniPic1()%>" 
			onclick="cl('<%=a.getAniName1()%>',<%=a.getAniNum()%>,
			'<%=a.getAniPic1()%>')"></td>
			<td><%=a.getAniName1() %></td>
		</tr>		
	<%} %>
	</table>
</body>
<script type="text/javascript">
	function cl(aniName,aniNum,aniPic1) {
		opener.frm.aniNaming.value=aniName;
		opener.frm.aniNum.value=aniNum;
		opener.frm.aniPic1.src="../upload/"+aniPic1;
		window.close();
	}
</script>
</html>