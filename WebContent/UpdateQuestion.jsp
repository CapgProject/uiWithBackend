<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updatequestioninput" method="post" >
	<table>
	<tr>
	<td>Enter question Id to be updated</td>
	<td><input type="text" name="questionid"></td>
	</tr>
	<tr>
	<td><input type="submit" value="Show Details"></td>
	</tr>
	</table>
	</form>
	<br><br><br><br>
	
	<jsp:include page="UpdateQuestionDetails.jsp"/>
</body>
</html>