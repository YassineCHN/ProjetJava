<%-- 
    Document   : AjouterService
    Created on : 26 déc. 2024, 21:55:46
    Author     : charl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter service</title>
    </head>
    <body>
        <h1>Ajouter un nouveau service</h1>
        <form action="NewServlet" method="post">
            <label for="nomService">Nom du service :</label>
            <input type="text" id="nomService" name="nomService" required><br><br>
            
            <label for="localisationService">Localisation :</label>
            <input type="text" id="localisationService" name="localisationService" required><br><br>

            <input type="hidden" name="action" value="creerService">
            <input type="submit" value="Créer">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
