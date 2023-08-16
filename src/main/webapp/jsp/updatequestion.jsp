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
	
<h2>Details of Question</h2>
	<form action="/question/modify" method="post">
		<br> Enter the question
		title <input type="text" name="title" value="${question.title}"><br>
		<br> Enter the question description <input type="text"
			name="description" value="${question.description}"><br>
		<br> Enter the question difficulty Enter the question difficulty
		: <select name="difficulty" id="difficulty">
			<option value="Easy">Easy</option>
			<option value="Medium">Medium</option>
			<option value="Hard">Hard</option>
		</select><br>(add comma separated values) <br> <br>
		Enter the options needed <input type="text" name="options"
			value="${question.options.toString().substring(1,question.options.toString().length()-1)}"> <br> <br>
		Enter the answer options <br> <input type="number" name="answer"
			value="${question.answer}"> <br> <br> <input
			type="submit">
	</form>
	<br>
	<br>
	<a href="/question/library"><button>Back</button></a>
	<br>
	<br>
	<c:out value="${message}"></c:out>
	<p id="mydesc"></p>



</body>
</html>