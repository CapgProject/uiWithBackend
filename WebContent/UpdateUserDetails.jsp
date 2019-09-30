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
<fo:form action="updateusersubmit" method="post" modelAttribute="user">
	<table>
			<tr>
				<td>User Id :</td>
				<td><input type="text" name="userId" value="${Update.userId}" readonly/></td>
			</tr>
			<tr>
				<td>Test Id :</td>
				<td><input type="text" name="testId" value="${Update.getUserTest().getTestId()}" readonly/></td>
			</tr>
			<tr>
				<td>Username :</td>
				<td><fo:input path="userName" value="${Update.userName}"/></td>
			</tr>

			<tr>
				<td>User Password :</td>
				<td><fo:input path="userPassword" value="${Update.userPassword}"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update User" />
			</tr>
	</table>
	</fo:form>
</body>
</html>