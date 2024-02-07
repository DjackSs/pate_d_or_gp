<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification utilisateur</title>
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/user_update.css">
<link rel="stylesheet" href="css/footer.css">
<script src="https://kit.fontawesome.com/9bb344ad6f.js" crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>

<body>
	<%@include file="../jspf/header.jspf"%>
	
	<main>

	<h1 class="playfair-font">Modification de vos informations personnelles</h1>
	<div class="inputUpdateUser">
	
		<form action="updateUser" method="POST">
		
			<div class="alignLabelInput">
				<div><label>Prénom: </label></div>
				<div><input type="text" name="name" value="${user.name}" /></div>
			</div>
			
			<div class="alignLabelInput">
				<div><label>Nom: </label></div>
				<div><input type="text" name="lastname" value="${user.lastname}" /></div>
			</div>
			
			<div class="alignLabelInput">
				<div><label>Email: </label></div>
				<div><input type="email" name="email" value="${user.email}" /></div>
			</div>
			
			<div class="alignLabelInput">
				<div><label>Mot de passe: </label></div>
				<div><input type="password" name="password" placeholder="Validez avec votre mot de passe" /></div>
			</div>
		
			<div class="alignLabelInput">
			    <div><input type="submit" value="Modifier" class="buttonStyle" /></div>
			    <div><a href="#"><button class="buttonStyle">Supprimer</button></a></div>
			    <div><a href="user"><button class="buttonStyle">Retour au profil</button></a></div>
			</div>
		</form>
	</div>
	

	</main>
	<%@include file="../jspf/footer.jspf"%>
</body>
</html>