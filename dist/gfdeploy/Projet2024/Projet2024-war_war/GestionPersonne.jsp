<%-- 
    Document   : GestionPersonne
    Created on : 12 janv. 2025, 10:33:08
    Author     : ychen
--%>
<%@page import="ENTITE.Z_PERSONNE"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<Z_PERSONNE> list = (List<Z_PERSONNE>) request.getAttribute("listePersonnes"); %>
        <title>Personnes</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Afficher Personnes</h1>
        
        <div class="ruban_actions">
            <form action="NewServlet" method="get" class="formulairebouton">
                <input type="hidden" name="action" value="ajouterPersonne">
                <input type="submit" value="Ajouter une personne" class="button_link">
            </form>
        </div>

        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Nom</TD>
                <TD>Prenom</TD>
                <TD>Type</TD>
            </tr>
            <% for (Z_PERSONNE cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers()%>"><%= cp.getIdpers() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers() %>"><%= cp.getNomPersonne() %></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers() %>"><%= cp.getPrenomPersonne()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers() %>"><%= cp.getTYPE()%></a>
                    </td>
                </tr>
            <% } %>
        </TABLE> 
    <td Width=25%><A HREF="landing_page.jsp" class="button_link"> Retour
            Menu</A></td>
            <br>
            <br>
            
    <hr>
        </div>
    </body>
    <%@ include file="footer.jsp" %>
</html>