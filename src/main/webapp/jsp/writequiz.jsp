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
	
	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Quiz Id</th>
			<th>Quiz Title</th>
			<th>Quiz Description</th>
			<th>Quiz Marks Per Question</th>
			<th>Action</th> 
			
			
		</tr>
		<c:forEach items="${quizs}" var="quiz">
			<tr>
			<td><c:out value="${quiz.id}"></c:out></td>
			<td><c:out value="${quiz.title}"></c:out></td>
			<td><c:out value="${quiz.description}"></c:out></td>
			<td><c:out value="${quiz.marksPerQuestion}"></c:out></td>
			
			<td><a href="/quiz/displayforquiz?id=${quiz.id}">attempt</a></td>
			
		</tr>

		</c:forEach>

	</table>
	</body>
</html>