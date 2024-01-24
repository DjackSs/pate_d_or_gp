<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<link rel="stylesheet" href="css/user_page.css">

</head>
<body>

	<%@include file="../jspf/header.jspf"%>

	<h1>PROFIL UTILISATEUR</h1>
	<div class="pictureInfo">
		<div class="profilPicture">
			<img alt="avatar_neutre" src="././img/avatar-neutre1.webp"
				height="300px">
		</div>
		<div class="profilInfo">
			<p>Prénom : ${user.name }</p>
			<p>Nom : ${user.lastname }</p>
			<p>Email : ${user.email }</p>
			<a href="updateUser?id=${user.id }"><button>Modifier profil</button></a>
		</div>
	</div>
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
					<td><a href="restaurant">${reservation.restaurantName}</a></td>
					 <fmt:formatDate value="${reservation.reservationTime}" pattern="dd/MM/yyyy à hh:mm" />
					<td>${reservation.reservationTime}</td>
					<td>${reservation.state}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2>MESSAGES</h2>
	<table>
		<thead>
			<tr>
				<th>Objet</th>
				<th>Contenu</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="message" items="${messages}">
				<tr>
					<td>${message.object}</td>
					<td>${message.content}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
