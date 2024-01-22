<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
</head>
<body>
	<%@ include file="../jspf/header.jspf"%>
	<h1>PROFIL UTILISATEUR</h1>
	<img alt="avatar_neutre" src="././img/avatar-neutre.webp">
	<p>${user.name }</p>
	<p>${user.lastname }</p>
	<p>${user.email }</p>
</body>
</html>