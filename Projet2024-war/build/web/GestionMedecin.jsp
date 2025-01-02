<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="ENTITE.Z_MEDECIN"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<Z_MEDECIN> list = (List<Z_MEDECIN>) request.getAttribute("listeUtilisateurMedecins"); %>
        <title>Utilisateur</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Afficher Utilisateurs</h1>
        
        <div class="ruban_actions">
            <a href="AjouterMedecin.jsp" class="button_link">Ajouter un utilisateur</a>
        </div>
    
        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Login</TD>
                <TD>Mot de passe</TD>
                <TD>Role</TD>
                <td>Specialite</td>
            </tr>
            <% for (Z_MEDECIN cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getId() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getLogin()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getMdp()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getRole()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getSpecialite()%>"><%= cp.getRole()%></a>
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
