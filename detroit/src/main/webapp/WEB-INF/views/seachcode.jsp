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

	<table class="table table-hover" width =33%">

		<thead>
			<tr>
			<th class="text-center" scope="col">Departure City</th>
			<th class="text-center" scope="col">Airlines</th>
			<th class="text-center" scope="col">Flight Number</th>
			<th class="text-center"scope="col">Select</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="flight" items="${listofflights }">
				<tr>
					<td class="text-center">${flight.departureAirportFsCode }</td>
					<td class="text-center">${flight.carrierFsCode }</td>
					<td class="text-center">${flight.flightNumber }</td>
					<td class="text-center"><a class="btn btn-primary"
						href="flightcode?carr=${flight.carrierFsCode }&num=${flight.flightNumber }">Select</a></td>
				</tr>
			</c:forEach>
		</tbody>


	</table>




</body>
</html>