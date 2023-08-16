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
	<form action="/question/display" method="post">
		Enter the question id <input type="number" name="id"><br><br>
		<input type="submit">
	</form>
	<br><br>
<a href="/question/library"><button >Back</button></a><br><br>
	<c:out value="${message}"></c:out>
	<p id="mydesc"></p>

	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Question Id</th>
			<th>Question Title</th>
			<th>QuestionOptions</th>
			<th>QuestionAnswer</th>
		</tr>

		<tr>
			<td><c:out value="${question.id}"></c:out></td>
			<td><c:out value="${question.title}"></c:out></td>
			<td><c:out value="${question.options.toString()}"></c:out>
			<td><c:out value="${question.answer}"></c:out></td>
		</tr>


	</table>


</body>
</html>