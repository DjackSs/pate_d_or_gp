<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<style>
	.pictureInfo {
		display: flex;
		justify-content: start-left;
		align-items: end;
	}
	.profilInfo {
	margin-left: 15px;
	}
</style>
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
			<p>Prï¿½nom : ${user.name }</p>
			<p>Nom : ${user.lastname }</p>
			<p>Email : ${user.email }</p>
		</div>
	</div>
	<h2>RESERVATIONS</h2>
	<table>
		<thead>
			<tr>
				<th>Restaurant</th>
				<th>Heure de rï¿½servation</th>
				<th>Statut de votre rï¿½servation</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reservation" items="${reservations}">
				<tr>
					<td><a href="restaurant">${reservation.restaurantName}</a></td>
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
	
	<section>
		<h3>Contactez nous :</h3>
		<a href="contact">Envoyez un message à l'équipe</a>
	</section>
</body>
</html>
