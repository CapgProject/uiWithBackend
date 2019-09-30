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
Add User
	<table>
		<fo:form action="addusersubmit" method="post" modelAttribute="user">

			<%-- <tr>
				<td>User Id :</td>
				<td><fo:input path="userId" /></td>
			</tr>  --%>

			<tr>
				<td>User Name:</td>
				<td><fo:input path="userName" /></td>
			</tr>

			<tr>
				<td>User Password :</td>
				<td><fo:input path="userPassword" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Add User" />
				<td><input type="reset" value="Clear">
			</tr>


		</fo:form>

	</table>
</body>
</html>