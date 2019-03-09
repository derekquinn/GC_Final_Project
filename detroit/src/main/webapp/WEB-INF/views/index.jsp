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
<p>
Hello world let's fly.
</p>



	<form action="/flight">
		<h2>Enter a flight number</h2>
		<i>Example: UX0193</i>
		<p>
			Flight Number: <input name="flightnumber" />
		</p>
		<p>
			<button type="submit">Tell me a Story!</button>
		</p>
	</form>




</body>
</html>