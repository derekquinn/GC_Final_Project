<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<title>Flight | Search</title>
</head>
<body>
<%@include file="partials/header.jsp"%>
	
	<p> ${ message }
	</p>


	<form action="/flightresults">
		<h2><center>Airport Pickup Utility </center><br>
		<center>Your Gateway to Detroit Metro Airport </center><br> </h2>
		<h3>Calculate your departure time</h3>
		<p>
			Flight Number: <input name="flightcode" placeholder="UX0193" value="${ flightNum}" size="10" min="4" maxlength="6" required pattern="[A-Za-z0-9]{2}\d{2,4}" />
		<a class="btn btn-primary" href="findflight">Don't Know?</a>

		</p>
		<p>
			Driver Origin Location: <input type="text" name="origin" placeholder="2100 Woodward Ave, Detroit, MI " size="35" required>



		</p>
		<input type="submit" value="Calculate Pickup"> 
		<input type="checkbox" name="bags" value="true"> Checked Bags?

	</form>


</body>
</html>