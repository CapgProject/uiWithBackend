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
			<th>Test Id</th>
			<th>Test Name</th>
			<th>Test Duration</th>
			<th>Test Start Time</th>
			<th>Test End Time</th>
		</tr>
		<a:forEach var="test" items="${testdata}">
			<tr>
				<td>${test.testId}</td>
				<td>${test.testName}</td>
				<td>${test.testDuration}</td>
				<td>${test.startTime}</td>
				<td>${test.endTime}</td>
			</tr>
		</a:forEach>
	</table>
</body>
</html>