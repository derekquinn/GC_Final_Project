<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <link href="../../css/editor.css" rel="stylesheet">
  <link href="album.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Flight | Search</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>
<%@include file="partials/header.jsp"%>
	
	<p> ${ message }
	</p>


	<form action="/flightresults">
		
		<!--    <form name="f1" method="get" action="#">
      <select name="clr">
          <option>Aeromexico</option>
          <option>Air Canada</option>
          <option>Air France</option>
          <option>Alaska Airlines</option>
          <option>American Airlines</option>
          <option>Delta Air Lines</option>
          <option>Frontier Airlines</option>
          <option>JetBlue</option>
          <option>Lufthansa</option>
          <option>Royal Jordanian Airlines</option>
          <option>Southwest Airlines</option>
          <option>Spirit Airlines</option>
          <option>United Airlines</option>
          <option>WOW air</option>
          
      </select>
   
   </form> -->
		 
		
		<p>
			Flight Number: <input name="flightcode" placeholder="UX0193"size="10" min="4" maxlength="6" required pattern="[A-Za-z]{2}\d{1,4}" />
		</p>
		<p>
			Driver Origin Location: <input type="text" name="origin" placeholder="2100 Woodward Ave, Detroit, MI " size="35" required>



		</p>
		<input type="submit" value="Calculate Pickup"> 
		<input type="checkbox" name="bags" value="true"> Checked Bags?

	</form>

</body>
</html>