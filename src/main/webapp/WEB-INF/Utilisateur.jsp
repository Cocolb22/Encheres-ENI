<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.bundles.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Mon profil</title>
		
		<!-- Inclure Bootstrap CSS (version 5) -->
		<link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
			integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
			crossorigin="anonymous">
		
		<!-- Inclure vos fichiers CSS personnalisés -->
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/utilisateurProfil.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/couleurs.css">
		
		<!-- Inclure Bootstrap JS (version 5) et jQuery -->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
			integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
			crossorigin="anonymous" defer></script>
	</head>
	<body>
	
		<jsp:include page="Header.jsp" />
		<div
			class="d-flex flex-column justify-content-center align-items-center p-4">
	
	
			<h1 class="mt-3 text-center display-1">Votre profil</h1>
	
			<c:if test="${listeCodesErreur != null}">
				<div class="alert alert-warning" role="alert">
					<ul>
						<c:forEach items="${listeCodesErreur}" var="codeErreur">
							<li>${LecteurMessage.getMessageErreur(codeErreur)}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<div class="transparent-card w-10 row justify-content-center mt-5">
				<div
					class="d-flex flex-wrap wrap justify-content-between text-center p-4">
					<h2>Bonjour ${utilisateurInscrit.pseudo }</h2>
					<h2>${utilisateurInscrit.credit } crédits</h2>
				</div>
				<form action="UtilisateurServlet" method="post">
					<div class="row">
						<div class="col-md-6 p-3">
	
							<div class="mb-3">
								<label for="nom">Nom :</label> <input type="text"
									class="form-control" id="nom" name="nom"
									value="${utilisateurInscrit.nom}" required>
							</div>
							<div class="mb-3">
								<label for="prenom">Prénom :</label> <input type="text"
									class="form-control" id="prenom" name="prenom"
									value="${utilisateurInscrit.prenom}" required>
							</div>
							<div class="mb-3">
								<label for="pseudo">Pseudo :</label> <input type="text"
									class="form-control" id="pseudo" name="pseudo"
									value="${utilisateurInscrit.pseudo}" required>
							</div>
							<div class="mb-3">
								<label for="email">Email :</label> <input type="email"
									class="form-control" id="email" name="email"
									value="${utilisateurInscrit.email}" required>
							</div>
							<div class="mb-3">
								<label for="telephone">Téléphone :</label> <input type="tel"
									class="form-control" id="telephone" name="telephone"
									value="${utilisateurInscrit.telephone}">
							</div>
	
						</div>
						<div class="col-md-6 p-3">
	
							<div class="mb-3">
								<label for="rue">Rue :</label> <input type="text"
									class="form-control" id="rue" name="rue"
									value="${utilisateurInscrit.rue}" required>
							</div>
							<div class="mb-3">
								<label for="codePostal">Code Postal :</label> <input type="text"
									class="form-control" id="codePostal" name="codePostal"
									value="${utilisateurInscrit.codePostal}" required>
							</div>
							<div class="mb-3">
								<label for="ville">Ville :</label> <input type="text"
									class="form-control" id="ville" name="ville"
									value="${utilisateurInscrit.ville}" required>
							</div>
							<div class="mb-3">
								<label for="motDePasse">Mot de passe :</label> <input
									type="password" class="form-control" id="motDePasse"
									name="motDePasse" value="${utilisateurInscrit.motDePasse}"
									required>
							</div>
							<div class="mb-3">
								<label for="confirmationMotDePasse">Confirmation du mot
									de passe :</label> <input type="password" class="form-control"
									id="confirmationMotDePasse" name="confirmationMotDePasse"
									required>
							</div>
						</div>
					</div>
					<div class="d-inline-flex flex-wrap wrap justify-content-center">
						<div class="col-md-6 p-2 text-end">
							<button type="submit" class="btn btn-primary m-3">Modifier</button>
						</div>
						<div class="col-md-6 p-2 text-start">
							<button type="submit" class="btn btn-primary m-3">Supprimer</button>
						</div>
					</div>
				</form>
			</div>
	
		</div>
	
	</body>
</html>
