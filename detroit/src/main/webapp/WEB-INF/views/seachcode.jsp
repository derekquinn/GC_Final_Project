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

	<table class="table table-hover" width=33%">

		<thead>
			<tr>

				<th width="16%">Select Your Flight</th>
				<th width="15%">Departure Time</th>
				<th width="17%">Departure Airport</th>
				<th width="17%">Airline Companies</th>
				<th width="15%">Flight Number</th>
				<th width="25%">Origin Departure City</th>




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
					<c:forEach var="a" items="${airportInfo}">

							<c:if test="${flight.departureAirportFsCode == a.cityCode }">
								<td>${a.city}</td>

							</c:if>
							<%-- <c:if test="${flight.departureAirportFsCode != a.cityCode }">
						<td>Unknown</td>
						</c:if> --%>


							<%--  						<c:choose>

							<c:when test="${flight.departureAirportFsCode == a.cityCode }">
								<td>${a.city}</td>
							</c:when>

							<c:otherwise>
								<td>Unknown</td>
							</c:otherwise>
						</c:choose>  --%>
						</c:forEach>
						</c:forEach>
	</table>




</body>
</html>