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
<%@include file="partials/header.jsp"%>
	<b>Previous Searches</b>
<br>
<br>

	<table width =80%>
		<tr>
			<td style=color=#000080>>Airline</td>
			<th>Flight #</th>
			<th>Driver Departure</th>
			<th>Traffic [SEC]</th>
			<th>Origin Address</th>
			<th>Refresh</th>
			<th>Remove</th>
		</tr>


		<c:forEach var="flights" items="${flights}">

			<tr>
				<td>${flights.carrierFsCode}</td>
				<td>${flights.flightNumber}</td>
				<td>${flights.fmtDriverDepartureTime}</td>
				<td>${flights.driveDurationSec }</td>
				<td>${flights.driverOrigin }</td>	
				<td><a href="/flightstatus/update?id=${flights.id}">Update</a></td>
				<td><a href="/flightstatus/delete?id=${flights.id}">Delete</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>