<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC 게시판</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>전화번호</td>
			<td>주소</td>
			<td>전자우편</td>
			<td>관계</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td><a href="content_view?bId=${dto.bId}">${dto.bId}</a></td>
			<td>${dto.bName}</td>
			<td>${dto.bTel}</td>
			<td>${dto.bAddress}</td>
			<td>${dto.bEmail}</td>
			<td>${dto.bRelation}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view">주소록 등록</a> </td>
		</tr>
	</table>


</body>
</html>