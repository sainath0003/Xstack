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
<h2 style="text-align: center; color:aqua;">Welcome to Create Quiz Page</h2>

	<form action="/quiz/add" style="font-family: sans-serif;" method="post">
		Enter the quiz title <input type="text" name="title"><br><br>
		Enter the quiz description <input type="text" name="description"><br><br>
		Enter the marksPerQuestion <input type="number" name="marksPerQuestion"><br><br>
		
		<input type="hidden" name="questionsString" value="1"><br><br>
		<input type="submit">
	</form>
	<br><br>
	<a href="/quiz/library"><button >Back</button></a><br><br>
	<p id="mydesc">
		<c:out value="${message}"></c:out>
	</p>
	

</body>
</html>