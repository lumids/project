<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%	String id = request.getParameter("id");	%>
<script type="text/javascript">
	function cl() {
		opener.frm.id.value="<%=id%>";
		opener.frm.hide.value="1";
		window.close();
	}
</script>
</head><body>
<%	MemberDao md = MemberDao.getInstance();
	int result   = md.idChk(id);
	if (result == 1) { %>
	<%=id %>는 이미 있네<p>
	<form>
		id : <input type="text" name="id"><p>
		<input type="submit" value="확인">
	</form>	
<% } else { %>
	<%=id %>는 사용할 수 있습니다
	<button onclick="cl()">창닫기</button>
<% } %>
</body>
</html>


