<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="ENTITE.Acte"%>
<%@page import="ENTITE.Z_USER"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<Acte> list = (List<Acte>) request.getAttribute("listeActe"); %>
        <title>Actes</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Actes médicaux</h1>
        
        <div class="ruban_actions">
            <a href="AjouterActe.jsp" class="button_link">Ajouter un acte médical</a>
        </div>
    
        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Acte</TD>
                <TD>Description</TD>
                <TD>Prix</TD>
                <TD>Remboursement de la sécurité sociale</TD>
                <TD>remboursement de la mutuelle</TD>
            </tr>
            <% for (Acte cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=10%>
                        <a href="NewServlet?action=afficherFicheActe&id_Acte=<%= cp.getId() %>"><%= cp.getId() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheActe&id_Acte=<%= cp.getId() %>"><%= cp.getActeNom()%></a>
                    </td>
                    <td Width=20%>
                        <a href="NewServlet?action=afficherFicheActe&id_Acte=<%= cp.getId() %>"><%= cp.getActeDescription()%></a>
                    </td>
                    <td Width=10%>
                        <a href="NewServlet?action=afficherFicheActe&id_Acte=<%= cp.getId() %>"><%= cp.getActePrix()%></a>
                    </td>
                    <td Width=25%>
                        <a href="NewServlet?action=afficherFicheActe&id_Acte=<%= cp.getId() %>"><%= cp.getCoefficient_SecuriteSociale()%></a>
                    </td>
                    <td Width=25%>
                        <a href="NewServlet?action=afficherFicheActe&id_Acte=<%= cp.getId() %>"><%= cp.getCoefficient_Mutuelle()%></a>
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
