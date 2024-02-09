<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>La Pâte d'Or - Réservation</title>
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/reservation.css">
<link rel="stylesheet" href="css/footer.css">
<script src="https://kit.fontawesome.com/9bb344ad6f.js" crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>
<body>
	<%@include file="../jspf/header.jspf"%>



	<main class="container">
	
		<section>
		

			<h1 class="playfair-font title-display-1">${restaurant.name }</h1>
	
			<c:choose>
				<c:when test="${restaurant.id % 2 == 0 }">
					<h3 class="title-display-3">On a hâte de vous accueillir</h3>
				</c:when>
	
				<c:when test="${restaurant.id % 2 == 1 }">
					<h3 class="title-display-3">Nous vous attendons !</h3>
				</c:when>
	
			</c:choose>
	
			
			<div class="form-resa-container">
			
				<form action="reservation" method="POST">
					<p>
						<c:forEach var="schedule" items="${restaurant.schedules }">
				
							<span>(Horaires : ${schedule.getOpenHour()}-${schedule.getCloseHour()}) </span>
							
						</c:forEach>
					</p>
								
					<div class="form-group">
					
						<label for="table-select"><i class="fa-solid fa-utensils" style="color: #eeebd0;"></i></label> 
						<select id="table-select" name="tables" required>
							<option value="none">Choisissez une table</option>
							
							<c:forEach var="current" items="${restaurant.tables }">
							
								<c:if test='${!current.state.equals("pres") }'>
									<option value="${current.id }">Table n°${current.id } - ${current.numberPlace } couverts</option>
								</c:if>
								
							</c:forEach>
							
						</select>
					</div>
								
					<div class="form-group">
					
					    <div class="input-group">
					    
					    	<label for="reservation-date"><i class="fa-regular fa-calendar-days" style="color: #eeebd0;"></i></label>
					        <input type="date" id="reservation-date" name="reservation-date" min="${dateTimeInputMin }" />
					        
					        <label for="reservation-hour"><i class="fa-solid fa-clock" style="color: #eeebd0;"></i></label>
					        <input type="time" id="reservation-hour" name="reservation-hour"  />
					        
					    </div>
					    
					</div>
							
		
				
				<input type="submit" class="form-submit" value="Valider"/>
			
			</form>
		
		</div>
		</section>

		<section class="form-message-container">
		
			<h2 class="title-display-2">Contactez-nous :</h2>

			<form method="post" action="contact">
					<input type="text" name="object" id="object" placeholder=" Objet">

					<textarea class="messageContent" placeholder=" Message"
						name="message" rows="5" cols="33"></textarea>

					<input id="submitMessage" type="submit" value="Envoyer">
			</form>
			
		</section>

	</main>
	<%@include file="../jspf/footer.jspf"%>
</body>
</html>