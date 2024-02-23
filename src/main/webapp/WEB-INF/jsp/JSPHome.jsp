<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>La Pâte d'Or - Accueil</title><link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
<link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
<link href="./css/navbar.css" rel="stylesheet"><link>
<link href="./css/footer.css" rel="stylesheet"><link>
<link href="./css/home.css" rel="stylesheet"><link>
</head>
<body>

	<%@include file="../jspf/header.jspf" %>
	
	<main>
	
		<section class="home-hero-section">	
		
			<div class="container">			
				<p class="beige-subtitle">Restaurants award 2023-2024</p>	
				<h1 class="playfair-font title-display-1">La Pâte d'Or</h1>	
				<p>Nos chefs attendent votre commande.</p>
				<p>De la cuisine de rue aux étoiles :</p>
				<p>Éveillez vos papilles et voyagez à travers les saveurs.</p>
				<a href="#home-restaurant-section">Consultez nos restaurants</a>
			</div>
			
		</section>
	
	
		<section id="home-restaurant-section" class="home-restaurant-section">
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
							<h3 class="playfair-font title-display-3">${restaurant.name}</h3>
							<p>(${restaurant.postalCode} ${restaurant.town.toLowerCase() })</p>
							<p>Succombez à la tentation d'une expérience culinaire hors du commun.
							Un voyage gustatif raffiné, entre terre et mer.</p>
						</div>
					</div>
				</c:forEach>
			</div>		
		</section>
		
		<section class="home-products-section">
			<h2 class="playfair-font title-display-2">Nos produits phares</h2>	
			<p>Appréciés de nos clients.</p>
			<div class="home-products-container container">
				<div class="home-products-card">
					<div class="home-products-card__box-img">
						<img alt="Confit de canard" src="././img/unsplash-confitcanard-img.jpg">
					</div>
					<div class="home-products-card__body">
						<h3 class="playfair-font title-display-3">Confit de Canard</h3>
						<p>24€00</p>
						<p>Cuisse de canard cuite lentement, avec une peau croustillante et une viande tendre.</p>
						<p>Présent dans la carte <span>Spécialités d'Été</span></p>
					</div>
				</div>
				<div class="home-products-card">
					<div class="home-products-card__box-img">
						<img alt="Panna cotta à la vanille" src="././img/unsplash-pannacotta-img.jpg">
					</div>
					<div class="home-products-card__body">
						<h3 class="playfair-font title-display-3">Panna cotta à la vanille</h3>
						<p>9€00</p>
						<p>Dessert à la vanille crémeux avec compote de baies.</p>
						<p>Présente dans la carte <span>Collection Hiver</span></p>
					</div>
				</div>
				<div class="home-products-card">
					<div class="home-products-card__box-img">
						<img alt="Cocktail Old Fashioned" src="././img/unsplash-oldfashioned-img.jpg">
					</div>
					<div class="home-products-card__body">
						<h3 class="playfair-font title-display-3">Old Fashioned</h3>
						<p>15€00</p>
						<p>Un cocktail intemporel avec bourbon, sucre et bitter.</p>
						<p>Présent dans la carte <span>Collection Hiver</span></p>
					</div>
				</div>
			</div>
		</section>
	
	</main>

	<%@include file="../jspf/footer.jspf" %>
</body>
</html>