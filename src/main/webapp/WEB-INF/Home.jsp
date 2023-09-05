<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<div class="container transparent-card d-flex justify-content-center">
		<div class="p-2 ">
			<form action="HomePageServlet" method="post" >
			<div class=" d-flex flex-row">
				<div class="p-2">
					<label for="recherche"><h3>Rechercher :</h3></label> <input type="text"
						id="recherche" name="nomArticle"
						placeholder="Entrez votre recherche">
				</div>

				<div class="p-2">
					<label for="categorie"><h3>Catégorie :</h3></label> <select id="categorie"
						name="categorie">
						<c:forEach items="${categorie}" var="lstCategorie">
							<option name="categorie" value="${lstCategorie.noCategorie }">${lstCategorie.libelle }</option>
						</c:forEach>

					</select>
				</div>
			</div>
				<c:if test="${utilisateurInscrit != null}">

					<div class="btn-group-vertical  d-flex " role="group"
						aria-label="Basic checkbox toggle button group">
						<div class="col achat grid p-2">
							<h3>Achat :</h3>
							<label class="btn  checkbox-label"><input value="true"
								name="achatEnchereOuverte" type="checkbox"
								onchange="updateCheckboxes('achat')">Enchères ouvertes</label>
							<label class="btn  checkbox-label"><input value="true"
								name="achatEnchereEnCours" type="checkbox"
								onchange="updateCheckboxes('achat')">En cours</label>
							<label class="btn  checkbox-label"><input value="true"
								name="achatEnchereRemportées" type="checkbox"
								onchange="updateCheckboxes('achat')">Remportées</label>
						</div>
						<div class="col vente grid p-2">
							<h3>Vente :</h3>
							<label class="btn  checkbox-label"><input value="true"
								name="venteEnchereEnCours" type="checkbox"
								onchange="updateCheckboxes('vente')">En cours</label> <label
								class="btn  checkbox-label"><input value="true"
								name="venteEnchereDebutes" type="checkbox"
								onchange="updateCheckboxes('vente')">Débutées</label> <label
								class="btn  checkbox-label"><input value="true"
								name="VenteEnchereTermines" type="checkbox"
								onchange="updateCheckboxes('vente')">Terminées</label>
						</div>
					</div>
				</c:if>
		</div>
						<div class="d-flex flex-column justify-content-center p-4">
					<button type="submit" class="btn btn-primary btn-lg" name="BT_SELECT_CATEGORIE"
						value="recherche" style="margin: 10px">Rechercher</button>
				</div>
			</form>
	</div>



<div class="container d-flex flex_column">
	<div class="card">
    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0">
        <c:forEach items="${modelEnchere.lstEnchere}" var="enchere">        
	        <div class="col-md-4">
	            Utilisez la classe d'image réactive pour limiter la largeur de l'image
	            <img src="https://m.media-amazon.com/images/I/61SRHAZd7ML._AC_UF1000,1000_QL80_.jpg" alt="tenue de François" class="img-fluid">
	         </div>
	         <div class="col-md-8">
	            <div class="card-body">
	            	<%-- <c:forEach items="${enchere.articleVendu}" var="article">  --%>
	                    <h5 class="card-title"> <a href="DetailVenteServlet?noArticle=${enchere.articleVendu.noArticle}"> ${enchere.articleVendu.nomArticle}</a></h5>
	               	<%-- </c:forEach> --%>
	                    <p class="card-text">Prix : ${enchere.montantEnchere} points</p>
	                    <p class="card-text"><small class="text-body-secondary">Fin de l'enchère: ${enchere.articleVendu.dateFinEncheres}</small></p>
	                    <br />                    
	                    <p class="card-text"><small class="text-body-secondary">Vendeur: ${enchere.articleVendu.utilisateur.pseudo}</small></p>
	             </div>
	         </div>
      	</c:forEach>
        </div>
    </div>
</div> 
</div>

</body>
</html>
