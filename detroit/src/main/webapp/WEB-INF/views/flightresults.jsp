<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight | Search Results</title>
</head>
<body>

	<%@include file="partials/header.jsp"%>
	<h2>Pick up Status</h2>
	<c:forEach var="flightstatus" items="${flightstatus}">
	<h5>${flightstatus.carrierFsCode}-${flightstatus.flightNumber}</h5>
				</c:forEach>
	

	<p>${ origlocation } ------> DTW</p>
	<c:forEach var="flightstatus" items="${flightstatus}">
		<div>
		<h3>Here are your Flight Results:</h3>   
		<div class="container">
  <div class="row">
    <div class="col-sm">
     <h4>Passenger Gate Arrival</h4>
      ${gatearrival }
    </div>
    <div class="col-sm">
     <h4>Driver Departure</h4>
      ${ grounddepttime }
      
    </div>
    <div class="col-sm">
     <h4>Pickup</h4>
      ${ timeatdoor }
      
    </div>
  </div>
</div>
<br>
<br>
<br>

			Expected Arrival:
			${flightstatus.operationalTimes.publishedArrival.dateLocal}<br>
			Actual Arrival:
			${flightstatus.operationalTimes.estimatedGateArrival.dateLocal}<br>

			Arrival Terminal: ${flightstatus.airportResources.arrivalTerminal}<br>
			Arrival Gate: ${flightstatus.airportResources.arrivalGate}<br>
			
			


		</div>

	</c:forEach>

	
	


	

</body>
</html>