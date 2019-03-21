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

	<table class="table table-hover">

		<thead>
			<tr>

				<th>Select A Flight</th>
				<th >Departure Time</th>
				<th >Departure Airport</th>
				<th >Airline </th>
				<th >Flight Number</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="flight" items="${listofflights }">
				<tr>
					<td><a class="btn btn-primary"
						href="flightcode?carr=${flight.carrierFsCode }&num=${flight.flightNumber }">Select</a></td>
					<td class="text-center">${flight.departureDate.dateLocal}</td>
					<td class="text-center">${flight.departureAirportFsCode }</td>
					<td class="text-center">${flight.carrierFsCode }</td>
					<td class="text-center">${flight.flightNumber }</td>
			
		
						</c:forEach>
					
	</table>




</body>
</html>