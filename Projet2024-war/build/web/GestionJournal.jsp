<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="ENTITE.JournalActe"%>
<%@page import="ENTITE.DossierHospitalisation"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<JournalActe> list = (List<JournalActe>) request.getAttribute("lesJournaux"); %>
        
        <title>Utilisateur</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Gestion des journaux d'actes</h1>
        
<!--        <div class="ruban_actions">
            <a href="NewServlet?action=ajouterDossierForm" class="button_link">Créer un Journal</a>
        </div>
    
      -->


    <TABLE border width=50%>
        <tr>
            <TD>ID</TD>
            <TD>Statut du journal</TD>
            <TD>Date de création</TD>
            <TD>Date de validation</TD>
            <TD>ID du dossier d'hospitalisation</TD>
            
        </tr>
        <% if (list != null || !list.isEmpty()) { %>
        <% for (JournalActe cp : list) { %>
            <tr>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= cp.getId() %>"><%= cp.getId() %></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= cp.getId() %>"><%= cp.getStatut()%></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= cp.getId() %>"><%= cp.getDateCreation()%></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= cp.getId() %>"><%= cp.getDateValidation()%></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheJournalActe&id_journal_1=<%= cp.getId() %>"><%= cp.getDossier().getId()%></a>
                </td>

            
            </tr>
        <% } %>
    </TABLE>
<% } else { %>
    </TABLE>
<% } %>

    <td Width=25%><A HREF="landing_page.jsp" class="button_link"> Retour
            Menu</A></td>
            <br>
            <br>
            
    <hr>
        </div>
    </body>
    <%@ include file="footer.jsp" %>
</html>
