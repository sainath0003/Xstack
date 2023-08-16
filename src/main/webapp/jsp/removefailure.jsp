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


	<h2 style="text-align: center;"><c:out value="${message}"></c:out></h2> 
	<p id="mydesc"></p>

	
	<br>
	<br>
	<a href="/question/displayall"><button>Back</button></a>
	<br>
	<br>
</body>
</html>