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
	<h2 style="text-align: center; color: aqua;">Welcome to Create
		Question Page</h2>
	<p style="text-align: center;">
	<form action="/question/add" style="font-style: italic;" method="post">
		Enter the question title :<input type="text" name="title"><br>
		Enter the question description : <input type="text" name="description"><br>
		Enter the question difficulty : <select name="difficulty"
			id="difficulty">
			<option value="Easy">Easy</option>
			<option value="Medium">Medium</option>
			<option value="Hard">Hard</option>
		</select> 
		<br>(add comma separated values) <br>
		<br> Enter the options needed :<input type="text" name="options"><br>
		<br> Enter the answer options <input type="number" name="answer"><br>
		<br> <input type="submit"
			style="align-self: center; text-shadow: red;"><br>
	</form>
	<br>
	<br>
	<a href="/question/displayall"><button>Back</button></a>
	<br>
	<br>

	<p id="mydesc">
		<c:out value="${message}"></c:out>
	</p>

	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Question Id</th>
			<th>Question Title</th>
			<th>Question options</th>
			<th>Question Answer</th>

		</tr>

		<tr>
			<td><c:out value="${question.id}"></c:out></td>
			<td><c:out value="${question.title}"></c:out></td>
			<td><c:out value="${question.options.toString()}"></c:out></td>
			<td><c:out value="${question.answer}"></c:out></td>
		</tr>


	</table>

</body>
</html>