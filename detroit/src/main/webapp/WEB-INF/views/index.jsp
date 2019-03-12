<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyFlight | Home</title>
</head>
<body>
<p>
Hello world let's fly.
</p>

<form action="/results">
  Flight number:<br>
  <input type="text" name="destination_addresses"><br>
  Where Are You Leaving From :<br>
  <input type="text" name="origin_addresses"><br><br>
  <input type="submit" value="Submit">
</form>





</body>
</html>