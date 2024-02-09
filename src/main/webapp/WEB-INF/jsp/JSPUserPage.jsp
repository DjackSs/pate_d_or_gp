<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

 	<main>
	
		<h1 class="title-display-1">PROFIL</h1>
		<section class="user-details-section">
			<h2 class="title-display-2"><i class="fa-solid fa-user"></i>INFORMATIONS PERSONNELLES</h2>
			<div class="user-details-content">
				<div class="pictureInfo">
					<div class="profilPicture">
						<img id="imgProfil" alt="avatar_neutre"
							src="././img/naruto-profil.webp">
					</div>
					<div class="profilInfo">
						<p><span>Prénom :</span> ${user.name }</p>
						<p><span>Nom :</span> ${user.lastname }</p>
						<p><span>Email :</span> ${user.email }</p>
						<a href="updateUser?id=${user.id }"><button>Modifier
								profil</button></a>
					</div>
				</div>
			</div>
		</section>
	
		<!-- Affichage des réservations -->
		<section class="user-resa-section">
			<h2 class="title-display-2"><i class="fa-solid fa-book-open"></i>RÉSERVATIONS</h2>
			
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
								<c:forEach var="reservation" items="${user.reservations}">
									
										<tr>
											<c:forEach var="map" items="${restaurants }">
												<c:if test="${reservation.id ==  map.key}">
													<td>
														<a class="user-resa__restaurant-link" href="restaurant?id=${map.value.id }">${map.value.name}</a>
													</td>
												</c:if>
											</c:forEach>
											
										</tr>
											<c:forEach var="reservationVO" items="${reservationVO }">
												<c:if test="${reservation.id == reservationVO.id}">
													<td>${reservationVO.reservationTime}</td>
												</c:if>
											</c:forEach>
								</c:forEach>
							</tbody>
							
						</table>
						
					
				</c:when>
				
				<c:otherwise>
				
					<p>Aucune réservation pour le moment</p>
					
				</c:otherwise>
				
			</c:choose>
			
		</section>
	
		<!-- Affichage des messages -->
		<section class="user-message-section">
			<h2 class="title-display-2"><i class="fa-solid fa-envelope-open-text"></i>MESSAGES</h2>
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
		</section>
	
	
		<section  class="user-contact-section">
			<h2 class="title-display-2"><i class="fa-solid fa-pen-nib"></i>CONTACTEZ-NOUS :</h2>
			<a class="link" href="contact">Envoyez un message à l'équipe</a>
		</section>
		
 	</main> 
	
	<%@include file="../jspf/footer.jspf" %>
</body>
</html>
