<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="java.util.List"%>
<%@page import="ENTITE.Utilisateur"%>
<%@page import="ENTITE.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<Service> list = (List<Service>) request.getAttribute("listeService"); %>
        <title>Utilisateur</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Afficher Utilisateurs</h1>
        
        <div class="ruban_actions">
            <a href="#" class="button_link">Ajouter un utilisateur</a>
        </div>
    
        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Localisation</TD>
                <TD>Nom</TD>
                
            </tr>
            <% for (Service cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheService&id_service=<%= cp.getId() %>"><%= cp.getId() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheService&id_service=<%= cp.getId() %>"><%= cp.getServiceLocalisation()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFicheService&id_service=<%= cp.getId() %>"><%= cp.getServiceNom()%></a>
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
