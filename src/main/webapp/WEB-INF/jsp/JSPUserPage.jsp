<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>La Pâte d'Or - Profil</title>
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/user_page.css">
<link rel="stylesheet" href="css/footer.css">
<script src="https://kit.fontawesome.com/9bb344ad6f.js" crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>
<body>

	<%@include file="../jspf/header.jspf"%>

	<section>
		<div class="align-i-h2">
			<div><i class="picto fa-solid fa-user"></i></div>
			<div><h2 class="first-header playfair-font">INFORMATIONS PERSONNELLES</h2></div>
		</div>
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
		<h2 class="header playfair-font">RÉSERVATIONS</h2>
	</section>
	<c:choose>

		<c:when test="${user.reservations.size() != 0 }">

			<table>
				<thead>
					<tr>
						<th>Restaurant</th>
						<th>Heures de réservation</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reservation" items="${User.reservations}">
						<tr>
							<td><a class="link" href="restaurant">${reservation.restaurantName}</a></td>
							<td>${reservation.reservationTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p class="content-default">Aucune réservation pour le moment</p>
		</c:otherwise>
	</c:choose>

	<!-- Affichage des messages -->

	<div class="pictoHeader">
		<i class="picto fa-solid fa-envelope-open-text"></i>
		<h2 class="header playfair-font">MESSAGES</h2>
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
			<p class="content-default">Aucun message pour le moment</p>
		</c:otherwise>
	</c:choose>


	<section>
		<i class="picto fa-solid fa-pen-nib"></i>
		<h2 class="header playfair-font">CONTACTEZ-NOUS :</h2>
	</section>
	<a class="link" href="contact">Envoyez un message à l'équipe</a>
	
	<%@include file="../jspf/footer.jspf" %>
</body>
</html>
