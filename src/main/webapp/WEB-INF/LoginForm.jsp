<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Page de Connexion</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="col-md-6">
        <h2 class="mb-3">Connexion</h2>
        <form action="ConnexionServlet" method="post">
            <div class="mb-3">
                <label for="pseudo" class="form-label">Pseudo</label>
                <input type="text" id="pseudo" name="pseudo" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="motDePasse" class="form-label">Mot de passe</label>
                <input type="password" id="motDePasse" name="motDePasse" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Connexion</button>
        </form>
        
        <div class="mt-3">
            <label class="form-check-label">
                <input type="checkbox" class="form-check-input"> Se souvenir de moi
            </label>
        </div>
        <div class="mt-2">
            <a href="MotDePasseOublie.jsp">Mot de passe oublié?</a>
        </div>
        <div class="mt-3">
            <p>Vous n'avez pas de compte?</p>
           <a href="UtilisateurServlet?action=inscription" class="btn btn-secondary">S'inscrire</a>
        </div>
    </div>
</div>

</body>
</html>
