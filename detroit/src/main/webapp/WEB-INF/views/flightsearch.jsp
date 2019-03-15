<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight | Search</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>
<%@include file="partials/header.jsp"%>
	
	<p> ${ message }
	</p>


	<form action="/flightresults">
		<h2>Calculate your departure time</h2>
		<p>
			Flight Number: <input name="flightcode" placeholder="UX0193"size="10" min="4" maxlength="6" required pattern="[A-Za-z]{2}\d{2,4}" />
		</p>
		<p>
			Driver Origin Location: <input type="text" name="origin" placeholder="2100 Woodward Ave, Detroit, MI " size="35" required>



		</p>
		<input type="submit" value="Calculate Pickup"> 
		<input type="checkbox" name="bags" value="true"> Checked Bags?

	</form>

</body>
</html>