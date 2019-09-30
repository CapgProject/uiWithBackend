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
<fo:form action="updatequestionsubmit" method="post" modelAttribute="question">
	<table>
			<tr>
				<td>Test Id :</td>
				<td><input type="text" name="testId" value="${Update.getOnlinetest().getTestId()}" readonly/></td>
			</tr>
			<tr>
				<td>Question Id :</td>
				<td><fo:input type="text" path="questionId" value="${Update.questionId}" readonly="true"/></td>
			</tr>
			<tr>
				<td>Question Title :</td>
				<td><fo:input type="text" path="questionTitle" value="${Update.questionTitle}"/></td>
			</tr>

			<tr>
				<td>Question Options :</td>
				<td><fo:input type="text" path="questionOptions" value="${Update.questionOptions}"/></td>
			</tr>

			<tr>
				<td>Question Answer :</td>
				<td><fo:input type="text" path="questionAnswer" value="${Update.questionAnswer}"/></td>
			</tr>

			<tr>
				<td>Question Marks :</td>
				<td><fo:input type="text" path="questionMarks" value="${Update.questionMarks}"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update Question" />
			</tr>

	</table>
	</fo:form>
</body>
</html>