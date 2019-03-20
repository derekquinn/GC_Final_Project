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

	<h5><c:choose>
	<c:when test="${flightstatus.carrierFsCode eq 'DL'}"> Delta Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'AA'}"> American Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'UA'}"> United Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'WN'}"> Southwest Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'NK'}"> Spirit Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'AS'}"> Alaska Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'B6'}"> JetBlue Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'LH'}"> Lufthansa Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'RV'}"> Air Canada Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq '5D'}"> AeroMexico Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'F9'}"> Frontier Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'AF'}"> Air France Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'RJ'}"> Royal Jordanian Airlines Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq 'WW'}"> WOW air Flight </c:when>
	<c:otherwise>${flightstatus.carrierFsCode}</c:otherwise>
	</c:choose>${flightstatus.flightNumber}<br></h5>


		Pickup Location: 
			<c:choose>
			<c:when test="${flightstatus.airportResources.arrivalTerminal eq 'M'}"> McNamara Terminal</c:when>
			<c:when test="${flightstatus.airportResources.arrivalTerminal eq 'N'}"> North Terminal </c:when>
			<c:otherwise> ${flightstatus.airportResources.arrivalTerminal} </c:otherwise>
			</c:choose><br>
	

	<p>Driver Origin: ${ origlocation } </p>

		<div>
	
		<div class="container">
  
    </div>
  </div>

<br>
<br>
<br>		
			<b> Time Adjustments</b>
			<ul>
			<li>Drive Time in Current Traffic Conditions: ${ traffic } </li>
			
			<c:choose> 
			<c:when test="${bags}"> <li>Checked Baggage Pickup: 10 Minutes </li></c:when>
			<c:otherwise> <li>No Checked bags</li></c:otherwise>
			</c:choose>
			
			<li>Walk time from Gate ${flightstatus.airportResources.arrivalGate}: ${walktime} Minutes</li>
			
			<c:choose>
			<c:when test="${planesize eq '-10'}"><li>Smaller aircraft disembarkment time: 10 Minutes <i>faster</i></li></c:when>
			<c:when test="${planesize eq '10'}"><li>Larger aircraft disembarkment delay: ${planesize} Minutes</li></c:when>
			<c:when test="${planesize eq '15'}"><li>Wide-body aircraft disembarkment delay: ${planesize} Minutes</li></c:when>
			<c:otherwise> <li>Standard aircraft size: <i> No time adjustment made</i></li></c:otherwise>
			</c:choose>
			</ul>

		

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