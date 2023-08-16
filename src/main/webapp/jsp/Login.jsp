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
<h2 style="text-align: center; color:orange;">Welcome to Login Page</h2>

<form action="/user/checkuser" style="text-align: center;"  method="post">
User type :
<select name="userType" id="user">
  <option value="admin">Admin</option>
  <option value="client">Client</option>
</select><br><br>
<b>UserId</b>   : <input type="number" name ="userId" style="align-content: center;"><br><br>
<b>UserName</b> : <input type="text" name ="userName" style="align-content: center;"><br><br>
<b>Password</b> : <input type="password" name = "password" style="align-content: center;"><br><br>
<input type="submit" >

</form>


Status :<c:out value="${message}"></c:out>
<br><br>
<br><br>
<a href="/quiz/welcome"><button style="border: thick; text-align: center;" ><b style="font-size: medium;font-family: monospace;">Back</b></button></a><br><br>
</body>
</html>