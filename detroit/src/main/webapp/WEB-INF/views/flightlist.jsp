<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Flight | List</title>
</head>
<body>
<%@include file="partials/header.jsp"%>
<p>

</p>
	<b>Previous Searches</b>
<br>
<br>

	<table class="table table-hover" width =33%>
		<tr>
			<th scope="col">Airline</th>
			<th scope="col">Flight #</th>
			<th scope="col">Driver Departure</th>
			<th scope="col">Refresh</th>
			<th scope="col">Remove</th>
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