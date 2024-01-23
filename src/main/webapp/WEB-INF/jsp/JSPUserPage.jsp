<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
</head>
<body>

	<%@include file="../jspf/header.jspf"%>

	<h1>PROFIL UTILISATEUR</h1>
	<div></div>
	<img alt="avatar_neutre" src="././img/avatar-neutre.webp">
	<p>${user.name }</p>
	<p>${user.lastname }</p>
	<p>${user.email }</p>
	<h2>RESERVATIONS</h2>
	<table>
		<thead>
			<tr>
				<th>Restaurant</th>
				<th>Heure de réservation</th>
				<th>Statut de votre réservation</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reservation" items="${reservations}">
				<tr>
					<td>${reservation.restaurantName}</td>
					<td>${reservation.reservationTime}</td>
					<td>${reservation.state}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
