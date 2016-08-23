<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NoticeBoard</title>
</head>
<body>
	<p> Welcome Home, ${userName} ! </p>
	<h3>Here is what you can do:</h3>
	<ol>
		<li><a href="./${userName}/viewProfile">View Profile</a></li>
		<li><a href="./${userName}/viewEatHistory">View Eat History</a></li>
	</ol>
</body>
</html>