<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pâte d'Or - Reservation</title>
</head>
<body>
	<%@include file="../jspf/header.jspf" %>

	<main>
	
		<c:choose>
		
			<c:when test="${restaurant.id % 2 == 0 }">
				<h1>On a hâte de vous accueillir</h1>
				<p>Contactez-nous.</p>			
			</c:when>
			
			<c:when test="${restaurant.id % 2 == 1 }">
				<h1>Nous vous attendons !</h1>
				<p>Contactez-nous.</p>			
			</c:when>
			
		</c:choose>
		
		<h2>${restaurant.name }</h2>
	
		<form action="reservation" method="POST">
		
			<div>
				<label for="reservation-table-select">🍽</label>
				<select id="reservation-table-select" name="tables" required>
					<option value="">Choisissez une table</option>
					<c:forEach var="current" items="${restaurantTables }">
						<c:if test='${!current.state.equals("pres") }'>
							<option value="${current.id }">Table n°${current.id } - ${current.numberPlace } couverts</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			
			<c:if test="${restaurantLunchSchedule.id != null }">
				<div>
					<p>Déjeuner <span>(Horaires : ${restaurantLunchSchedule.getOpenHour()}-${restaurantLunchSchedule.getCloseHour()}) </span></p>
					<label for="lunch-reservation-date">📅</label>
					<input type="date" id="lunch-reservation-date" name="lunch-reservation-date" min="${dateTimeInputMin }" />
					<label for="lunch-reservation-hour">⏰</label>
					<input type="time" id="lunch-reservation-hour" name="lunch-reservation-hour"
					 min="${restaurantLunchSchedule.getOpenHour()}" max="${restaurantLunchSchedule.getCloseHour() }" step="1800" />
				</div>			
			</c:if>
			
			<c:if test="${restaurantDinerSchedule.id != null }">
				<div>
					<p>Diner <span>(Horaires : ${restaurantDinerSchedule.getOpenHour()}-${restaurantDinerSchedule.getCloseHour()}) </span></p>
					<label for="diner-reservation-date">📅</label>
					<input type="date" id="diner-reservation-date" name="diner-reservation-date" min="${dateTimeInputMin }"  />
					<label for="diner-reservation-hour">⏰</label>
					<input type="time" id="diner-reservation-hour" name="diner-reservation-hour"
					 min="${restaurantDinerSchedule.getOpenHour()}" max="${restaurantDinerSchedule.getCloseHour() }" step="1800" />
				</div>			
			</c:if>
			
			<div>
				<label for="reservation-object" hidden></label>
				<input hidden="true" type="text" id="reservation-object" name="reservation-object" value="Réservation utilisateur id n° à rajouter" />
				<label hidden="true" for="reservation-message"></label>
				<textarea id="reservation-message" name="reservation-message" placeholder="Un besoin particulier ? Laissez-nous un message"></textarea>				
			</div>
		
			<input type="submit" value="Valider"/>
			
		</form>
	</main>
</body>
</html>