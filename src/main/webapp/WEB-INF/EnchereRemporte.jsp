<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchere Remportée</title>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/enchereRemporte.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/couleurs.css">


</head>
<body>

<jsp:include page="Header.jsp"/>

<div class="container" style="max-width: 700px">

	<h1 class="d-flex justify-content-center pt-3">Vous avez remporté la vente</h1>
	
	<div class="container transparent-card p-4" style="max-width: 750px;">

		<div class="mb-3 ps-2 element">
			<p><span style="font-weight: bold;">Article :</span> ${ articleVendu.nomArticle }</p>
		</div>
		<div class="mb-3 ps-2 element">
			<p><span style="font-weight: bold;">Description :</span> ${ articleVendu.description }</p>
		</div>
		<div class="mb-3 ps-2 element">
			<p><span style="font-weight: bold;">Meilleure offre :</span> ${enchere.montantEnchere } points</p>
		</div>
		<div class="mb-5 ps-2 element">
			<p><span style="font-weight: bold;">Prix initial :</span> ${articleVendu.prixInitial } points</p>
		</div>
		
		<div class="mb-3 mt-4 ps-2 element retrait">
			<p class=titre-retrait><span style="font-weight: bold;">Retrait</span></p>
			<div class="p-3">
				<p>
					<span style="font-weight: bold;">Rue :</span>
					${articleVendu.pointRetrait.rue}
				</p>
				<p>
					<span style="font-weight: bold;">Code Postal :</span>
					${articleVendu.pointRetrait.codePostal}
				</p>
				<p>
					<span style="font-weight: bold;">Ville :</span>
					${articleVendu.pointRetrait.ville}
				</p>
			</div>
		</div>
		<div class="mb-3 ps-2 element">
			<p><span style="font-weight: bold;">Vendeur :</span> ${ articleVendu.utilisateur.pseudo }</p>
		</div>
		<div class="mb-3 ps-2 element">
			<p><span style="font-weight: bold;">Telephone :</span> ${ articleVendu.utilisateur.telephone }</p>
		</div>
		
		<div class="mb-3 ps-2 element d-flex justify-content-center">
			<a href="${pageContext.request.contextPath}/HomePageServlet" class="btn btn-primary m-2">Retour</a>
		</div>
			
	</div>
</div>


</body>
</html>