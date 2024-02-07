<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pâte d'Or - Reservation</title>
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/reservation.css">
<link rel="stylesheet" href="css/footer.css">
<script src="https://kit.fontawesome.com/9bb344ad6f.js"
	crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&family=Playfair+Display:wght@400;500;600;700;800;900&display=swap"
	rel="stylesheet">
</head>
<body>
	<%@include file="../jspf/header.jspf"%>

	<main>
		<h1>${restaurant.name }</h1>

		<c:choose>
			<c:when test="${restaurant.id % 2 == 0 }">
				<h3>On a hâte de vous accueillir</h3>
				<p>Contactez-nous.</p>
			</c:when>

			<c:when test="${restaurant.id % 2 == 1 }">
				<h3>Nous vous attendons !</h3>
			</c:when>

		</c:choose>
		<div class="form-resa-container">
	<c:forEach var="lunchSchedule" items="${restaurantLunchSchedule }">
	
		<c:if test="${lunchSchedule.id != null }">
			<form action="lunchReservation" method="POST">
				
				<p>Déjeuner <span>(Horaires : ${lunchSchedule.getOpenHour()}-${lunchSchedule.getCloseHour()}) </span></p>
				
				<div class="form-group">
					<label for="lunch-reservation-table-select"><i class="fa-solid fa-utensils" style="color: #eeebd0;"></i></label> 
					<select id="lunch-reservation-table-select" name="lunch-tables" required>
						<option value="">Choisissez une table</option>
						
						<c:forEach var="current" items="${restaurant.tables }">
							<c:if test='${!current.state.equals("pres") }'>
								<option value="${current.id }">Table n°${current.id } - ${current.numberPlace } couverts</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<label for="lunch-reservation-date"><i class="fa-regular fa-calendar-days" style="color: #eeebd0;"></i></label>
					<input type="date" id="lunch-reservation-date" name="lunch-reservation-date" min="${dateTimeInputMin }" />
					<label for="lunch-reservation-hour"><i class="fa-solid fa-clock" style="color: #eeebd0;"></i></label>
					<input type="time" id="lunch-reservation-hour" name="lunch-reservation-hour"
					 min="${lunchSchedule.getOpenHour()}" max="${lunchSchedule.getCloseHour() }" />
				</div>	
				
				<input type="submit" value="Valider"/>
			
			</form>
		</c:if>
		
	</c:forEach>
	
	<c:forEach var="DinerSchedule" items="${restaurantDinerSchedule }">
	
		<c:if test="${DinerSchedule.id != null }">
			<form action="dinerReservation" method="POST">
			
				<p>Diner <span>(Horaires : ${DinerSchedule.getOpenHour()}-${DinerSchedule.getCloseHour()}) </span></p>
				
				<div class="form-group">
					<label for="diner-reservation-table-select"><i class="fa-solid fa-utensils" style="color: #eeebd0;"></i></label>
					<select id="diner-reservation-table-select" name="diner-tables" required>
						<option value="">Choisissez une table</option>
						<c:forEach var="current" items="${restaurantTables }">
							<c:if test='${!current.state.equals("pres") }'>
								<option value="${current.id }">Table n°${current.id } - ${current.numberPlace } couverts</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<label for="diner-reservation-date"><i class="fa-regular fa-calendar-days" style="color: #eeebd0;"></i></label>
					<input type="date" id="diner-reservation-date" name="diner-reservation-date" min="${dateTimeInputMin }"  />
					<label for="diner-reservation-hour"><i class="fa-solid fa-clock" style="color: #eeebd0;"></i></label>
					<input type="time" id="diner-reservation-hour" name="diner-reservation-hour"
					 min="${DinerSchedule.getOpenHour()}" max="${DinerSchedule.getCloseHour() }" />
				</div>			
			
				<input type="submit" value="Valider"/>
			
			</form>
			
		</c:if>
	
	</c:forEach>
</div>
		<section>
			<h1>Contactez nous :</h1>

			<form method="post" action="contact">
				<div class="inputContainer">
					<input type="text" name="object" id="object" placeholder=" Objet">

					<textarea class="messageContent" placeholder=" Message"
						name="message"></textarea>

					<input class="button-30" id="submitMessage" type="submit"
						value="Envoyer">
				</div>
			</form>
		</section>

	</main>
	<%@include file="../jspf/footer.jspf"%>
</body>
</html>