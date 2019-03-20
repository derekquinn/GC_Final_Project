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

	<!--  <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading" contenteditable="true" spellcheckker="false">Pick up Details</h1>
          <p class="lead text-muted"></p>
          <p> -->
<%--             
          <h5>Flight Number: ${flightstatus.carrierFsCode}-${flightstatus.flightNumber}</h5>
				
	

	 <p>From: ${ origlocation }</p>
          </p><br><br>
        
     

      <div class="album py-5 bg-light"><span class="badge badge-primary" style="border: 1px dashed rgb(66, 133, 244);">Primary badge</span>
        <div class="container">

          <div class="row">
            <div class="col-md-4">
              <div class="card mb-4 box-shadow">
                
                <div class="card-body">
                  <p class="card-text" style="text-align: center;">Passenger Gate Arrival</p><div><br></div><div style="text-align: center;">Time</div><p></p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
	<c:forEach var="flightstatus" items="${flightstatus}">
	<h5>${flightstatus.carrierFsCode}-${flightstatus.flightNumber}</h5>
				</c:forEach>
	

	<p>${ origlocation } ------> DTW</p>
	<c:forEach var="flightstatus" items="${flightstatus}"> --%>

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
	<c:when test="${flightstatus.carrierFsCode eq 'G7'}"> GoJet Flight </c:when>
	<c:when test="${flightstatus.carrierFsCode eq '9E'}"> Endeavor Air (Delta Connection) Flight </c:when>
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

      <span class="progress" style="width: 100%;"></span>

      <span class="progress" style="width: ${ progresspercent }%;"></span>

    </div>

      <ol class="progress-points" data-current="4">
    <c:forEach var="timeline" items= "${ timelinePoint }">
	 
	<li class="progress-point ${ timeline.completed ? 'completed' : '' }"> 
	 <span class="label">${timeline.description} ${ timeline.timeAsString }</span>
     </li>
      
    
      
    
     </c:forEach>   </ol>
  
  </div>

  <!-- End progress bar -->
  
</div>
 


</body>
</html>