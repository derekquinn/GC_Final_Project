<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/progressbarstyle.css" />
<title>Flight | Search Results</title>
</head>
<body>

	<%@include file="partials/header.jsp"%>
	<h2>Pick up Status</h2>
	<%-- <c:forEach var="flightstatus" items="${flightstatus}"> --%>
	<h5><c:choose>
	<c:when test="${flightstatus.carrierFsCode eq 'DL'}"> Delta Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'AA'}"> American Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'UA'}"> United Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'WN'}"> Southwest Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'NK'}"> Spirit Airlines Flight </c:when>
	<c:otherwise>${flightstatus.carrierFsCode}</c:otherwise>
	</c:choose>${flightstatus.flightNumber}<br></h5>
				<%-- </c:forEach> --%>

	<p>${ origlocation } ------> DTW</p>
	<%-- <c:forEach var="flightstatus" items="${flightstatus}"> --%>
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
     <h4>Passenger To Arrivals Door</h4>
      ${ timeatdoor }
      
    </div>
  </div>
</div>
<br>
<br>
<br><%-- 
			Expected Arrival:
			${flightstatus.operationalTimes.publishedArrival.dateLocal}<br>
			Actual Arrival:
			${flightstatus.operationalTimes.estimatedGateArrival.dateLocal}<br> --%>


			
			Arrival Terminal: 
			<c:choose>
			<c:when test="${flightstatus.airportResources.arrivalTerminal eq 'M'}"> McNamara Terminal</c:when>
			<c:when test="${flightstatus.airportResources.arrivalTerminal eq 'N'}"> North Terminal </c:when>
			<c:otherwise> ${flightstatus.airportResources.arrivalTerminal} </c:otherwise>
			</c:choose><br>
			Arrival Gate: ${flightstatus.airportResources.arrivalGate}<br>
			Drive Time in Current Traffic Conditions: ${ traffic } <br> 
			Time adjustment due to bags: <br>
			Time adjustment due to gate walk:<br>
			Time adjustment due to flight size:<br>
		</div>

	<%-- </c:forEach> --%>
	
	
	<!-- <div class="inliner"></div> -->
<div class="inlined">
  
  <!-- Start progress bar -->
  <div class="progress-meter">
    <div class="track">
      <span class="progress" style="width: 50%;"></span>
    </div>

      <ol class="progress-points" data-current="4">
    <c:forEach var="timeline" items= "${ timelinePoint }">
	 
	<li class="progress-point ${ timeline.completed ? 'completed' : '' }"> 
	 <span class="label">${timeline.description} ${ timeline.timeAsString }</span>
     </li>
      
    
      
    
     </c:forEach>
     </ol>
  
  </div>

  <!-- End progress bar -->
  
</div>

	
</body>
</html>