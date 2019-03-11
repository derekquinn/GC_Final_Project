<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight | Search</title>
</head>
<body>


	<form action="/flightresults">
		<h2>Enter a flight number</h2>
		<i>Example: UX0193</i>
		<p>
			Flight Number: <input name="flightcode" />
		</p>
		<p>
		<input type= "text" name= "origin">
			<input type="submit" value="Tell Me a Story">
		</p>
	</form>

</body>
</html>