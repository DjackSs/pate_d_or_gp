<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pâte d'Or - Home</title><link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
<link href="./css/home.css" rel="stylesheet"><link>
</head>
<body>
	<%@include file="../jspf/header.jspf" %>
	
	<main>
	
		<section class="home-hero-section">	
			<div class="container">			
				<p class="beige-subtitle">Restaurants award 2023-2024</p>	
				<h1 class="playfair-font title-display-1">La pâte d'or</h1>	
				<p>Nos chefs attendent votre commande.</p>
				<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis dicta cum exercitationem, illum minima cupiditate.</p>
				<a href="#">Consultez nos restaurants</a>
			</div>
		</section>
	
	
		<section class="home-restaurant-section">
			<p>Aliments frais et de qualité supérieure</p>
			<h2 class="playfair-font title-display-2">Asseyez-vous, détendez-vous, on s'occupe de tout</h2>	
			<p>Voici un aperçu de nos restaurants</p>
			
			<div class="home-restaurant-grid container">
				<c:forEach var="restaurant" items="${restaurants}">
					<div class="home-restaurant-card">
						<a href="restaurant?id=${restaurant.id }"></a>
						<div class="home-restaurant-card__box-img">
							<c:choose>
								<c:when test="${restaurant.id % 2 == 0 }">
									<img alt="" src="././img/unsplash-restaurant-img1.jpg">
								</c:when>
								<c:when test="${restaurant.id % 2 == 1 }">
									<img alt="" src="././img/unsplash-restaurant-img2.jpg">
								</c:when>
							</c:choose>
						</div>
						<div class="home-restaurant-card__body">
							<h3>${restaurant.name}</h3>
							<p>(${restaurant.postalCode} ${restaurant.town })</p>
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis, cumque.</p>
						</div>
					</div>
				</c:forEach>
			</div>		
		</section>
		
		<section class="home-products-section">
			<h2 class="playfair-font title-display-2">Nos produits phares</h2>	
			<p>Voici un aperçu de nos restaurants</p>
		</section>
	
	</main>


</body>
</html>