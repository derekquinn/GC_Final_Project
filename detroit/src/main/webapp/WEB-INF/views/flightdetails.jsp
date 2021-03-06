<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>APU | Flight Details</title>
</head>
<link rel="stylesheet" href="/progressbarstyle.css" />
<%@include file="partials/header.jsp"%>
	<div class="container">
<br>
<br>
		<h2>Pick Up Status</h2>
				<h5>
			<c:choose>
				<c:when test="${flight.carrierFsCode eq 'DL'}"> Delta Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'AA'}"> American Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'UA'}"> United Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'WN'}"> Southwest Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'NK'}"> Spirit Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'AS'}"> Alaska Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'B6'}"> JetBlue Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'LH'}"> Lufthansa Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'RV'}"> Air Canada Flight </c:when>
				<c:when test="${flight.carrierFsCode eq '5D'}"> AeroMexico Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'F9'}"> Frontier Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'AF'}"> Air France Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'RJ'}"> Royal Jordanian Airlines Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'WW'}"> WOW air Flight </c:when>
				<c:when test="${flight.carrierFsCode eq 'G7'}"> GoJet Flight </c:when>
				<c:when test="${flight.carrierFsCode eq '9E'}"> Endeavor Air (Delta Connection) Flight </c:when>
				<c:otherwise>${flight.carrierFsCode}</c:otherwise>
			</c:choose>${flight.flightNumber} arriving from ${flight.departureAirportFsCode }<br>
		</h5>
		

	  <!-- Start progress bar -->
  <div class="progress-meter">
    <div class="track">

      <span class="progress" style="width: 100%;"></span>

      <span class="progress" style="width: ${ progresspercent }%;"></span>

    </div>

      <ol class="progress-points" data-current="4">
    <c:forEach var="timeline" items= "${ timelinePoint }">
	 
	<li class="progress-point ${ timeline.completed ? 'completed' : '' }"> 
	 <span class="label">${timeline.description} <br>${ timeline.timeAsString }</span>
     </li>
         
     </c:forEach>   </ol>
  
  </div>
<br>
<br>
  <!-- End progress bar -->
<br>
<br>

		<!-- BEGIN GROUND TRAFFIC  OUTPUT -->

		<a class="list-group-item list-group-item-action">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">Drive Time</h5>
				<medium>${ traffic }</medium>
			</div>
			<p class="mb-1">
				This pickup originates at ${ origlocation } and ends at
				<c:choose>
					<c:when
						test="${flight.airportResources.arrivalTerminal eq 'M'}"> McNamara Terminal.</c:when>
					<c:when
						test="${flight.airportResources.arrivalTerminal eq 'N'}"> North Terminal. </c:when>
					<c:otherwise> DTW </c:otherwise>
				</c:choose>
			</p> 
		</a>

		<!-- END GROUND TRAFFIC  OUTPUT -->

		<!-- BEGIN AIRCRAFT SIZE METRICS OUTPUT -->
		<div class="list-group">

			<c:choose>

				<c:when test="${planesize eq '0'}">

					<a class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">Aircraft Size</h5>
							<medium>No deplaning delay</medium>
						</div>
						<p class="mb-1">The passenger is flying on a smaller regional
							aircraft, which allows faster deplaning.</p> 
					</a>
				</c:when>


				<c:when test="${planesize eq '20'}">

					<a class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">Aircraft Size</h5>
							<medium>20 minutes added</medium>
						</div>
						<p class="mb-1">The passenger is flying on a larger aircraft,
							which can cause deplaning delays.</p>
					</a>
				</c:when>


				<c:when test="${planesize eq '25'}">

					<a class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">Aircraft Size</h5>
							<medium>25 minutes added</medium>
						</div>
						<p class="mb-1">The passenger is flying on a wide-body
							aircraft, which can cause significant deplaning delays.</p>
							</a>
				</c:when>

				<c:otherwise>

					<a class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">Aircraft Size</h5>
							<medium>10 minutes added</medium>
						</div>
						<p class="mb-1">The passenger is flying on a medium-sized
							aircraft, which can cause some deplaning delay.</p> 
					</a>



				</c:otherwise>
			</c:choose>

		</div>
		<!-- END AIRCRAFT SIZE METRICS OUTPUT -->

		<!-- BEGIN GATE WALK OUTPUT -->
		<a class="list-group-item list-group-item-action">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">Concourse Walk Time</h5>
				<medium>${walktime} minute walk time</medium>
			</div>
			<p class="mb-1">The passenger is landing at gate
				${flight.airportResources.arrivalGate}.</p> 
		</a>

		<!-- END GATE WALK OUTPUT -->

		<!-- BEGIN BAGGAGE  OUTPUT -->

		<c:choose>
			<c:when test="${bags}">
				<a class="list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">Checked Bags</h5>
						<medium>15 minutes added</medium>
					</div>
					<p class="mb-1">The passenger has checked bags, which can cause
						delays at baggage claim.</p>    
				</a>
			</c:when>

			<c:otherwise>

				<a class="list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">Checked Bags</h5>
						<medium>No checked baggage delay</medium>
					</div>
					<p class="mb-1">The passenger has not checked bags.</p> 
				</a>


			</c:otherwise>
		</c:choose>
		<!-- END BAGGAGE  OUTPUT -->


  
<br>
<br>
<br>
<br>
  
</div>

</body>
</html>