<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>La Pâte d'Or - Contact</title>
<link rel="stylesheet" href="css/contact.css">
<link rel="stylesheet" href="css/footer.css">
<script src="https://kit.fontawesome.com/9bb344ad6f.js" crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>
<body>

	<%@include file="../jspf/header.jspf" %>
	
	<main>
		<section>
		
			<h1 class="playfair-font">Contactez-nous :</h1>
			
			<form method="post" action="contact">
					<div class="inputContainer">
						<div>
							<input type="text" name="object" id="object" placeholder=" Objet">
						</div>
						<div>
							<textarea name="message" placeholder=" Message"></textarea>
						</div>
						<div>
							<input type="submit" value="Envoyer" class="button-30">
						</div>
					</div>
			</form>
			
		</section>
	</main>
	
	<%@include file="../jspf/footer.jspf" %>
	
</body>
</html>