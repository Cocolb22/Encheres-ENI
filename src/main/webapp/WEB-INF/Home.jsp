<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous" defer></script>
<title>Home Page</title>

<script>
function updateCheckboxes(column) {
    var checkboxes = document.querySelectorAll("." + column + " input[type='checkbox']");
    var oppositeColumn = column === "achat" ? "vente" : "achat";

    checkboxes.forEach(function(checkbox) {
        checkbox.disabled = false;
    });

    var selectedCheckboxes = document.querySelectorAll("." + column + " input[type='checkbox']:checked");

    if (selectedCheckboxes.length > 0) {
        var oppositeCheckboxes = document.querySelectorAll("." + oppositeColumn + " input[type='checkbox']");
        oppositeCheckboxes.forEach(function(checkbox) {
            checkbox.disabled = true;
        });
    } else {
        var oppositeCheckboxes = document.querySelectorAll("." + oppositeColumn + " input[type='checkbox']");
        oppositeCheckboxes.forEach(function(checkbox) {
            checkbox.disabled = false;
        });
    }
}
</script>


</head>
<body>

<jsp:include page="Header.jsp"/>

<div style="text-align: center; padding-top: 20px"><h1>Liste des enchères</h1> </div>

<div class="filters mt-5">
    <form action="HomePageServlet" method="get" style="display: flex">
        <div style="margin: 10px">
            <label for="recherche">Rechercher :</label>
            <input type="text" id="recherche" name="recherche" placeholder="Entrez votre recherche">
        </div>
        
        <div class="categories">
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

<c:if test="${utilisateurInscrit != null}">
	 <div class="container transparent-card d-flex flex-column align-items-center justify-content-center text-align-center">
    <div class=" btn-group-verticla " role="group" aria-label="Basic checkbox toggle button group">
    
	        <div class="col achat grid"> 
	            <h3>Achat</h3>
	            <label class="btn checkbox-label"><input type="checkbox" onchange="updateCheckboxes('achat')">Enchères ouvertes</label>
	            <label class="btn checkbox-label"><input type="checkbox" onchange="updateCheckboxes('achat')">En cours</label>
	            <label class="btn checkbox-label"><input type="checkbox" onchange="updateCheckboxes('achat')">Remportées</label>
	        </div>
	        <div class="col vente grid"> 
	            <h3>Vente</h3>
	            <label class="btn checkbox-label"><input type="checkbox" onchange="updateCheckboxes('vente')">En cours</label>
	            <label class="btn checkbox-label"><input type="checkbox" onchange="updateCheckboxes('vente')">Débutées</label>
	            <label class="btn checkbox-label"><input type="checkbox" onchange="updateCheckboxes('vente')">Terminées</label>
	        </div>
    </div>
    
</div>
 </c:if>


${model.lstEnchere}

<!-- <div class="card">
    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0">
        
        <div class="col-md-4">
                Utilisez la classe d'image réactive pour limiter la largeur de l'image
                <img src="https://m.media-amazon.com/images/I/61SRHAZd7ML._AC_UF1000,1000_QL80_.jpg" alt="tenue de François" class="img-fluid">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title"><a href=""> </a></h5>
                    <p class="card-text">Prix : </p>
                    <p class="card-text"><small class="text-body-secondary">Fin de l'enchère: </small></p>
                    <br />
                    <p class="card-text"><small class="text-body-secondary">Vendeur: </small></p>
                </div>
            </div>
      
        </div>
    </div>
</div> -->
</div>

</body>
</html>


