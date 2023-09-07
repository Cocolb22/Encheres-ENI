<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.time.LocalDate" %>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<jsp:include page="Header.jsp" />

<audio id="myAudio" autoplay>
    <source src="${pageContext.request.contextPath}/song/SF-fouet3.mp3" type="audio/mpeg">
</audio>

<div class="container">
    <h1 class="display-1 text-center mt-5">Liste des enchères</h1>

    <div class="transparent-card p-4 mt-4">
        <h3>Filtres :</h3>
        <form action="HomePageServlet" method="post">
            <c:if test="${utilisateurInscrit != null}">
                <div class="row">
                    <div class="col-md-6">
                        <h4>Achat</h4>
                        <div class="form-check achat">
                            <input class="form-check-input" type="checkbox" name="achatEnchereOuverte"
                                   value="true" <c:if test="${not empty param.achatEnchereOuverte}">checked="checked"</c:if>
                                   onchange="updateCheckboxes()">
                            <label class="form-check-label">Enchères ouvertes</label>
                        </div>
                        <div class="form-check achat">
                            <input class="form-check-input" name="achatEnchereEnCours" type="checkbox" value="true"
                                <c:if test="${not empty param.achatEnchereEnCours}">checked="checked"</c:if>
                                onchange="updateCheckboxes()">
                            <label class="form-check-label">Mes enchères en cours</label>
                        </div>
                        <div class="form-check achat">
                            <input class="form-check-input" name="achatEnchereRemportées" type="checkbox" value="true"
                                <c:if test="${not empty param.achatEnchereRemportées}">checked="checked"</c:if>
                                onchange="updateCheckboxes()">
                            <label class="form-check-label">Mes enchères remportées</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h4>Vente</h4>
                        <div class="form-check vente">
                            <input class="form-check-input" type="checkbox" name="venteEnchereEnCours"
                                   value="true" <c:if test="${not empty param.venteEnchereEnCours}">checked="checked"</c:if>
                                   onchange="updateCheckboxes()">
                            <label class="form-check-label">Mes ventes en cours</label>
                        </div>
                        <div class="form-check vente">
                            <input class="form-check-input" name="venteEnchereDebutes" type="checkbox" value="true"
                                <c:if test="${not empty param.venteEnchereDebutes}">checked="checked"</c:if>
                                onchange="updateCheckboxes()">
                            <label class="form-check-label"> Ventes non débutées</label>
                        </div>
                        <div class="form-check vente">
                            <input class="form-check-input" name="VenteEnchereTermines" type="checkbox" value="true"
                                <c:if test="${not empty param.VenteEnchereTermines}">checked="checked"</c:if>
                                onchange="updateCheckboxes()">
                            <label class="form-check-label">Ventes terminées</label>
                        </div>
                    </div>
                </div>
              </c:if>
                <div class="row mt-3">
                    <div class="col-md-6">
                        <label for="recherche"><h4>Rechercher :</h4></label>
                        <input type="text" id="recherche" name="nomArticle" placeholder="Entrez votre recherche"
                               class="form-control">
                    </div>
                    <div class="col-md-6">
                        <label for="categorie"><h4>Catégorie :</h4></label>
                        <select id="categorie" name="categorie" class="form-select">
                            <option value="0">Aucune</option>
                            <c:forEach items="${categorie}" var="lstCategorie">
								<c:if test="${categorieSelectionnee == lstCategorie.noCategorie }">
									<option value="${lstCategorie.noCategorie }" selected>${lstCategorie.libelle }</option>
								</c:if>
								<c:if test="${categorieSelectionnee != lstCategorie.noCategorie }">
									<option value="${lstCategorie.noCategorie }">${lstCategorie.libelle }</option>
								</c:if>
							</c:forEach>
                        </select>
                    </div>
                </div>
                <div class="text-center mt-3">
                    <button type="submit" class="btn btn-primary" name="BT_SELECT_CATEGORIE" value="recherche">Rechercher</button>
                </div>
            
        </form>
    </div>

    <div class="row mt-4">
        <c:forEach items="${modelEnchere.lstEnchere}" var="enchere">
            <div class="enchere col-md-4 mb-4">
            	<c:if test="${enchere.articleVendu.dateFinEncheres.isBefore(LocalDate.now())}">
	    			<a href="EnchereRemporteServlet?noArticle=${enchere.articleVendu.noArticle }" class="a-title">
		    	</c:if> 
		    	<c:if test="${enchere.articleVendu.dateFinEncheres.isAfter(LocalDate.now())}"> 
		    		<a href="DetailVenteServlet?noArticle=${enchere.articleVendu.noArticle}" class="a-title">
			    </c:if> 
                <div class="card">
                    <img src="${pageContext.request.contextPath}/images/fouet.jpg" class="card-img-top p-3" alt="Image de l'enchère">
                    <div class="card-body">
                        <h5 class="card-title">${enchere.articleVendu.nomArticle}</h5>
                        <p class="card-text">Prix : ${enchere.montantEnchere} points</p>
                        <p class="card-text">
                            <small class="text-muted">Fin de l'enchère: ${enchere.articleVendu.dateFinEncheresFormatted}</small>
                        </p>
                        <p class="card-text">
                            <small class="text-muted">Vendeur: ${enchere.articleVendu.utilisateur.pseudo}</small>
                        </p>
                    </div>
                </div>
                </a>
                
            </div>
        </c:forEach>
    </div>
</div>

<script>
    function updateCheckboxes() {
    	var checkboxesAchat = document.querySelectorAll(".achat input[type='checkbox']");
        var checkboxesVente = document.querySelectorAll(".vente input[type='checkbox']");

        checkboxesAchat.forEach(function(checkboxAchat) {
            checkboxAchat.addEventListener("change", function() {
                if (checkboxAchat.checked) {
                    checkboxesVente.forEach(function(checkboxVente) {
                        checkboxVente.checked = false;
                    });
                }
            });
        });

        checkboxesVente.forEach(function(checkboxVente) {
            checkboxVente.addEventListener("change", function() {
                if (checkboxVente.checked) {
                    checkboxesAchat.forEach(function(checkboxAchat) {
                        checkboxAchat.checked = false;
                    });
                }
            });
        });
    }

    document.addEventListener("DOMContentLoaded", function() {
        var audio = document.getElementById("myAudio");

        audio.addEventListener("canplay", function() {
            audio.play();
        });
        
        updateCheckboxes();
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous" defer></script>

</body>
</html>