<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="assigntestsubmit" method="post">
		<table>
			<tr>
				<td>Enter Test Id</td>
				<td><input type="text" name="testid"></td>
			</tr>
			<tr>
				<td>Enter User Id</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Assign Test"></td>
			</tr>
		</table>
	</form>
</body>
</html>