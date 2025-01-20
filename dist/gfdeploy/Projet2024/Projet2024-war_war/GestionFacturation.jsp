<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="ENTITE.Facture"%>
<%@page import="ENTITE.Acte"%>
<%@page import="ENTITE.Z_USER"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Toutes les factures -->
        <% List<Facture> list = (List<Facture>) request.getAttribute("listeFacture"); %>
        <!-- Les factures affichées -->
       
        
        
        
        
        
        <title>Actes</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Factures</h1>
        
        <div class="ruban_actions">
            <a href="" class="button_link">Ajouter une facture</a>
        </div>
    
        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Date de création</TD>
                <TD>Montant total</TD>
                <TD>ID dossier</TD>
                <TD>ID journal</TD>
                <TD>Statut de la facture</TD>
            </tr>
            <% for (Facture cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheFacture&id_Facture=<%= cp.getId() %>"><%= cp.getId() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheFacture&id_Facture=<%= cp.getId() %>"><%= cp.getFactureDateEmissions()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFicheFacture&id_Facture=<%= cp.getId() %>"><%= cp.getFactureMontant()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFicheFacture&id_Facture=<%= cp.getId() %>"><%= cp.getLeDossier().getId()%></a>
                    </td>
                    <td Width=30%>
                        <a href="NewServlet?action=afficherFicheFacture&id_Facture=<%= cp.getId() %>"><%= cp.getLeJournal().getId()%></a>
                    </td>
     //                <td Width=30%>
                        <a href="NewServlet?action=afficherFicheFacture&id_Facture=<%= cp.getId() %>"><%= (cp.isFacturePayee()) ? "Payée" : "Impayée"%></a>
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
