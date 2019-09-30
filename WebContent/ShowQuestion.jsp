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
			<th>Question Id</th>
			<th>Question Name</th>
			<th>Question Marks</th>
			<th>Question Options</th>
			<th>Question Chosen Answer</th>
			<th>Question Marks Scored</th>
		</tr>
		<a:forEach var="question" items="${questiondata}">
			<tr>
				<td>${question.questionId }</td>
				<td>${question.questionTitle }</td>
				<td>${question.questionMarks}</td>
				<td>${question.questionOptions }</td>
				<td>${question.chosenAnswer }</td>
				<td>${question.marksScored }</td>


			</tr>
		</a:forEach>
	</table>
</body>
</html>