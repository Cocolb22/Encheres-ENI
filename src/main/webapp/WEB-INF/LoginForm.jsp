<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="fr.eni.encheres.bundles.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page de Connexion</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginForm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/couleurs.css">
    
</head>
<body>

<jsp:include page="Header.jsp"/>

<div class="texte background-content">
    <p>Doudou, tu viens plus aux soirées ?</p>
    <p>On a fait une soirée privatisation de la poste,</p>
    <p>Je peux te dire que j'ai reçu des gros colis,</p>
    <p> y a même des gens qui ont payé en liquide.</p>
</div>

<div class="container d-flex justify-content-end align-items-center vh-100  ">
    <div class="col-md-6 transparent-card  ">
        <h2 class="mb-3">Connexion</h2>

            <c:if test="${listeCodesErreur != null}">
            	<div class="alert alert-warning alert-dismissible fade show" role="alert">
            		<strong>Attention!</strong> 
                    <c:forEach items="${listeCodesErreur}" var="codeErreur">
                        <p>${LecteurMessage.getMessageErreur(codeErreur)}</p>
                    </c:forEach>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button> 
                </div> 
            </c:if>


            <form action="LoginServlet" method="post">
                <div class="mb-3">
                    <label for="pseudo" class="form-label">Pseudo :</label> <input
                        type="text" id="pseudo" name="pseudo" class="form-control"
                        required>
                </div>
                <div class="mb-3">
                    <label for="motDePasse" class="form-label">Mot de passe: </label> <input
                        type="password" id="motDePasse" name="motDePasse"
                        class="form-control" required>
                </div>
                <div class="d-flex justify-content-center align-items-center">
                    <button type="submit" class="btn btn-primary">Connexion</button>
                </div>
            </form>

            <div class="d-flex justify-content-center align-items-center p-3">
            <div class="form-check">
                <label class="form-check-label">
		                <input type="checkbox" class="form-check-input" name="rememberMe"> Se souvenir de moi
		            </label>
            </div>
            <div class="ms-3">
                <a href="MotDePasseOublie.jsp" class="link">Mot de passe oublié?</a>
            </div>
        </div>
        <div class="d-flex justify-content-center align-items-center text-align-center p-3">
            <p>Vous n'avez pas de compte?</p>
            <a href="HomePageServlet?action=inscription" class="btn btn-secondary m-4">S'inscrire</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous" defer></script>

</body>
</html>
