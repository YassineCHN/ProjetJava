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
        <% List<Z_MEDECIN> list = (List<Z_MEDECIN>) request.getAttribute("listeMedecins"); %>
        <title>Utilisateur</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Afficher Medecins</h1>
        
    
        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Nom</TD>
                <TD>Prenom</TD>
                <TD>Adresse Mail</TD>
                <td>Specialite</td>
                <TD>Nom Service</TD>
                <TD>Localisation Service</TD>
            </tr>
            <% for (Z_MEDECIN cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers()%>"><%= cp.getIdpers() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers() %>"><%= cp.getNomPersonne() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers() %>"><%= cp.getPrenomPersonne()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers() %>"><%= cp.getAdressePersonne()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers()%>"><%= cp.getSpecialite()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers()%>"><%= cp.getService().getId()%>-<%= cp.getService().getServiceNom() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getIdpers()%>"><%= cp.getService().getServiceLocalisation()%></a>
                    </td>
                </tr>
            <% } %>
        </TABLE> 
    <td Width=25%><A HREF="NewServlet?action=afficherPersonnes" class="button_link"> Retour
            Menu</A></td>
            <br>
            <br>
            
    <hr>
        </div>
    </body>
    <%@ include file="footer.jsp" %>
</html>
