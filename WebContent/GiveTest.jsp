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
			<th>Question Title</th>
			<th>Question Options</th>
			<th>Question Marks</th>
			<th>Question Chosen Answer</th>
		</tr>
		<a:forEach var="question" items="${questiondata}">
			<tr>
				<td>${question.questionId }</td>
				<td>${question.questionTitle }</td>
				<td>${question.questionOptions }</td>
				<td>${question.questionMarks}</td>
				<td><input type="text" name="answer"/>${question.chosenAnswer}</td>
			</tr>
		</a:forEach>
	</table>
</body>
</html>