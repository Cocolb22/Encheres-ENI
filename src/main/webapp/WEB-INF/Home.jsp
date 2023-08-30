<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous" defer></script>
<title>Home Page</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div class="container p-4">

<div ><h1>Liste des enchères</h1> </div>

<div class="filters mt-5">
    <form action="HomePageServlet" method="get" style="display: flex">
        <div style="margin: 10px">
            <label for="recherche">Rechercher :</label>
            <input type="text" id="recherche" name="recherche" placeholder="Entrez votre recherche">
        </div>
        
        <div class="categories"  style="margin: 10px">
            <label for="categorie">Catégorie :</label>
            <select id="categorie" name="categorie">
                <option value="categorie1">Informatique</option>
                <option value="categorie2">Vetement</option>
                <option value="categorie3">Ameublement</option>
                <option value="categorie4">Sport et loisirs</option>
            </select>
        </div>
        
        <div>
            <button type="submit" class="submit"  style="margin: 10px">Rechercher</button>
        </div>
    </form>
</div>


<div class="card">
    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0">
            <div class="col-md-4">
                <!-- Utilisez la classe d'image réactive pour limiter la largeur de l'image -->
                <img src="https://m.media-amazon.com/images/I/61SRHAZd7ML._AC_UF1000,1000_QL80_.jpg" alt="tenue de François" class="img-fluid">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>
