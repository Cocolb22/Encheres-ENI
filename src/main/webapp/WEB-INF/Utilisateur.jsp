<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.bundles.LecteurMessage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/utilisateurProfil.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/couleurs.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous" defer></script>
    
<title>Mon profil</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div class="container transparent-card">

	<h1> Bonjour ${utilisateurInscrit.pseudo }</h1>
  
  <c:if test="${listeCodesErreur != null}">
	    	<c:forEach items="${listeCodesErreur}" var="codeErreur">
	    		<p>${LecteurMessage.getMessageErreur(codeErreur)} </p>
	    	</c:forEach>
	    </c:if>
  
 	<form action="InscriptionServlet" method="post">
        <div class="row">
            <div class="col-md-6 p-3">
                <!-- Champs de gauche -->
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" class="form-control" id="nom" name="nom" value=" ${utilisateurInscrit.nom}" required>
                </div>
                <div class="form-group">
                    <label for="prenom">Prénom :</label>
                    <input type="text" class="form-control" id="prenom" name="prenom" value=" ${utilisateurInscrit.prenom}" required>
                </div>
                <div class="form-group">
                    <label for="pseudo">Pseudo :</label>
                    <input type="text" class="form-control" id="pseudo" name="pseudo" value=" ${utilisateurInscrit.pseudo}" required>
                </div>
                <div class="form-group">
                    <label for="email">Email :</label>
                    <input type="email" class="form-control" id="email" name="email" value=" ${utilisateurInscrit.email}" required>
                </div>
                <div class="form-group">
                    <label for="telephone">Téléphone :</label>
                    <input type="tel" class="form-control" id="telephone" name="telephone" value=" ${utilisateurInscrit.telephone}">
                </div>
            </div>
            <div class="col-md-6 p-3">
                <!-- Champs de droite -->
                <div class="form-group">
                    <label for="rue">Rue :</label>
                    <input type="text" class="form-control" id="rue" name="rue" value=" ${utilisateurInscrit.rue}" required>
                </div>
                <div class="form-group">
                    <label for="codePostal">Code Postal :</label>
                    <input type="text" class="form-control" id="codePostal" name="codePostal" value=" ${utilisateurInscrit.codePostal}" required>
                </div>
                <div class="form-group">
                    <label for="ville">Ville :</label>
                    <input type="text" class="form-control" id="ville" name="ville" value=" ${utilisateurInscrit.ville}" required>
                </div>
                <div class="form-group">
                    <label for="motDePasse">Mot de passe :</label>
                    <input type="password" class="form-control" id="motDePasse" name="motDePasse" value=" ${utilisateurInscrit.motDePasse}" required>
                </div>
                 <div class="form-group">
	                    <label for="confirmationMotDePasse">Confirmation du mot de passe :</label>
	                    <input type="password" class="form-control" id="confirmationMotDePasse" name="confirmationMotDePasse" required>
	                </div>
            </div>
        </div>
			<div class="row d-flex">
			     <div class="col-md-6 p-2 d-flex justify-content-end">
			     <button type="submit" class="btn btn-primary m-3 pd-2" name="action" value="update">Modifier</button>
			     </div>
			     <div class="col-md-6 p-2 d-flex justify-content-start">
			     <button type="submit" class="btn btn-primary m-3 pd-2" name="action" value="delete">Supprimer</button>
			     </div>
			</div>
    </form>
</div>
</body>
</html>