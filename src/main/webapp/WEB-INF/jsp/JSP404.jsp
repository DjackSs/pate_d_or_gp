<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
   
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>La Pâte d'Or - Accueil</title><link rel="preconnect" href="https://fonts.googleapis.com">
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
<link href="./css/page404.css" rel="stylesheet"><link>
</head>
<body>

	<%@include file="../jspf/header.jspf" %>
	
	<main>
		
		<section class="page404-section container">
			<h1 class="playfair-font title-display-1">Oups !</h1>
			<h3 class="beige-subtitle title-display-3">La page que vous recherchez semble introuvable.</h3>
			<p>Code erreur : 404</p>
			<a href="accueil">Retour à l'accueil</a>
		</section>
	
	</main>

	<%@include file="../jspf/footer.jspf" %>
</body>
</html>