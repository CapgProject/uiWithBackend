<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updatetestsubmit" method="post" >
	<table>
			<tr>
				<td>Test Id :</td>
				<td><input type="text" name="testId" value="${Update.testId}" readonly/></td>
			</tr>
			<tr>
				<td>Test Name :</td>
				<td><input type="text" name="testName" value="${Update.testName}"/></td>
			</tr>

			<tr>
				<td>Test Duration :</td>
				<td><input type="text" name="testDuration" value="${Update.testDuration}"/></td>
			</tr>

			<tr>
				<td>Test Start Time :</td>
				<td><input type="text" name="startTime" value="${Update.startTime}"/></td>
			</tr>

			<tr>
				<td>Test End Time :</td>
				<td><input type="text" name="endTime" value="${Update.endTime}"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update Test" />
			</tr>

	</table>
	</form>
</body>
</html>