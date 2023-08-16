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
	<h2 style="font-style: italic;">
		Thanks For Attempting the Quiz
		<c:out value="${quiz.title}"></c:out>
	</h2>
	<p id="mydesc" style="font-family: cursive;">
		<b>Total marks obtained : <c:out value="${score}"></c:out></b>
	</p>

	<br>
	<a href="/user/clientwelcome"><button>Back</button></a>
</body>
</html>