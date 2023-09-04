<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail vente</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous" defer></script>

</head>
<body>

	<jsp:include page="Header.jsp" />

	<h1>Detail vente</h1>

	<div class="container detail">

		<div class="mb-2">
			<h4>Article: ${ articleVendu.nomArticle }</h4>
		</div>
		<div class="mb-2">
			<h4>Description: ${ articleVendu.description }</h4>
		</div>
		<div class="mb-2">
			<h4>Catégorie: ${ articleVendu.categorie.libelle }</h4>
		</div>
		<div class="mb-2">
			<h4>Meilleure offre:</h4>
		</div>
		<div class="mb-2">
			<h4>Mise à prix: ${ articleVendu.prixInitial }</h4>
		</div>
		<div class="mb-2">
			<h4>Fin de l'enchère: ${ articleVendu.dateFinEncheres }</h4>
		</div>
		<div class="mb-2">
			<h4>Retrait: ${ articleVendu.pointRetrait.rue }</h4>
			<h4>Retrait: ${ articleVendu.pointRetrait.codePostal }</h4>
			<h4>Retrait: ${ articleVendu.pointRetrait.ville }</h4>
		</div>
		<div class="mb-2">
			<h4>Vendeur: ${ articleVendu.utilisateur.pseudo }</h4>
		</div>
		<div class="mb-2">
			<h4>Ma proposition:</h4>
		</div>





	</div>


</body>
</html>