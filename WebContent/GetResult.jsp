<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="getresultsubmit" method="post">
		<table>
			<tr>
				<td>Enter Test Id whose Result is to be checked:</td>
				<td><input type="text" name="testid"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Check Result"></td>
			</tr>
		</table>
	</form>
	<br><br><br><br>
	<jsp:include page="ShowResult.jsp"/></body>
</html>