<%-- 
    Document   : newjsp
    Created on : 17 nov. 2024, 17:19:29
    Author     : charl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire d'authentification</title>
    
</head>

<body>

    <form action="maServlet" method="get">
        <fieldset>
        <label for="login">Login :</label>
        <input type="text" id="login" name="login" required>
        <br><br>
        <label for="password">Mot de passe :</label>
        <input type="text" id="password" name="password" required>
        
        
        <br><br>
        <label for="test1">test :</label>
        <input type="text" id="test1" name="test1" required>
        
        
        <br><br>
        <button type="submit">Se connecter</button>
        <input type="hidden" name="action" value="authentificationUtilisateur">
        
        </fieldset>
    </form>

</body>

</html>
