<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/detailVente.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/couleurs.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous" defer></script>

</head>
<body>
	<jsp:useBean id="date" class="java.util.Date"/>
	<jsp:include page="Header.jsp" />

	<h1 class="detail">Détail vente</h1>

	<div class="container transparent-card p-4" style="max-width: 750px;">

		<div class="mb-3 ps-2 element">
			<p>
				<span style="font-weight: bold;">Article :</span> ${ articleVendu.nomArticle }
			</p>
		</div>
		<div class="mb-3 ps-2 element">
			<p>
				<span style="font-weight: bold;">Description :</span> ${ articleVendu.description }
			</p>
		</div>
		<div class="mb-3 ps-2 element">
			<p>
				<span style="font-weight: bold;">Catégorie :</span> ${ articleVendu.categorie.libelle }
			</p>
		</div>
		<div class="mb-3 ps-2 element">
			<p>
				<span style="font-weight: bold;">Meilleure offre :</span> ${ enchere.montantEnchere }
				points
			</p>
		</div>
		<div class="mb-3 ps-2 element">
			<p>
				<span style="font-weight: bold;">Mise à prix :</span> ${ articleVendu.prixInitial }
				points
			</p>
		</div>
		<div class="mb-5 ps-2 element">
			<p>
				<span style="font-weight: bold;">Fin de l'enchère:</span> ${ articleVendu.dateFinEncheresFormatted }
			</p>
		</div>
		<div class="mb-3 mt-4 ps-2 element retrait">
			<p class=titre-retrait>
				<span style="font-weight: bold;">Retrait 
			</p>
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
			<p>
				<span style="font-weight: bold;">Vendeur :</span> ${ articleVendu.utilisateur.pseudo }
			</p>
		</div>

		<c:if test="${utilisateurInscrit != null}">
			<form action="DetailVenteServlet" method="post">
				<div class="container">
					<div class="row">
						<div class="d-flex justify-content-center text-align-center">
							<div class="col-sm-6 d-flex justify-content-center ">
								<label for="propositionPrix"
									class="form-label form-label-sm m-1 element">Ma
									proposition</label>
							</div>
							<div class="col-sm-6 ">
								<input type="number" step="1" min="0" id="propositionPrix"
									name="propositionPrix" class="form-control" required>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="col-sm-6">
							<button type="submit" class="btn btn-primary m-2" name="encherir">Enchérir</button>
						</div>
					</div>

				</div>
			</form>
		</c:if>
	</div>


</body>
</html>