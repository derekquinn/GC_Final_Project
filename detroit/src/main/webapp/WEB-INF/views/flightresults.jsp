<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="../../css/editor.css" rel="stylesheet">
  <link href="album.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="stylesheet" href="/progressbarstyle.css" />
<link rel="stylesheet" href="style.css" />
<title>Flight | Search Results</title>
</head>
<body>

	<%@include file="partials/header.jsp"%>
	 <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading" contenteditable="true" spellcheckker="false">Pick up Details</h1>
          <p class="lead text-muted"></p>
          <p>
            
          <h5>Flight Number: ${flightstatus.carrierFsCode}-${flightstatus.flightNumber}</h5>
				<%-- </c:forEach> --%>
	

	 <p>From: ${ origlocation }</p>
          </p><br><br>
          <div class="inliner"></div>
<div class="inlined">
  
  <!-- Start component -->
  <div class="progress-meter">
    <div class="track">
      <span class="progress" style="width: 50%;"></span>
    </div>
    <ol class="progress-points" data-current="4">
    <c:forEach var="timeline" items= "${ timelinePoint }">
<%-- <%-- 
   <!--    <li class="progress-point ${ timeline.completed ? 'completed' : '' }">-->
        <span class="label">${timeline.description} ${ timeline.time }</span>
   <!--     </li>-->
      </c:forEach> --%> 
     <%--  <li class="progress-point ${ timeline.completed ? 'completed' : '' }"> --%>
      <li class="progress-point completed">
        <span class="label">${timeline.description} ${ timeline.time }</span>
      </li>
     </c:forEach>

    </ol>
  </div>
          
        </div>
      </section>
     

   <%--    <div class="album py-5 bg-light"><span class="badge badge-primary" style="border: 1px dashed rgb(66, 133, 244);">Primary badge</span>
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
     <h4>Passenger To Arrivals Door</h4>
      ${ timeatdoor }
      
    </div>
  </div>
</div>
<br>
<br>
<br>
		</div>

	</c:forEach> --%>
	
	
	<%-- <div class="inliner"></div>
<div class="inlined">
  
  <!-- Start component -->
  <div class="progress-meter">
    <div class="track">
      <span class="progress" style="width: 50%;"></span>
    </div>
    <ol class="progress-points" data-current="4">
    <c:forEach var="timeline" items= "${ timelinePoint }">
<%-- 
   <!--    <li class="progress-point ${ timeline.completed ? 'completed' : '' }">-->
        <span class="label">${timeline.description} ${ timeline.time }</span>
   <!--     </li>-->
      </c:forEach> 
      <li class="progress-point ${ timeline.completed ? 'completed' : '' }">
      <li class="progress-point completed">
        <span class="label">${timeline.description} ${ timeline.time }</span>
      </li>
     </c:forEach>

    </ol>
  </div>

  <!-- End component -->
  
</div>

	 --%>
</body>
</html>