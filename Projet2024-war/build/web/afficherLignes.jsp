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
                
                
                <td><a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= e.getId_journal().getId()%>" > <%= e.getCommentaire() %></a></td>
                <td><a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= e.getId_journal().getId()%>" > <%= e.getDate_acte() %></a></td>
                <td><a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= e.getId_journal().getId()%>" > <%= e.getId() %></a></td>
                <td><a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= e.getId_journal().getId()%>" > <%= e.getId_acte().getActeNom() %></a></td>
                <td><a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= e.getId_journal().getId()%>" > <%= e.getId_journal().getId()%></a></td>
            </tr>
            <% } %>
        </tbody>
    </table>
        
            <td Width=25%><A HREF="landing_page.jsp" class="button_link"> Retour
            Menu</A></td>
            <br>
            <br>
        </div>
</body>
</html>
