<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight | Detail </title>
</head>
<body>
	<%@include file="partials/header.jsp"%>

	<h2>Pickup Timing</h2>

	<h5>${flight.carrierFsCode}-${flight.flightNumber}</h5>

	

	<p>${ flight.driverOrigin } ------> DTW</p>

		<div>
<br>
		<div class="container">
  <div class="row">
    <div class="col-sm">
     <h4>Passenger Gate Arrival</h4>
      ${flight.fmtGateArrival }
    </div>
    <div class="col-sm">
     <h4>Driver Departure</h4>
      ${ flight.fmtDriverDepartureTime }
      
    </div>
    <div class="col-sm">
     <h4>Pickup</h4>
      ${ flight.fmtPickupTime}
    </div>  
</div>
  </div>
</div>

</body>
</html>