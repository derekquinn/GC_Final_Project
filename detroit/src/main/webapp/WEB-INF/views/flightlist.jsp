<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>API | Flights</title>
</head>
<body>
<%@include file="partials/header.jsp"%>

<br>
<br>


	<table class="table table-hover" width =33%>
		<tr>
		
			<th class="text-center" width =4% >Details</th>
			<th class="text-center" scope="col">Driver Departure</th>
			<th class="text-center" scope="col">Airline</th>
			<th class="text-center" scope="col">Flight #</th>
			<!-- <th class="text-center" scope="col">Driver Departure</th> -->
			<!-- <th class="text-center" scope="col">Traffic [SEC]</th> -->
			<th class="text-center" scope="col">Origin Address</th>
			<th class="text-center" width =4%>Refresh</th>
			<th class="text-center" width =4%>Remove</th>
</tr>


		<c:forEach var="flights" items="${flights}">

			<tr>
				
			<td class="text-center"><a href="/flights/${flights.id}">Details</a></td>
			<td class="text-center">${flights.fmtDriverDepartureTime}</td>
				<td class="text-center">${flights.carrierFsCode}</td>
				<td class="text-center">${flights.flightNumber}</td>
				<%-- <td class="text-center">${flights.fmtDriverDepartureTime}</td> --%>
				<%-- <td class="text-center">${flights.driveDurationSec }</td> --%>
				<td class="text-center">${flights.driverOrigin }</td>	
				<td class="text-center"><a href="/flightstatus/update?id=${flights.id}">Update</a></td>
				<td class="text-center"><a href="/flightstatus/delete?id=${flights.id}">Delete</a></td>
				
			</tr>
		</c:forEach>

	</table>

</body>
</html>