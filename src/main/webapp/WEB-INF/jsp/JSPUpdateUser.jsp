<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification utilisateur</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<%@include file="../jspf/header.jspf"%>
	
	<main>

	<h1>Modification des données utilisateur</h1>
	<div class="inputUpdateUser">
	
		<form action="updateUser" method="POST">
		
			<div>
				<label>Prénom: </label> <input type="text" name="name"
					value="${user.name}" />
			</div>
			
			<div>
				<label>Nom: </label> <input type="text" name="lastname"
					value="${user.lastname}" />
			</div>
			
			<div>
				<label>Email: </label> <input type="email" name="email"
					value="${user.email}" />
			</div>
			
			<div>
				<label>Mot de passe: </label> <input type="password" name="password"
					placeholder="Validez avec votre mot de passe" />
			</div>
		
			<div>
				<input type="submit" value="Modifier" />
			</div>
		</form>
	</div>
	
	<a href="user">Retour à la page profil</a>

	</main>
	
</body>
</html>