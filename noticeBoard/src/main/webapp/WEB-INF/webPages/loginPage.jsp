<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NoticeBoard</title>
</head>
<body>
<c:if test="${isInvalid}">
	<div id="errorDiv" style="hidden"><font color="red"><i>Username or password is incorrect!</i></font></div>
</c:if>
<div>
<form action="loginUser" method="post" >
	<label>Username: </label> <input type="text" name="uName"/><br/>
	<label>Password: </label> <input type="password" name="password"/><br/>
	<input type="submit" />
</form>
</div>
</body>
</html>