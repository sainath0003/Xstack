<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Attempting Quiz</title>
</head>
<body>
	


	<h1 class="quiz-title">${quiz.title}</h1>
	
	<form action="/quiz/checkanswers" method="post">
	
		<input type="text" hidden="hidden" name="quizid" value="${quiz.id}">
		
		<c:forEach items="${quiz.questions}" var="question">
				
				<p class="question-text">${question.title}</p>
				<c:forEach items="${question.options}" var="option">
					
						${option} <input type="radio" name="${question.id}" value="${option}"> 
					
				</c:forEach>
		</c:forEach>
		<br><br>
		
		<button type="submit" class="submit-btn">Submit</button>
	</form>



</body>
</html>