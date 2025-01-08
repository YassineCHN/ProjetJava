<%@page import="java.util.List"%>
<%@page import="ENTITE.LigneJournal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%List<LigneJournal> lignes22 = (List<LigneJournal>) request.getAttribute("lignes");%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fiche Journal</title>
    
</head>
<%@ include file="navbar.jsp" %>
<body>
    <h1>Détails du Journal</h1>
    <div class="main_content">
    <table>
        <thead>
            <tr>
                <th>Commentaire</th>
                <th>Date de l'acte</th>
                <th>ID Ligne</th>
                <th>Nom de l'acte</th>
                <th>ID Journal</th>
            </tr>
        </thead>
        <tbody>
            <% for (LigneJournal e : lignes22) { %>
            <tr>
                <td><%= e.getCommentaire() %></td>
                <td><%= e.getDate_acte() %></td>
                <td><%= e.getId() %></td>
                <td><%= e.getId_acte().getActeNom() %></td>
                <td><%= e.getId_journal().getId() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
        </div>
</body>
</html>
