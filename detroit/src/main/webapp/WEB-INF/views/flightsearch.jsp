<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Flight | Search</title>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Bootstrap core CSS -->
<link href="../../css/editor.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style.css" rel="stylesheet">


</head>


<body style="background-color:gray:">
	<%@include file="partials/header.jsp"%>



	<div class="container">

		<form action="/flightresults" style="text-align:center;">
			<br>
			<h1  style="text-align: center; color:#007bff;;">
					 <!--<h1 style="font-family: -apple-system, system-ui,&amp; quot; Segoe UI&amp;quot; , Roboto , &amp;quot; Helvetica Neue&amp;quot; , Arial , &amp;quot; Noto Sans&amp;quot; , sans-serif , &amp;quot; Apple Color Emoji&amp;quot; , &amp; quot; Segoe UI Emoji&amp;quot; , &amp; quot; Segoe UI Symbol&amp;quot; , &amp; quot; Noto Color Emoji&amp;quot;; color: rgb(33, 37, 41); text-align: center;"> -->
					 Airport Pickup Utility(APU)</h1>
					 <h6 style="color:#007bff;">Your Pathway to DTW</h6>
					<br><br><br><br> 
					 
					 
				<div class="form-group">
					<input style="width: 250px;" name="flightcode"
						placeholder="Flight Number" value="${ flightNum}" size="10"
						min="4" maxlength="6" required pattern="[A-Za-z0-9]{2}\d{2,4}" /><br>
					<a href="findflight">Don't Know?</a>

				</div>
				<br><br>
				<div class="form-group">
					<input type="text" name="origin" placeholder="Your Address" style="text-align: center:"
						size="45" required><br>
					<input type="checkbox" name="bags" value="true" style="text-align: left;"> Checked Bags?
				</div>
					<input class="btn btn-primary" type="submit" value="Calculate Pickup">
		</form>
	</div>
	

	
</body>
</html>