<%-- 
    Document   : login
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
    </head>
    <body>
        
        <h1>Hello World!</h1>
        <form action="NewServlet" method="post">
        <fieldset>
        <label for="login">Login :</label>
        <input type="text" id="login" name="login" required>
        <br><br>
        <label for="password">Mot de passe :</label>
        <input type="text" id="password" name="password" required>

        <br><br>
    
        
        <input type="hidden" name="action" value="authentificationUtilisateur">
        
        
        </fieldset>
            <input type="submit" value="Valider"  />
            <input type="reset" value="Remettre à zéro" /> <br />
    </form>
    </body>
</html>
