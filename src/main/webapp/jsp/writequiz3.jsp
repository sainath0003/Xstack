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
	<form action="/quiz/displayforquiz" method="post">
		Enter the quiz id <input type="number" name="id"><br> <input
			type="submit">
	</form>
	
	
	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Question Id</th>
			<th>Question Title</th>
			<th>QuestionOptions</th>

		</tr>
		<c:forEach items="${questions}" var="question">
			<tr>
				<td><c:out value="${question.id}"></c:out></td>
				<td><c:out value="${question.title}"></c:out></td>
				<td><c:out value="${question.options.toString()}"></c:out>
				
			</tr>

		</c:forEach>

	</table>
	<br>
	<br>
	<label for="w3review">Enter answers to the above questions(note
		add each question answers in new Line)</label>
	<form action="/quiz/sucess" >
		<textarea id="w3review" name="answers" rows="8" cols="50">
</textarea>
		
		<br> <input type="submit">
	</form>
	<br> <br> <a href="/question/library"><button>Back</button></a>
	<br>
	<p id="mydesc">
		Total marks obtained :
		<c:out value="${marks}"></c:out>
	</p>
</body>
</html>