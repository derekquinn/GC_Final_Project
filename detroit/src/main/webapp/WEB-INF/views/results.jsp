<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<body>

	<table>

		<thead>
			<tr>

				<th>Trip Id</th>
				<th>Carrier Code</th>
				<th>Flight Number</th>
			</tr>
		</thead>


		<tbody>

			<c:forEach var="flightstatus" items="${ flightstatus }">
				<tr>
					<td>${ flightstatus.id}</td>
					<td>${ flightstatus.carrierFsCode}</td>
					<td>${ flightstatus.flightNumber}</td>
				</tr>

			</c:forEach>

		</tbody>


	</table>


	<div>Duration in traffic ${ dur }</div>



</body>
</html>