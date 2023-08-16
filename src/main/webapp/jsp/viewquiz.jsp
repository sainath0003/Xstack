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
	
	
	<p id="mydesc">
		<c:out value="${message}"></c:out>
	</p>

	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Quiz Id</th>
			<th>Quiz Title</th>
			<th>Quiz Description</th>
			<th>Quiz Marks Per Question</th>
			<th>Quiz Questions</th>
		</tr>

		<tr>
			<td><c:out value="${quiz.id}"></c:out></td>
			<td><c:out value="${quiz.title}"></c:out></td>
			<td><c:out value="${quiz.description}"></c:out></td>
			<td><c:out value="${quiz.marksPerQuestion}"></c:out></td>
			<td><c:out value="${quiz.questions}"></c:out></td>
		</tr>


	</table>
	<br><br>
	<a href="/quiz/displayall"><button >Back</button></a><br><br>

</body>
</html>