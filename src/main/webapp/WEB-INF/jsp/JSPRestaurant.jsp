<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>La Pâte d'Or - Restaurant</title><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
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
<link href="./css/restaurant.css" rel="stylesheet"><link>
</head>
<body>
	<%@include file="../jspf/header.jspf" %>
	
	<main>
	
		<h1 class="playfair-font title-display-1">${restaurant.name }</h1>
		<h3 class="title-display-3 beige-subtitle">${restaurant.address } ${restaurant.postalCode } ${restaurant.town }</h3>
		
		<section class="restaurant-content-section container">
			<div class="restaurant-content__img-box">
				<img src="././img/unsplash-restaurant-img1.jpg" alt="" />
			</div>
			
			<article class="restaurant-content__card-box">
				<h2 class="playfair-font title-display-2">${restaurantCard.name }</h2>
				
				<div>					
					<h4 class="beige-subtitle">Entrée(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCard.dishes }">
							<c:if test='${current.category.equals("entry") }'>
								<li>${current.name } (${current.price }€)</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
				
				<div>					
					<h4 class="beige-subtitle">Plat(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCard.dishes }">
							<c:if test='${current.category.equals("dish") }'>
								<li>${current.name } (${current.price }€)</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
				
				<div>					
					<h4 class="beige-subtitle">Desserts(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCard.dishes }">
							<c:if test='${current.category.equals("desert") }'>
								<li>${current.name } (${current.price }€)</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
				
				<div>					
					<h4 class="beige-subtitle">Boisson(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCard.dishes }">
							<c:if test='${current.category.equals("beverage") }'>
								<li>${current.name } (${current.price }€)</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
			</article>
		</section>
		
		<div class="restaurant-calltoaction-section">
		
				<c:if test='${user != null }'>
					<a href="reservation?idRestaurant=${restaurant.id }">Réserver</a>
				</c:if>
		</div>	
	
		
		<article class="restaurant-schedule-section container">
			<ul>
				<li>
					<span class="beige-subtitle">Lundi</span> 
					<c:forEach var="current" items="${restaurant.schedules }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>
					<span class="beige-subtitle">Mardi</span>  
					<c:forEach var="current" items="${restaurant.schedules }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>
					<span class="beige-subtitle">Mercredi</span> 
					<c:forEach var="current" items="${restaurant.schedules }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>
					<span class="beige-subtitle">Jeudi</span> 
					<c:forEach var="current" items="${restaurant.schedules }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>
					<span class="beige-subtitle">Vendredi</span> 
					<c:forEach var="current" items="${restaurant.schedules }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>
					<span class="beige-subtitle">Samedi</span> 
					<c:forEach var="current" items="${restaurant.schedules }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>
					<span class="beige-subtitle">Dimanche</span> 
					<c:forEach var="current" items="${restaurant.schedules }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
			</ul>
		</article>
	</main>
	
	<%@include file="../jspf/footer.jspf" %>
</body>
</html>