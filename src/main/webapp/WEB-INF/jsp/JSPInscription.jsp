<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pate D'Or - Inscription</title>
</head>
<body>
	<%@include file="../jspf/header.jspf" %>
	
	<main>
	<section>
	<h1>Crée votre compte :</h1>
	<form method="post" action="inscription">
		<fieldset>
			<legend>Saisissez vous informations :</legend>
			
			<label for="name">Nom</label>
			<input type="text" name="name" id="name">
			
			<label for="lastname">Prénom</label>
			<input type="text" name="lastname" id="lastname">
			
			<label for="email">Email</label>
			<input type="email" name="email" id="email">
			
			<label for="password">Mot de passe</label>
			<input type="password" name="password" id="password">
			
			<input type="submit" value="Inscription">
		
		</fieldset>
	
	
	</form>
	</section>
	</main>
	

</body>
</html>