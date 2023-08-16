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

	<h2 style="text-align: center; color: yellow;">Welcome to SignUp
		Page</h2>

	<form action="/user/saveuser" method="post">
		UserName : <input type="text" name="userName"><br> <br>
		Password : <input type="text" name="password"><br> <br>
		CustomerName : <input type="text" name="customerName"><br>
		<br> EmailId : <input type="text" name="emailId"><br>
		<br> <input type="submit">

	</form>

	Status :
	<c:out value="${message}"></c:out>
	<br>
	<br>
	<br>
	<br>

	<a href="/quiz/welcome"><button style="border: thick; text-align: center;">
			<b style="font-size: medium; font-family: monospace;">Back</b>
		</button></a>

	<br>
	<br>

</body>
</html>