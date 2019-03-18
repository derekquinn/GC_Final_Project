<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search By Airline</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>
	<%@include file="partials/header.jsp"%>

	<p>${ message }</p>

	<table class="table">

		<thead>
			<tr>
			<th width="20%">Original Departure City</th>
			<th width="20%">Airline Companies</th>
			<th width="20%">Flight Number</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="flight" items="${listofflights }">
				<tr>
					<td>${flight.departureAirportFsCode }</td>
					<td>${flight.carrierFsCode }</td>
					<td>${flight.flightNumber }</td>
					<td><a class="btn btn-primary"
						href="flightcode?carr=${flight.carrierFsCode }&num=${flight.flightNumber }">Select</a></td>
				</tr>
			</c:forEach>
		</tbody>


	</table>




</body>
</html>