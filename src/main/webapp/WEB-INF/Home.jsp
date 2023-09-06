<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous" defer></script>
<title>Home Page</title>

<script>
	function updateCheckboxes(column) {
		var checkboxes = document.querySelectorAll("." + column
				+ " input[type='checkbox']");
		var oppositeColumn = column === "achat" ? "vente" : "achat";

		checkboxes.forEach(function(checkbox) {
			checkbox.disabled = false;
		});

		var selectedCheckboxes = document.querySelectorAll("." + column
				+ " input[type='checkbox']:checked");

		if (selectedCheckboxes.length > 0) {
			var oppositeCheckboxes = document.querySelectorAll("."
					+ oppositeColumn + " input[type='checkbox']");
			oppositeCheckboxes.forEach(function(checkbox) {
				checkbox.disabled = true;
			});
		} else {
			var oppositeCheckboxes = document.querySelectorAll("."
					+ oppositeColumn + " input[type='checkbox']");
			oppositeCheckboxes.forEach(function(checkbox) {
				checkbox.disabled = false;
			});
		}
	}

	document.addEventListener("DOMContentLoaded", function() {
		var audio = document.getElementById("myAudio");

		audio.addEventListener("canplay", function() {
			audio.play();
		});
	});
</script>


</head>
<body>

	<jsp:include page="Header.jsp" />

	<audio id="myAudio" autoplay>
		<source src="${pageContext.request.contextPath}/song/SF-fouet3.mp3"
			type="audio/mpeg">
	</audio>

	<div style="text-align: center; padding-top: 20px">
		<h1>Liste des enchères</h1>
	</div>

<c:if test="${utilisateurInscrit != null}">

		<div
			class="container transparent-card">
			<div class="row">
			
				<div class="btn-group-vertical d-flex flex-row" role="group"
					aria-label="Basic checkbox toggle button group">
					<div class="col-md-4 achat grid">
						<h3 class="ms-2">Achat</h3>
						<label class="btn  checkbox-label"><input
							name="achatEnchereOuverte" type="checkbox" value="true"
							onchange="updateCheckboxes('achat')"> Enchères ouvertes</label> <label
							class="btn  checkbox-label"><input
							name="achatEnchereEnCours" type="checkbox" value="true"
							onchange="updateCheckboxes('achat')"> Mes enchères en cours</label> <label
							class="btn  checkbox-label"><input
							name="achatEnchereRemportées" type="checkbox" value="true"
							onchange="updateCheckboxes('achat')"> Mes enchères remportées</label>
					</div>
					<div class="col-md-4 vente grid">
						<h3 class="ms-2">Vente</h3>
						<label class="btn  checkbox-label"><input
							name="venteEnchereEnCours" type="checkbox" value="true"
							onchange="updateCheckboxes('vente')">Mes ventes en cours</label> <label
							class="btn  checkbox-label"><input
							name="venteEnchereDebutes" type="checkbox" value="true"
							onchange="updateCheckboxes('vente')"> Ventes non débutées</label> <label
							class="btn  checkbox-label"><input
							name="VenteEnchereTermines" type="checkbox" value="true"
							onchange="updateCheckboxes('vente')">Ventes terminées</label>
					</div>
					
					<form action="HomePageServlet" method="post">
						<div class=" col-md-4 d-flex flex-column">
							<div>
								<label for="recherche">Rechercher :</label> <input type="text" id="recherche" name="nomArticle" placeholder="Entrez votre recherche">
							</div>
					
							<div class="categories">
								<label for="categorie">Catégorie :</label> 
									<select id="categorie" name="categorie">
										<c:forEach items="${categorie}" var="lstCategorie">
											<option name="categorie" value="${lstCategorie.noCategorie }">${lstCategorie.libelle }</option>
										</c:forEach>
									</select>
							</div>
					
							<div class="d-flex justify-content-center">
								<button type="submit" class="submit" name="BT_SELECT_CATEGORIE" value="recherche" style="margin: 10px">Rechercher</button> 
							</div>
						</div>	
					</form>
					
				</div>
			</div>
		</div>
	</c:if>


	<div class="row">
    <c:forEach items="${modelEnchere.lstEnchere}" var="enchere">
        <div class="col-md-4 mb-3 d-flex justify-content-center align-items-center">
            <div class="card ">
                <div class="row g-0">
                    <div class=" col-md-4 mt-2 mb-2 ">
                        	<img src="${pageContext.request.contextPath}/images/fouet.jpg" class="img-fluid rounded-start" alt="Description of the image">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title"><a href="DetailVenteServlet?noArticle=${enchere.articleVendu.noArticle}" class="a-title">${enchere.articleVendu.nomArticle}</a></h5>
                            <p class="card-text">Prix : ${enchere.montantEnchere} points</p>
                            <p class="card-text"><small class="text-muted">Fin de l'enchère: ${enchere.articleVendu.dateFinEncheresFormatted}</small></p>
                            <p class="card-text"><small class="text-muted">Vendeur: ${enchere.articleVendu.utilisateur.pseudo}</small></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
	
</body>
</html>