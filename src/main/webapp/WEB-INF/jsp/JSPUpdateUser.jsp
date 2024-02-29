<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>La Pâte d'Or - Modifier profil</title>
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/user_update.css">
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

		<h1 class="title-display-1">Modifier profil</h1>
		<div class="inputUpdateUser">
		
			<form action="updateUser" method="POST">
			
				<div class="alignLabelInput">
					<label for="name">Prénom : </label> 
					<c:choose>
						<c:when test="${errors.nameSize != null }">
							<input type="text" name="name" id="name" placeholder="${errors.nameSize }">
						</c:when>
						<c:otherwise >
							<input type="text" name="name" id="name" value="${user.name }" > 
						</c:otherwise>	
					</c:choose>
				</div>
				
				<div class="alignLabelInput">
					<label for="lastname">Nom : </label>
					<c:choose>
						<c:when test="${errors.lastnameSize != null }">
							<input type="text" name="lastname" id="lastname" placeholder="${errors.lastnameSize }">
						</c:when>
						<c:otherwise >
							<input type="text" name="lastname" id="lastname" value="${user.lastname }" > 
						</c:otherwise>	
					</c:choose>
				</div>
				
				<div class="alignLabelInput">
					<label for="email">Email : </label>
					<c:choose>
						<c:when test="${errors.emailSize != null }">
							<input type="email" name="email" id="email" placeholder="${errors.emailSize }">
						</c:when>
						<c:when test="${errors.emailMatch != null }">
							<input type="email" name="email" id="email" placeholder="${errors.emailMatch }">
						</c:when>
						<c:otherwise >
							<input type="email" name="email" id="email" value="${user.email }" > 
						</c:otherwise>	
					</c:choose>
				</div>
				
				<div class="alignLabelInput">
					<label for="password">Mot de passe: </label>
					<input type="password" name="password" id="password" placeholder="${errors.password }">
				</div>
			
				<div class="alignLabelInput">
				    <input type="submit" value="Modifier"/>
			    	<a href="updateUser?delete=true" onclick="return confirm('Etes-vous sûr de vouloir supprimer votre compte ?');">Supprimer</a>
				   	<a href="user">Retour profil</a>
				</div>
			</form>
		</div>
	
	</main>
	<%@include file="../jspf/footer.jspf"%>
</body>
</html>