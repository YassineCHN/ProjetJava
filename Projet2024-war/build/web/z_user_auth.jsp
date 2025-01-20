<%-- 
    Document   : loginHeritage
    Created on : 18 nov. 2024, 12:55:37
    Author     : charl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="newcss.css          ">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            String message_affichage = (String) request.getAttribute("message");
        %>
    </head>
    <body>
        <h1>Authentification utilisateur</h1>
        <h1><%=(message_affichage==null) ? "" : message_affichage %></h1>
        <form action="NewServlet" method="post">
        <fieldset>
        <label for="loginHeritage">Login :</label>
        <input type="text" id="loginHeritage" name="loginHeritage" required>
        <br><br>
        <label for="passwordHeritage">Mot de passe :</label>
        <input type="text" id="passwordHeritage" name="passwordHeritage" required>

        <br><br>
    
        
        <input type="hidden" name="action" value="authentificationUtilisateur_HERITAGE">
        
        
        </fieldset>
            <input type="submit" value="Valider"  />
            <input type="reset" value="Remettre à zéro" /> <br />
    </form>
    </body>
</html>
