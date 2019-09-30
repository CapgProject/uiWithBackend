<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updateuserinput" method="POST">
	<table>
		
			<tr>
				<td>User Id :</td>
				<td><input type = "text" name="userid" /></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Update User" /></td>
			</tr>	
	
	</table>
</form>
<br><br><br><br>
<jsp:include page="UpdateUserDetails.jsp"/>
</body>
</html>