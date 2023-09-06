<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginForm.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/couleurs.css">
<title></title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-mb-4">
			<div class="mb-3 ps-2 element">
				<p> ${ articleVendu.nomArticle }</p>
			</div>
			<div class="mb-3 ps-2 element">
				<p> ${ articleVendu.description }</p>
			</div>
			<div class="mb-3 ps-2 element">
				<p> ${ enchere.montantEnchere } points</p>
			</div>
			<div class="mb-3 ps-2 element">
				<p> ${ articleVendu.prixInitial } points</p>
			</div>
		
			<div class="mb-3 mt-4 ps-2 element retrait">
			<p class=titre-retrait><span style="font-weight: bold;">Retrait</span></p>
			<div class="p-3">
				<p>
					<span style="font-weight: bold;">Rue :</span>
					${articleVendu.pointRetrait.rue}
				</p>
				<p>
					<span style="font-weight: bold;">Code Postal :</span>
					${articleVendu.pointRetrait.codePostal}
				</p>
				<p>
					<span style="font-weight: bold;">Ville :</span>
					${articleVendu.pointRetrait.ville}
				</p>
			</div>
			<div class="mb-3 ps-2 element">
				<p><span style="font-weight: bold;">Vendeur</span> ${ articleVendu.utilisateur.pseudo } </p>
			</div>
			<div class="mb-3 ps-2 element">
				<p><span style="font-weight: bold;">Telephone</span> ${ articleVendu.utilisateur.telephone } </p>
			</div>
		</div>
		</div>
	
	</div>




</div>

</body>
</html>