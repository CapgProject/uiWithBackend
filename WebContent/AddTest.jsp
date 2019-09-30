<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %> --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Add Test
<form action="addtestsubmit" method="post" >
	<table>

		

			<!-- <tr>
				<td>Test Id :</td>
				<td><input type="text" name="testId"/></td>
			</tr> -->

			<tr>
				<td>Test Name :</td>
				<td><input type="text" name="testName"/></td>
			</tr>

			<tr>
				<td>Test Duration :</td>
				<td><input type="text" name="testDuration"/></td>
			</tr>

			<!-- <tr>
				<td>Test Total Marks :</td>
				<td><input type="text" name="testTotalMarks"/></td>
			</tr> -->

			<tr>
				<td>Test Start Time :</td>
				<td><input type="text" name="startTime"/></td>
			</tr>

			<tr>
				<td>Test End Time :</td>
				<td><input type="text" name="endTime"/></td>
			</tr>
			<!-- <tr>
				<td>Test Marks Scored :</td>
				<td><input type="text" name="marksScored"/></td>
			</tr>-->
			<tr> 
				<td><input type="submit" value="Add Test" />
				<td><input type="reset" value="Clear">
			</tr>

		

	</table>
	</form>
</body>
</html>