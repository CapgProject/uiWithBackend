<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Add Question
	
		<form action="addquestionsubmit" method="POST">
			<table>
			<tr>
				<td>Test ID: </td>
				<td><input type="text" name="testid" /></td>
			</tr>
			
			<%-- <tr>
				<td>Question Title :</td>
				<td><fo:input path="questionTitle" /></td>
			</tr>

			<tr>
				<td>Question Marks :</td>
				<td><fo:input path="questionMarks" /></td>
			</tr>

			<tr>
				<td>Question Options :</td>
				<td><fo:input path="questionOptions" /></td>
			</tr>


			<tr>
				<td>Question Answer:</td>
				<td><fo:input path="questionAnswer" /></td>
			</tr>


			<tr>
				<td>Marks Scored :</td>
				<td><fo:input path="marksScored" /></td>
			</tr> --%>

			<tr>
				<td><input type="submit" value="Add Question" />
				<!-- <td><input type="reset" value="Clear"> -->
			</tr>
			</table>
		</form>
	
</body>
</html>