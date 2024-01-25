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
	
		<c:if test="${restaurantLunchSchedule.id != null }">
			<form action="lunchReservation" method="POST">
				
				<p>Déjeuner <span>(Horaires : ${restaurantLunchSchedule.getOpenHour()}-${restaurantLunchSchedule.getCloseHour()}) </span></p>
				
				<div>
					<label for="lunch-reservation-table-select">🍽</label>
					<select id="lunch-reservation-table-select" name="lunch-tables" required>
						<option value="">Choisissez une table</option>
						<c:forEach var="current" items="${restaurantTables }">
							<c:if test='${!current.state.equals("pres") }'>
								<option value="${current.id }">Table n°${current.id } - ${current.numberPlace } couverts</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				
				<div>
					<label for="lunch-reservation-date">📅</label>
					<input type="date" id="lunch-reservation-date" name="lunch-reservation-date" min="${dateTimeInputMin }" />
					<label for="lunch-reservation-hour">⏰</label>
					<input type="time" id="lunch-reservation-hour" name="lunch-reservation-hour"
					 min="${restaurantLunchSchedule.getOpenHour()}" max="${restaurantLunchSchedule.getCloseHour() }" />
				</div>	
				
				<input type="submit" value="Valider"/>
			
			</form>
		</c:if>
	
		<c:if test="${restaurantDinerSchedule.id != null }">
			<form action="dinerReservation" method="POST">
			
				<p>Diner <span>(Horaires : ${restaurantDinerSchedule.getOpenHour()}-${restaurantDinerSchedule.getCloseHour()}) </span></p>
				
				<div>
					<label for="diner-reservation-table-select">🍽</label>
					<select id="diner-reservation-table-select" name="diner-tables" required>
						<option value="">Choisissez une table</option>
						<c:forEach var="current" items="${restaurantTables }">
							<c:if test='${!current.state.equals("pres") }'>
								<option value="${current.id }">Table n°${current.id } - ${current.numberPlace } couverts</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				
				<div>
					<label for="diner-reservation-date">📅</label>
					<input type="date" id="diner-reservation-date" name="diner-reservation-date" min="${dateTimeInputMin }"  />
					<label for="diner-reservation-hour">⏰</label>
					<input type="time" id="diner-reservation-hour" name="diner-reservation-hour"
					 min="${restaurantDinerSchedule.getOpenHour()}" max="${restaurantDinerSchedule.getCloseHour() }" />
				</div>			
			
				<input type="submit" value="Valider"/>
			
			</form>
			
		</c:if>
		
		<section>
		<h1>Contactez nous :</h1>
		
		<form method="post" action="contact">
		
			<fieldset>
				<legend>Envoyez-nous un message</legend>
				
				<div>
					<label for="object">Objet de votre message :</label>
					<input type="text" name="object" id="object">
					
					<textarea name="message"></textarea>
					
					<input type="submit" value="Envoyer">
				</div>
			
			</fieldset>
		
		
		</form>
		</section>
			
	</main>
</body>
</html>