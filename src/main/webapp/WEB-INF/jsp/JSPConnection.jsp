<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pate D'Or - Connection</title>
</head>
<body>
	<%@include file="../jspf/header.jspf" %>
	
	<main>
	<section>
	<h1>Connectez vous :</h1>
	<form method="post" action="connection">
		<fieldset>
			<legend>Saisissez vous identifiants :</legend>
			
			<label for="email">Email</label>
			<input type="email" name="email" id="email">
			
			<label for="password">Mot de passe</label>
			<input type="password" name="password" id="password">
			
			<input type="submit" value="Connection">
		
		</fieldset>
	
	
	</form>
	</section>
	<h2>Pas de compte ?</h2>
	<a href="inscription" >Inscrivez vous</a>
	<section>
	</section>
	</main>

</body>
</html>