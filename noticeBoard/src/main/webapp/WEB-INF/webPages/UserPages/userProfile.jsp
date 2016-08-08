<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updateUser" method="post" >
	First Name: <input type="text" value=${user.fName} name="fName" /> <br/>
	Last Name: <input type="text" value=${user.lName} name="lName"/> <br/>
	Email: <input type="text" value=${user.email} name="email"/> <br/>
	ContactNo: <input type="text" value=${user.contactNo} name="contactNo" /> <br/>
	Password: <input type="password" value=${user.password} name="password" /> <br/>
	<input type="submit" />
</form>
<c:if test="${isUpdated}">
	<div id="updateMessageDiv"><font color="green"><i>Your profile has been updated!</i></font></div>
</c:if>
</body>
</html>