<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>

</style> 

<title>Flight | Search</title>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- <link href="../../css/editor.css" rel="stylesheet">  -->

<!-- Custom styles for this template -->
<link  rel="stylesheet" href="/style.css"> 
<!-- <link rel="stylesheet" href="/progressbarstyle.css" /> -->

</head>


<body id="bodytest">

<%@include file="partials/header.jsp"%>



	<div class="container">

		<form action="/flightresults" style="text-align:center;">
			<br>
			<h1  style="text-align: center; color:#007bff;;">
				
					 Airport Pickup Utility(APU)</h1>
					 <h6 style="color:#007bff;"><b>Your Pathway to DTW</b></h6>
					<br><br><br><br> 
					 
					 
				<div class="form-group">
					<input style="width: 250px;" name="flightcode"
						placeholder="Flight Number Ex: DL2882 " value="${flightNum}" size="10"
						min="4" maxlength="6" required pattern="[A-Za-z0-9]{2}\d{2,4}" /><br>
					<a href="findflight">Don't Know?</a>

				</div>
				<br><br>
				<div class="form-group">
					<input type="text" name="origin" placeholder="Address Ex: 1538 Centre St, Detroit, MI" style="text-align: center:"
						size="45" required><br>
					<input type="checkbox" name="bags" value="true" style="text-align:left;"> <b style="color:#007bff;"> Checked Bags?</b>
				</div>
					<input class="btn btn-primary" type="submit" value="Calculate Pickup">
		</form>
	</div>
	

	
</body>
</html>