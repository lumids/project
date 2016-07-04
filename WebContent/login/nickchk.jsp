<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%	String nickname = request.getParameter("nicknam");
%>
<script type="text/javascript">
	function cl() {
		opener.frm.nickname.value="<%=nickname%>";
		window.close();
	}
</script>
</head><body>
<%	MemberDao md = MemberDao.getInstance();
	int result   = md.nickChk(nickname);
	if (result == 1) { %>
	<%=nickname %>는 이미 있네<p>
	<form>
		id : <input type="text" name="id"><p>
		<input type="submit" value="확인">
	</form>	
<% } else { %>
	<%=nickname %>는 사용할 수 있습니다
	<button onclick="cl()">창닫기</button>
<% } %>
</body>
</html>


