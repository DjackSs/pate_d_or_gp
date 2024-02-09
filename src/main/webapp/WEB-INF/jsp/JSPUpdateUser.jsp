<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
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
					<label>Prénom : </label>
					<input type="text" name="name" value="${user.name}" />
				</div>
				
				<div class="alignLabelInput">
					<label>Nom : </label>
					<input type="text" name="lastname" value="${user.lastname}" />
				</div>
				
				<div class="alignLabelInput">
					<label>Email : </label>
					<input type="email" name="email" value="${user.email}" />
				</div>
				
				<div class="alignLabelInput">
					<label>Mot de passe: </label>
					<input type="password" name="password"/>
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