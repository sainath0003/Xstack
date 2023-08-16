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
<h2 style="text-align: center; color:orange;">Welcome to Display All Quiz's Page</h2>

	<p id="mydesc">
		
	</p>
<a href="/quiz/add"><button>Add Quiz</button></a>
	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Quiz Id</th>
			<th>Quiz Title</th>
			<th>Quiz Description</th>
			<th>Quiz Marks Per Question</th>
			<th>Quiz Questions</th>
			<th>Delete Quiz</th> 
			<th>Update Quiz</th> 
			<th>View Quiz</th> 
			
		</tr>
		<c:forEach items="${quizs}" var="quiz">
			<tr>
			<td><c:out value="${quiz.id}"></c:out></td>
			<td><c:out value="${quiz.title}"></c:out></td>
			<td><c:out value="${quiz.description}"></c:out></td>
			<td><c:out value="${quiz.marksPerQuestion}"></c:out></td>
			<td>
			<c:forEach items="${quiz.questions}" var="question">
			QuestionID :<c:out value="${question.id}"></c:out>
			Question Title: <c:out value="${question.title}"></c:out>
			<br>
			</c:forEach>
			</td>
			<td><a href="/quiz/deletefromdisplay?id=${quiz.id}">delete</a></td>
			<td><a href="/quiz/modify?id=${quiz.id}">update</a></td>
			<td><a href="/quiz/display?id=${quiz.id}">view</a></td>
		</tr>

		</c:forEach>

	</table>
	<br><br>
	<a href="/user/adminwelcome"><button >Back</button></a><br>
</body>
</html>