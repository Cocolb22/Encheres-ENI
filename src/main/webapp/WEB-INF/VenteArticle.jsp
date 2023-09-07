<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/couleurs.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/venteArticle.css">
 
<title>Vente article servlet</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<h1 class="m-3 d-flex justify-content-center text-align-center">Vendre un article</h1>

    <div class="container pb-5" style="max-width: 750px;" >
        
        <div class="container pt-3 p-5">    
            
            <form action="VenteArticleServlet" method="post">
                
                <div class="form-body">
                     <div class="mb-2">
                            <label for="article" class="form-label form-label-sm">Article :</label>
                            <input type="text" id="article" name="nomArticle" class="form-control" required>
                        </div>
                        <div class="mb-2">
                            <label for="description" class="form-label form-label-sm">Description :</label>
                            <input type="text" id="description" name="description" class="form-control" required>
                        </div>
                        <div class="categories mb-2 ">
                            <label for="categorie">Catégorie :</label>
                            <select id="categorie" name="categorie" class="rounded p-1">
                                <c:forEach items="${categorie}" var="lstCategorie"> 
                            <option name="categorie" value="${lstCategorie.noCategorie }">${lstCategorie.libelle }</option>
                          </c:forEach>
                            </select>
                        </div>
        
                         <div class="mb-2">
                            <label for="image" class="form-label form-label-sm">Image :</label>
                                <input type="file" id="image" name="image" class="form-control" accept="image/*" required>
                        </div>
                         <div class="mb-2">
                            <label for="miseAPrix" class="form-label form-label-sm">Mise à prix :</label>
                           <input type="number" step="10" min="0" id="miseAPrix" name="miseAPrix" class="form-control" required>
                        </div>
                         <div class="mb-2">
                            <label for="dateDebut" class="form-label form-label-sm">Date de début de l'enchère :</label>
                            <input type="date" id="dateDebut" name="dateDebut" class="form-control" required value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>"></div>
                        <div class="mb-2">
                            <label for="dateFin" class="form-label form-label-sm">Date de fin de l'enchère :</label>
                            <input type="date" id="dateFin" name="dateFin" class="form-control" required value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                        </div>
                    </div>    
                    <div class="retrait">
                        <div class="mb-2">
                        <h5>Point de retrait:</h5>
                            <label for="rue" class="form-label form-label-sm">Rue :</label>
                            <input type="text" id="rue" name="rue" class="form-control" value="${utilisateurInscrit.rue }" required>
                        </div>
                        <div class="mb-2">
                            <label for="codePostal" class="form-label form-label-sm">Code Postal :</label>
                            <input type="text" id="codePostal" name="codePostal" class="form-control" value="${utilisateurInscrit.codePostal }" required>
                        </div>
                        <div class="mb-2">
                            <label for="ville" class="form-label form-label-sm">Ville :</label>
                            <input type="text" id="ville" name="ville" class="form-control" value="${utilisateurInscrit.ville }" required>
                        </div>
                    </div>
                    <div class="btn-list">
                        <div class=" d-inline-flex">
                             <div class="col-md-6 p-2 d-flex justify-content-end">
                                <button type="submit" class="btn btn-primary m-3 pd-2" name="action" value="enregistrer">Enregistrer</button>
                             </div>
                             <div class="col-md-6 p-2 d-flex justify-content-start">
                                <button type="submit" class="btn btn-primary m-3 pd-2" name="action" value="annuler">Annuler</button>
                             </div>
                        </div>
                    </div>
            </form>
        </div>
    </div>
</body>
</html>