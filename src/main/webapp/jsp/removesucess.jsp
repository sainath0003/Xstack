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
	<c:out value="${message}"></c:out>
	<form action="deletequizs" method="post">
		Enter the quiz id <input type="text" name="id"><br> <input
			type="submit">
	</form>

	<c:out value="${message}"></c:out>
	<p id="mydesc"></p>

	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Quiz Id</th>
			<th>Quiz Title</th>
		</tr>

		<tr>
			<td><c:out value="${quiz.id}"></c:out></td>
			<td><c:out value="${quiz.title}"></c:out></td>
		</tr>


	</table>
</body>
</html>