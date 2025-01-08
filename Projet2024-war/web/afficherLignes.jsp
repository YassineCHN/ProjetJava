<%-- 
    Document   : afficherLignes
    Created on : 7 janv. 2025, 00:32:56
    Author     : charl
--%>

<%@page import="java.util.List"%>
<%@page import="ENTITE.LigneJournal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%List<LigneJournal> lignes22 = (List<LigneJournal>) request.getAttribute("lignes");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
       <%for(LigneJournal e : lignes22){%>
       <p><%= e.getCommentaire()%></p>
       <p><%= e.getDate_acte()%></p>
       <p><%= e.getId()%></p>
       <p><%= e.getId_acte().getActeNom()%></p>
       <p><%= e.getId_journal().getId()%></p>

       
       
       <%}%>
    </body>
</html>
