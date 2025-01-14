<%-- 
    Document   : AjouterPatient
    Created on : 26 déc. 2024, 21:55:46
    Author     : charl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter patient</title>
    </head>
    <body>
        <h1>Ajouter un nouveau patient</h1>
        <form action="NewServlet" method="post">
            <label for="Nom">Nom :</label>
            <input type="text" id="nomPatient" name="nomPatient" required><br><br>
            
            <label for="Prenom">Prénom :</label>
            <input type="text" id="prenomPatient" name="prenomPatient" required><br><br>

            <label for="AdressePatient">Adresse :</label>
            <input type="text" id="adressePatient" name="adressePatient"><br><br>
            
            <label for="numSecuPatient">Numéro de sécurité sociale :</label>
            <input type="text" id="numSecuPatient" name="numSecuPatient" required><br><br>

            <label for="NomMutuelle">Mutuelle :</label>
            <input type="text" id="nomMutuelle" name="nomMutuelle" ><br><br>
            
            <label for="AdresseMutuelle">Adresse Mutuelle :</label>
            <input type="text" id="adresseMutuelle" name="adresseMutuelle" ><br><br>
            
            <input type="hidden" name="action" value="creerPatient">
            <input type="submit" value="Créer">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
