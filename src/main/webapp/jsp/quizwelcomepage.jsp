<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2 style="text-align: center; color:blue;">Welcome to Quiz Function Page</h2>


<h3>Enter the task to be performed :</h3>
<b style ="text-align: center; color:red;">
<a href="/quiz/add">Create a Quiz</a><br><br>
<a href="/quiz/modify">Update a Quiz</a><br><br>
<a href="/quiz/delete">Remove a Quiz</a><br><br>
<a href="/quiz/display">View a Quiz </a><br><br>
<a href="/quiz/displayall">View All Quiz</a><br>
<br></b>
<a href="/user/adminwelcome"><button >Back</button></a><br><br>
</body>
</html>