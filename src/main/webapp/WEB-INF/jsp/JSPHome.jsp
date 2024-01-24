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
	
		<section class="home-hero">	
			<div class="container">			
				<p class="beige-subtitle">Restaurant nantais award 2023-2024</p>	
				<h1 class="playfair-font title-display-1">La pâte d'or</h1>	
				<p>Nos chefs attendent votre commande</p>
				<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis dicta cum exercitationem, illum minima cupiditate.</p>
				<a href="#">Consultez nos restaurants</a>
			</div>
		</section>
	
	
		<section class="home-grid">		
		<c:forEach var="item" items="${restaurants}">
			<a href="restaurant?id=${item.id }">
				<article>
				<h2>${item.name}</h2>
				</article>
			</a>
		</c:forEach>
		</section>
	
	</main>


</body>
</html>