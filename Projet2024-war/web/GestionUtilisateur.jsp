<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="ENTITE.Z_USER"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<Z_USER> list = (List<Z_USER>) request.getAttribute("listeUtilisateur"); %>
        <title>Utilisateur</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Afficher Utilisateurs</h1>
        
        <div class="ruban_actions">
            <form action="NewServlet" method="get" class="formulairebouton">
                <input type="hidden" name="action" value="ajouterUtilisateur">
                <input type="submit" value="Ajouter un utilisateur" class="button_link">
            </form>
        </div>

        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Login</TD>
                <TD>Mot de passe</TD>
                <TD>Role</TD>
                <TD>ID Personne</TD>
            </tr>
            <% for (Z_USER cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateur&id_utilisateur=<%= cp.getId() %>"><%= cp.getId() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateur&id_utilisateur=<%= cp.getId() %>"><%= cp.getLogin()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFicheUtilisateur&id_utilisateur=<%= cp.getId() %>"><%= cp.getMdp()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFicheUtilisateur&id_utilisateur=<%= cp.getId() %>"><%= cp.getRole()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFicheUtilisateur&id_utilisateur=<%= cp.getId() %>"><%= (cp.getPersonne() != null) ? cp.getPersonne().getIdpers() : "Aucune Personne" %>
    </a>
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
