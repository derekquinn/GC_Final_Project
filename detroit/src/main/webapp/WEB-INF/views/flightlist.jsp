<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight | List</title>
</head>
<body>
<p>
<a href = "/">Home</a>
</p>
	<b>Previous Searches</b>
<br>
<br>

	<table width =33%>
		<tr>
			<th>Airline</th>
			<th>Flight #</th>
			<th>Driver Departure</th>
			<th>Refresh</th>
			<th>Remove</th>
		</tr>


		<c:forEach var="flights" items="${flights}">

			<tr>
				<td>${flights.carrierFsCode}</td>
				<td>${flights.flightNumber}</td>
				<td>${flights.fmtDriverDepartureTime}</td>
				
				<td><a href="/flightstatus/update?id=${flights.id}">Update</a></td>
				<td><a href="/flightstatus/delete?id=${flights.id}">Delete</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>