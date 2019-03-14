<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight | Search</title>
</head>
<body>
<%@include file="partials/header.jsp"%>

	<form action="/flightresults">
		<h2>Enter a flight number</h2>
		<i>Example: UX0193</i>
		<p>
			Flight Number: <input name="flightcode" />
		</p>
		<p>
			Driver Origin Location: <input type= "text" name= "origin">
			<input type="submit" value="Calculate Pickup">
		</p>
	</form>

</body>
</html>