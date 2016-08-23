<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NoticeBoard</title>
</head>
<body>
<h1>Here is a list of the most recent items you have logged, ${userName}!  </h1>
<c:forEach items="${user.eatHistory}" var="eatRecord">
	<div style="border:thick;border-color: black;">
		<h6>${eatHistory.foodName}</h6><br/>
		<h1>${eatHistory.date}</h1>
		<h1>${eatHistory.location.city} , ${eatHistory.location.state}</h1>
	</div>
</c:forEach>
<div>
	<h6>Did you eat something that i don't know about ? Let me know below:</h6>
	<form action="addEatRecord" method= "post" >
	<input type="text"  placeholder="What did you eat?" name="foodName"/> <br/>
	<input type="text" placeholder="Where did you eat it? City, State or Country" name="location"/> <br/>
	<input type="text" placeholder="WHen did you eat it? DD-MMM-YYYY HH:mm:ss" name="date"/>
	<input type="submit" />
</form>
</div>
</body>
</html>