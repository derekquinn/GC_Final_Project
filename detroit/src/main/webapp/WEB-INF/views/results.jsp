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

<c:forEach var="results" items="${ trix }">
	<div>
	Destination address is ${ results.destination_addresses }
	Arrival address is ${ results.arrival_addresses }
	Duration in traffic ${ results.rows.elements.durationInTraffic.humanReadable }
	</div>
</c:forEach>
	

</body>
</html>