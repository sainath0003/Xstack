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
	<h2 style="text-align: center; color: orange;">Welcome to
		Questions Page</h2>
	<a href="/question/add"><button>Add Question</button></a>
	<p id="mydesc"></p>

	<table aria-describedby="mydesc" border="1">
		<tr>
			<th>Question Id</th>
			<th>Question Title</th>
			<th>QuestionOptions</th>
			<th>QuestionAnswer</th>
			<th>DeleteQuestion</th>
			<th>UpdateQuestion</th>


		</tr>
		<c:forEach items="${questions}" var="question">
			<tr>
				<td><c:out value="${question.id}"></c:out></td>
				<td><c:out value="${question.title}"></c:out></td>
				<td><c:out value="${question.options.toString()}"></c:out>
				<td><c:out value="${question.answer}"></c:out></td>
				<td><a href="/question/deletefromdisplay?id=${question.id}">Delete</a></td>
				<td><a
					href="/question/displayquestionforupdate?id=${question.id}">Update</a></td>

			</tr>

		</c:forEach>

	</table>
	<br>
	<br>
	<a href="/user/adminwelcome"><button>Back</button></a>
	<br>
</body>
</html>