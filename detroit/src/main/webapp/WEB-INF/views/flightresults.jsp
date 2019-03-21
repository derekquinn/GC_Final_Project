<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<%@include file="partials/header.jsp"%>
<link rel="stylesheet" href="/progressbarstyle.css" />

<title>APU | Flight Results</title>
</head>

	<div class="container">

<br>
		<h2>Pick Up Status</h2>
				<h5>
			<c:choose>
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
			</c:choose>${flightstatus.flightNumber} arriving from ${flightstatus.departureAirportFsCode }<br>
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



		<!-- BEGIN GROUND TRAFFIC  OUTPUT -->

		<a class="list-group-item list-group-item-action">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">Drive Time</h5>
				<small>${ traffic }</small>
			</div>
			<p class="mb-1">
				This pickup originates at ${ origlocation } and ends at
				<c:choose>
					<c:when
						test="${flightstatus.airportResources.arrivalTerminal eq 'M'}"> McNamara Terminal.</c:when>
					<c:when
						test="${flightstatus.airportResources.arrivalTerminal eq 'N'}"> North Terminal. </c:when>
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
							<small>No deplaning delay</small>
						</div>
						<p class="mb-1">The passenger is flying on a smaller regional
							aircraft, which allows faster deplaning.</p> 
					</a>
				</c:when>


				<c:when test="${planesize eq '20'}">

					<a class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">Aircraft Size</h5>
							<h4>20 minutes added</h4>
						</div>
						<p class="mb-1">The passenger is flying on a larger aircraft,
							which can cause deplaning delays.</p>
					</a>
				</c:when>


				<c:when test="${planesize eq '25'}">

					<a class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">Aircraft Size</h5>
							<small>25 minutes added</small>
						</div>
						<p class="mb-1">The passenger is flying on a wide-body
							aircraft, which can cause significant deplaning delays.</p>
							</a>
				</c:when>

				<c:otherwise>

					<a class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">Aircraft Size</h5>
							<small>10 minutes added</small>
						</div>
						<p class="mb-1">The passenger is flying on a medium-sized
							aircraft, which can cause some deplaning delay.</p> 
					</a>



				</c:otherwise>
			</c:choose>

		</div>
		<!-- END AIRCRAFT SIZE METRICS OUTPUT -->

		<!-- BEGIN GATE WALK OUTPUT -->
			<c:choose>

				<c:when test="${flightstatus.airportResources.arrivalTerminal eq 'N'}">
		<a class="list-group-item list-group-item-action">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">Concourse Walk Time</h5>
				<small>${walktime} minute walk time</small>
			</div>
			<p class="mb-1">The passenger is landing at gate
				${flightstatus.airportResources.arrivalGate}.</p> 
		</a></c:when>
		<c:when test="${flightstatus.airportResources.arrivalTerminal eq 'M'}">
		<a class="list-group-item list-group-item-action">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">Concourse Walk Time</h5>
				<small>${walktime} minute walk time</small>
			</div>
			<p class="mb-1">The passenger is landing at gate
				${flightstatus.airportResources.arrivalGate}.</p> 
		</a></c:when>
		
				<c:otherwise>
		<a class="list-group-item list-group-item-action">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">Concourse Walk Time</h5>
				<small>Walk Time Unavailable</small>
			</div>
			<p class="mb-1">Walking time between gates varies depending on terminal. Refresh closer to landing time to see more.</p> 
		</a></c:otherwise>
		
		
		</c:choose>

		<!-- END GATE WALK OUTPUT -->

		<!-- BEGIN BAGGAGE  OUTPUT -->

		<c:choose>
			<c:when test="${bags}">
				<a class="list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">Checked Bags</h5>
						<small>15 minutes added</small>
					</div>
					<p class="mb-1">The passenger has checked bags, which can cause
						delays at baggage claim.</p>    
				</a>
			</c:when>

			<c:otherwise>

				<a class="list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">Checked Bags</h5>
						<small>No checked baggage delay</small>
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