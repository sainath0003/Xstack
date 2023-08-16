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
	<h2 style="text-align: center; color: aqua;">Add Questions to Quiz
		${quiz.title}</h2>
	<h3>Quiz Questions</h3>
	<table aria-describedby="mydesc" border="1">

		<tr>
			<th>Question Id</th>
			<th>Question Title</th>
			<th>Question options</th>
			<th>Question Answer</th>
			<th>Delete Question</th>

		</tr>
		<c:forEach items="${quiz.questions}" var="question">
			<tr>
				<td><c:out value="${question.id}"></c:out></td>
				<td><c:out value="${question.title}"></c:out></td>
				<td><c:out value="${question.options.toString()}"></c:out></td>
				<td><c:out value="${question.answer}"></c:out></td>
				<td><a href="/quiz/deletequestion?quizid=${quiz.id}&&questionid=${question.id}">delete</a></td>
				
			</tr>
		</c:forEach>

	</table>

	<br>
	<br>
	<a href="/quiz/displayall"><button>Back</button></a>
	<br>
	<br>

	<p id="mydesc">
		<c:out value="${message}"></c:out>
	</p>
	<h3>Available Questions</h3>

	<table aria-describedby="mydesc" border="1">

		<tr>
			<th>Question Id</th>
			<th>Question Title</th>
			<th>Question options</th>
			<th>Question Answer</th>
			<th>Add Question</th>

		</tr>
		<c:forEach items="${questions}" var="question">
			<tr>
				<td><c:out value="${question.id}"></c:out></td>
				<td><c:out value="${question.title}"></c:out></td>
				<td><c:out value="${question.options.toString()}"></c:out></td>
				<td><c:out value="${question.answer}"></c:out></td>
				<td><a href="/quiz/addquestion?quizid=${quiz.id}&&questionid=${question.id}">add</a></td>	
			</tr>
		</c:forEach>

	</table>

</body>
</html>