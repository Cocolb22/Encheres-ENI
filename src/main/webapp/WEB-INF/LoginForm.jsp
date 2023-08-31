<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.bundles.LecteurMessage" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginForm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/couleurs.css">
    
    <title>Page de Connexion</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

	<div class="container d-flex justify-content-center align-items-center vh-100">
	    <div class="col-md-6 transparent-card">
	        <h2 class="mb-3">Connexion</h2>
	        
	        <c:if test="${listeCodesErreur != null}">
          <c:forEach items="${listeCodesErreur}" var="codeErreur">
            <p>${LecteurMessage.getMessageErreur(codeErreur)} </p>
          </c:forEach>
	      </c:if>
		    
	        <form action="LoginServlet" method="post">
	            <div class="mb-3">
	                <label for="pseudo" class="form-label">Pseudo</label>
	                <input type="text" id="pseudo" name="pseudo" class="form-control" required>
	            </div>
	            <div class="mb-3">
	                <label for="motDePasse" class="form-label">Mot de passe</label>
	                <input type="password" id="motDePasse" name="motDePasse" class="form-control" required>
	            </div>
	            <div class="d-flex justify-content-center align-items-center">
	             	<button type="submit" class="btn btn-primary d-flex justify-content-center align-items-center">Connexion</button>
	            </div>
	        </form>
	        
	        <div class="d-flex justify-content-center align-items-center p-3">
		        <div class="m-3">
		            <label class="form-check-label">
		                <input type="checkbox" class="form-check-input"> Se souvenir de moi
		            </label>
		        </div>
		        <div class="m-3">
		            <a href="MotDePasseOublie.jsp" class="link">Mot de passe oublié?</a>
		        </div>
	        </div>
	         <div class="d-flex justify-content-center align-items-center text-align-center p-3">
		        <div class="m-3">
		            <p>Vous n'avez pas de compte?</p>
		           <a href="HomePageServlet?action=inscription" class="btn btn-secondary d-flex justify-content-center align-items-center">S'inscrire</a>
		        </div>
	        </div>
	    </div>
	</div>
</body>
</html>
