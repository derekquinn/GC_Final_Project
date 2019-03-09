<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Test</title>
</head>
<body>
<c:forEach var="flightstatus" items="${flightstatus}">
	<div>
	<h3>${flightstatus.flightId} - ${flightstatus.carrierFsCode}-${flightstatus.flightNumber}</h3>
	
	Expected Arrival: ${flightstatus.operationalTimes.publishedArrival.dateLocal}<br>
	Actual Arrival: ${flightstatus.operationalTimes.estimatedGateArrival.dateLocal}<br>
	
	Arrival Terminal: ${flightstatus.airportResources.arrivalTerminal}<br>
	Arrival Gate: ${flightstatus.airportResources.arrivalGate}
	
	
	
	</div>





</c:forEach>

</body>
</html>