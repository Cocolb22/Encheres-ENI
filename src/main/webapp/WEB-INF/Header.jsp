<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdeli
.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous" defer></script>

</head>
<body>
<c:if test="${user == null}">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Eighth navbar example">
    <div class="container">
      <a href="${pageContext.request.contextPath}/HomePageServlet" class="navbar-brand">Eni Enchères</a>
      <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="navbar-collapse collapse justify-content-end" id="navbarsExample07">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="HomePageServlet?action=inscription">S'inscrire</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="HomePageServlet?action=login">Se connecter</a>
        </li>      
    </ul>
</div>
    </div>
  </nav>
 </c:if>
<c:if test="${user != null}">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Eighth navbar example">
    <div class="container">
      <a href="${pageContext.request.contextPath}/HomePageServlet" class="navbar-brand">Eni Enchères</a>
      <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="navbar-collapse collapse justify-content-end" id="navbarsExample07">
    	<ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" >Encheres</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" aria-current="page" >Vendre un article</a>
        </li>
         <li class="nav-item">
            <a class="nav-link active" aria-current="page" >Mon profil</a>
        </li>
         <li class="nav-item">
            <a class="nav-link active" aria-current="page">Deconnexion</a>
        </li> 
    </ul>
</div>
    </div>
  </nav>
 </c:if>
</body>
</html>