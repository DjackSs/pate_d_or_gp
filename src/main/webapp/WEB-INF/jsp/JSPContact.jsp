<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pate D'Or - Contact</title>
</head>
<body>

	<%@include file="../jspf/header.jspf" %>
	
	<main>
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