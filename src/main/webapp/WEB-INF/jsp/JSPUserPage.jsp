<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<link rel="stylesheet" href="css/user_page.css">
<script src="https://kit.fontawesome.com/9bb344ad6f.js"
	crossorigin="anonymous"></script>
</head>
<body>

	<%@include file="../jspf/header.jspf"%>

	<h1>PROFIL UTILISATEUR</h1>
	<section>
		<i class="picto fa-solid fa-user"></i>
		<h2 class="header">INFORMATIONS PERSONNELLES</h2>
	</section>
	<div class="pictureInfo">
		<div class="profilPicture">
			<img id="imgProfil" alt="avatar_neutre"
				src="././img/naruto-profil.webp" height="300px">
		</div>
		<div class="profilInfo">
			<p>Prénom : ${user.name }</p>
			<p>Nom : ${user.lastname }</p>
			<p>Email : ${user.email }</p>
			<a href="updateUser?id=${user.id }"><button>Modifier
					profil</button></a>
		</div>
	</div>

	<!-- Affichage des réservations -->
	<section>
		<i class="picto fa-solid fa-book-open"></i>
		<h2 class="header">RESERVATIONS</h2>
	</section>
	<c:choose>
		<c:when test="${user.reservations.size() != 0 }">
			<table>
				<thead>
					<tr>
						<th>Restaurant</th>
						<th>Heure de réservation</th>
						<th>Statut de votre réservation</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reservation" items="${User.reservations}">
						<tr>
							<td><a href="restaurant">${reservation.restaurantName}</a></td>
							<td>${reservation.reservationTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>Aucune réservation pour le moment</p>
		</c:otherwise>
	</c:choose>

	<!-- Affichage des messages -->

	<div class="pictoHeader">
		<i class="picto fa-solid fa-envelope-open-text"></i>
		<h2 class="header">MESSAGES</h2>
	</div>
	<c:choose>
		<c:when test="${user.messages.size() != 0 }">
			<table>
				<thead>
					<tr>
						<th>Objet</th>
						<th>Contenu</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="message" items="${user.messages}">
						<tr>
							<td>${message.object}</td>
							<td>${message.content}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>Aucun message pour le moment</p>
		</c:otherwise>
	</c:choose>


	<section>
		<i class="picto fa-solid fa-pen-nib"></i>
		<h2 class="header">CONTACTEZ NOUS :</h2>
	</section>
	<a href="contact">Envoyez un message à l'équipe</a>
</body>
</html>
