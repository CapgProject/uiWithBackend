<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updatetestinput" method="post" >
	<table>
	<tr>
	<td>Enter test Id to be updated</td>
	<td><input type="text" name="testid"></td>
	</tr>
	<tr>
	<td><input type="submit" value="Show Details"></td>
	</tr>
	</table>
	</form>
	<br><br><br><br>
	
	<jsp:include page="UpdateTestDetails.jsp"/>
</body>
</html>