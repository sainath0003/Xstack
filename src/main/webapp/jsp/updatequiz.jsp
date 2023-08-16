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
	<h2 style="text-align: center;">Updating the Quiz</h2>
	<h3>The Quiz details with title ${question.title}</h3>

	<p id="mydesc">
		<c:out value="${message}"></c:out>
	</p>

	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Quiz Id</th>
			<th>Quiz Title</th>
			<th>Quiz Description</th>
			<th>Quiz Marks Per Quiz</th>
			<th>Quiz Questions</th>
		</tr>

		<tr>
			<td><c:out value="${quiz.id}"></c:out></td>
			<td><c:out value="${quiz.title}"></c:out></td>
			<td><c:out value="${quiz.description}"></c:out></td>
			<td><c:out value="${quiz.marksPerQuestion}"></c:out></td>
			<td><c:forEach items="${quiz.questions}" var="question">
			QuestionID :<c:out value="${question.id}"></c:out>
			Question Title: <c:out value="${question.title}"></c:out>
					<br>
				</c:forEach></td>
		</tr>

	</table>
	<form action="/quiz/modifyquiz" method="post">
	    <input type="hidden" name="id" value="${quiz.id}"><br>
		<br> Enter the quiz title <input type="text" name="title"
			value="${quiz.title}"><br>
		<br> Enter the quiz description <input type="text"
			name="description" value="${quiz.description}"><br>
		<br> Enter the marksPerQuestion <input type="number"
			name="marksPerQuestion" value="${quiz.marksPerQuestion}"><br>
	
		<br> <input type="submit">
		</form>
		<br>
	<br>
	<a href="/quiz/displayall"><button>Back</button></a>
		
</body>
</html>