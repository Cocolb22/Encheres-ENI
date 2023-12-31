<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inscriptionForm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/couleurs.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous" defer></script>
    <title>Formulaire d'inscription</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div class="container">
	<div class="transparent-card">
	    <h1 class="text-center">Formulaire d'inscription</h1>
	    <form>
	        <div class="row">
	            <div class="col-md-6">
	                <!-- Champs de gauche -->
	                <div class="form-group">
	                    <label for="nom">Nom :</label>
	                    <input type="text" class="form-control" id="nom" name="nom" required>
	                </div>
	                <div class="form-group">
	                    <label for="prenom">Pr�nom :</label>
	                    <input type="text" class="form-control" id="prenom" name="prenom" required>
	                </div>
	                <div class="form-group">
	                    <label for="pseudo">Pseudo :</label>
	                    <input type="text" class="form-control" id="pseudo" name="pseudo" required>
	                </div>
	                <div class="form-group">
	                    <label for="email">Email :</label>
	                    <input type="email" class="form-control" id="email" name="email" required>
	                </div>
	                <div class="form-group">
	                    <label for="telephone">T�l�phone :</label>
	                    <input type="tel" class="form-control" id="telephone" name="telephone" required>
	                </div>
	            </div>
	            <div class="col-md-6">
	                <!-- Champs de droite -->
	                <div class="form-group">
	                    <label for="rue">Rue :</label>
	                    <input type="text" class="form-control" id="rue" name="rue" required>
	                </div>
	                <div class="form-group">
	                    <label for="codePostal">Code Postal :</label>
	                    <input type="text" class="form-control" id="codePostal" name="codePostal" required>
	                </div>
	                <div class="form-group">
	                    <label for="ville">Ville :</label>
	                    <input type="text" class="form-control" id="ville" name="ville" required>
	                </div>
	                <div class="form-group">
	                    <label for="motDePasse">Mot de passe :</label>
	                    <input type="password" class="form-control" id="motDePasse" name="motDePasse" required>
	                </div>
	                <div class="form-group">
	                    <label for="confirmationMotDePasse">Confirmation du mot de passe :</label>
	                    <input type="password" class="form-control" id="confirmationMotDePasse" name="confirmationMotDePasse" required>
	                </div>
	            </div>
	        </div>
	        <div class="text-center">
	            <button type="submit" class="btn btn-primary m-2">S'inscrire</button>
	              <a href="${pageContext.request.contextPath}/UtilisateurServlet" class="btn btn-primary m-2">Annuler</a>

	        </div>
	    </form>
	</div>
</div>


</body>
</html>
