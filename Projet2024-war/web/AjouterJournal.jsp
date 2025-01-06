<%-- 
    Document   : AjouterPatient
    Created on : 26 déc. 2024, 21:55:46
    Author     : charl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>

<%String id_dossier = (String) session.getAttribute("id_dossier");%>
<!DOCTYPE html>

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Créer un journal d'actes</title>
        </head>
        
      
    <body>
        
        <h1>Créer le journal</h1>
        
        <form action="NewServlet" method="post">
            <label for="loginPatient">Login :</label>
            <input type="text" id="loginPatient" name="loginPatient" required><br><br>
            
            <label for="mdpPatient">Mot de passe :</label>
            <input type="password" id="mdpPatient" name="mdpPatient" required><br><br>

            <label for="numSecuPatient">Numéro de sécurité sociale :</label>
            <input type="text" id="numSecuPatient" name="numSecuPatient" required><br><br>

            <input type="hidden" name="action" value="creerJournal">
            <input type="submit" value="Créer">
        </form>
    </body>
    <%@ include file="footer.jsp" %>

