<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pâte d'Or - Home</title>
</head>
<body>
<%@include file="../jspf/header.jspf" %>
<h1>Bienvenue</h1>


	<c:forEach var="item" items="${restaurants}">
		<a href="restaurant?id=${item.id }">
			<article>
			<h2>${item.name}</h2>
			</article>
		</a>
	</c:forEach>

</body>
</html>