<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<body>
	<div>
	Destination address is ${ origin.destinationAddresses }
	</div>
	
	<div> 
	Arrival address is ${ origin.arrivalAddresses }
	</div>
	<div>
	Duration in traffic ${ origin.rows.elements.durationInTraffic.humanReadable }
	</div>



</body>
</html>