<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>La Pâte d'Or - Inscription</title>
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
<link href="./css/inscription.css" rel="stylesheet"><link>
</head>
<body>

	<%@include file="../jspf/header.jspf" %>
	
	<main>
	
		<section class="inscription-section-container container">
		
			<h1 class="playfair-font title-display-1">Créer votre compte :</h1>
			
			<form method="post" action="inscription">
				<fieldset>
					<legend>Saisissez vos informations</legend>
					
					<label for="name">Prénom</label>
					<c:choose>
						<c:when test="${errors.nameSize != null }">
							<input type="text" name="name" id="name" placeholder="${errors.nameSize }">
						</c:when>
						<c:otherwise >
							<input type="text" name="name" id="name" value="${newUser.name }" > 
						</c:otherwise>	
					</c:choose>
						
					<label for="lastname">Nom</label>
					<c:choose>
						<c:when test="${errors.lastnameSize != null }">
							<input type="text" name="lastname" id="lastname" placeholder="${errors.lastnameSize }">
						</c:when>
						<c:otherwise >
							<input type="text" name="lastname" id="lastname" value="${newUser.lastname }" > 
						</c:otherwise>	
					</c:choose>
					
					<label for="email">Email</label>
					<c:choose>
						<c:when test="${errors.emailSize != null }">
							<input type="text" name="email" id="name" placeholder="${errors.emailSize }">
						</c:when>
						<c:when test="${errors.emailMatch != null }">
							<input type="text" name="name" id="email" placeholder="${errors.emailMatch }">
						</c:when>
						<c:otherwise >
							<input type="text" name="email" id="email" value="${newUser.email }" > 
						</c:otherwise>	
					</c:choose>
					
					<label for="password">Mot de passe</label>
					<c:choose>
						<c:when test="${errors.password != null }">
							<input type="text" name="password" id="password" placeholder="${errors.password }">
						</c:when>
						<c:otherwise >
							<input type="text" name="password" id="password" > 
						</c:otherwise>	
					</c:choose>
					
					
					<input type="submit" value="Inscription">
				</fieldset>
			</form>
			
		</section>
		
	</main>
	
	<%@include file="../jspf/footer.jspf" %>
	
</body>
</html>