<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>APU | Detail </title>
</head>
<body>
	<%@include file="partials/header.jsp"%>

	<h2>Pickup Timing</h2>

	<h5>${flight.carrierFsCode}-${flight.flightNumber}</h5>

	

	<p>${ flight.driverOrigin } ------> DTW</p>


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
<center>
<br> 
<c:choose>
    <c:when test="${flight.hasBags }">

<div class="alert alert-warning" role="alert">
The inbound passenger has checked bags. Pickup time has been adjusted accordingly.
</div>
    </c:when>
   
    <c:otherwise>
<div class="alert alert-success" role="alert">

The inbound passenger does <b>not</b> have checked bags. Pickup time has been adjusted accordingly.  
</center>
</div>
    </c:otherwise>
</c:choose>



<br>
<center>
<div class="progress" style="width:80%;height:170px ">
<div class="progress-bar progress-bar-striped progress-bar-animated bg-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 20%" > <B> <h4>Pickup <br> scheduled</h4> </B></div>
</center>


<br>
<center>
<div class="progress" style="width:80%;height:170px ">
<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 40%" > <B> <h4>Driver <br> inbound</h4> </B> </div>
</center>

<br>
<center>
<div class="progress" style="width:80%;height:170px ">
<div class="progress-bar progress-bar-striped progress-bar-animated " role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 60%" > <B> <h4>Driver inbound</h4> </B></div>
</center>

<br>
<center>
<div class="progress" style="width:80%;height:170px ">
<div class="progress-bar progress-bar-striped progress-bar-animated " role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 80%" > <B> <h4>Pickup <br> soon!</h4> </B></div>

</center>

<br>
<center>
<div class="progress" style="width:80%;height:170px ">
<div class="progress-bar progress-bar-striped progress-bar-animated bg-success" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 100%" > <B> <h4>Driver inbound</h4> </B></div>
</center>




</body> 
</html>