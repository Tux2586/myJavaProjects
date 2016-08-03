<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addUser" method="post" >
	<label>First Name: </label> <input type="text" name="fName"/><br/>
	<label>Last Name: </label> <input type="text" name="lName"/><br/>
	<label>Username: </label> <input type="text" name="uName"/><br/>
	<label>Password: </label> <input type="password" name="password"/><br/>
	<label>Email: </label> <input type="text" name="email"/><br/>
	<label>Contact#: </label> <input type="text" name="contactNo"/><br/>
	<input type="submit" />
</form>
</body>
</html>