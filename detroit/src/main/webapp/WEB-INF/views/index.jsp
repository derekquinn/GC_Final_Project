<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyFlight | Home</title>
</head>
<body>
Hello world let's fly.
<c:forEach var="flightStatus" items="${donut}">


<tr><td><a href="/details/${donut.id }">${donut.name }</a></td>
<td>${donut.id }</td>

</tr>

</c:forEach>
</body>
</html>