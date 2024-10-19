<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Login Form</h2>

	<form action="loginValidation" method="post">
	
		<input type="text" placeholder="Enter your name" name="name">
		<input type="password" placeholder="Enter your password" name="pass">
		
		<input type="submit" value="Done">
	
	
	
	</form>
	
	<h2>${msg}</h2>



</body>
</html>