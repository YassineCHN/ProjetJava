<%-- 
    Document   : AjouterActe
    Created on : 01 janv. 2025, 23:18:00
    Author     : votrenom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter Acte</title>
    </head>
    <body>
        <h1>Ajouter un nouvel acte</h1>
        <form action="NewServlet" method="post">
            <label for="acteNom">Nom de l'acte :</label>
            <input type="text" id="acteNom" name="acteNom" required><br><br>
            
            <label for="acteDescription">Description de l'acte :</label>
            <input type="text" id="acteDescription" name="acteDescription" required><br><br>

            <label for="actePrix">Prix de l'acte :</label>
            <input type="number" step="0.01" id="actePrix" name="actePrix" required><br><br>

            <input type="hidden" name="action" value="creerActe">
            <input type="submit" value="Créer">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
