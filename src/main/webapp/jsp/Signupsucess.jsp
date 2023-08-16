<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>User Registered completed Sucessfully</h2>
	<c:out value="${message}"></c:out>
	<p id="mydesc"></p>

	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>User Id</th>
			<th>User Name</th>
			<th>User Type</th>
			<th>Customer Name</th>
			<th>Email Id</th>

		<tr>
			<td><c:out value="${user.userId}"></c:out></td>
			<td><c:out value="${user.userName}"></c:out></td>
			<td><c:out value="${user.userType}"></c:out></td>
			<td><c:out value="${user.customerName}"></c:out>
			<td><c:out value="${user.emailId}"></c:out></td>
		</tr>


	</table>
<br><br>
<br><br>
<a href="/quiz/welcome"><button style="border: thick; text-align: center;" ><b style="font-size: medium;font-family: monospace;">Back</b></button></a><br><br>


</body>
</html>