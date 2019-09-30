<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
		<tr>
			<th>User Id</th>
			<th>User Name</th>
		</tr>
		<a:forEach var="user" items="${userdata}">
			<tr>
				<td>${user.userId}</td>
				<td>${user.userName}</td>
			</tr>
		</a:forEach>
	</table>
</body>
</html>