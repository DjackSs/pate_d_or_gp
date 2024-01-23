<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pâte d'Or - Reservation</title>
</head>
<body>
	<%@include file="../jspf/header.jspf" %>

	<main>
		<c:choose>
			<c:when test="${restaurant.id % 2 == 0 }">
				<h1>On a hâte de vous accueillir</h1>
				<p>Contactez-nous.</p>			
			</c:when>
			<c:when test="${restaurant.id % 2 == 1 }">
				<h1>Nous vous attendons !</h1>
				<p>Contactez-nous.</p>			
			</c:when>
		</c:choose>
		
		<h2>${restaurant.name }</h2>
	
		<form action="user" method="POST">
			<div>
				<label for="reservationTable"></label>
				<select id="">
					<option></option>
				</select>
			</div>
		
		
		</form>
	</main>
</body>
</html>