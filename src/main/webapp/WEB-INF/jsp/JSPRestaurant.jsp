<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.time.LocalTime" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>P�te d'Or - Restaurant</title>
</head>
<body>
	<%@include file="../jspf/header.jspf" %>
	
	<main>
	
		<h1>${restaurant.name }</h1>
		<h3>${restaurant.address } ${restaurant.postalCode } ${restaurant.town }</h3>
		
		<div>
			<div>
				<img src="././img/unsplash-restaurant-img1.jpg" alt="" style="width: 300px; height: 300px"/>
			</div>
			
			<article>
				<h2>${restaurantCard.name }</h2>
				
				<div>					
					<h4>Entr�e(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCardDishes }">
							<c:if test='${current.category.equals("entry") }'>
								<li>${current.name }</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
				
				<div>					
					<h4>Plat(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCardDishes }">
							<c:if test='${current.category.equals("dish") }'>
								<li>${current.name }</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
				
				<div>					
					<h4>Desserts(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCardDishes }">
							<c:if test='${current.category.equals("desert") }'>
								<li>${current.name }</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
				
				<div>					
					<h4>Boisson(s)</h4>
					<ul>
						<c:forEach var="current" items="${restaurantCardDishes }">
							<c:if test='${current.category.equals("beverage") }'>
								<li>${current.name }</li>
							</c:if>
						</c:forEach>				
					</ul>
				</div>
			</article>
		</div>	
	
		<div>
			<a href="#">R�server</a>
			<a href="#">Contact</a>
		</div>
		
		<article>
			<ul>
				<li>Mon 
					<c:forEach var="current" items="${restaurantSchedule }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>Mar  
					<c:forEach var="current" items="${restaurantSchedule }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>Mer 
					<c:forEach var="current" items="${restaurantSchedule }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>Jeu 
					<c:forEach var="current" items="${restaurantSchedule }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>Fri 
					<c:forEach var="current" items="${restaurantSchedule }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>Sam 
					<c:forEach var="current" items="${restaurantSchedule }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
				<li>Dim 
					<c:forEach var="current" items="${restaurantSchedule }">
						${current.openHour }-${current.closeHour }
					</c:forEach>	
				</li>
			</ul>
		</article>
	</main>
</body>
</html>