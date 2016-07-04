<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="renamePro.do">
<input type="hidden" name="ownNum" value="${ownNum}">
<c:if test="${aniNaming!=null }">
	<input type="text" name="aniNaming" value="${aniNaming}">
	<input type="submit" value="변경">
</c:if>
<c:if test="${aniNaming==null }">
	<input type="text" name="aniNaming">
	<input type="submit" value="변경">
</c:if>
</form>
