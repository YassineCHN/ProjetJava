<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="ENTITE.DossierHospitalisation"%>
<%@page import="ENTITE.Z_MEDECIN"%>
<%@page import="java.util.List"%>
<%@page import="ENTITE.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<DossierHospitalisation> list = (List<DossierHospitalisation>) request.getAttribute("listeDossiers"); %>
        <% System.out.println("DANS LA JSP") ;%>
        <% System.out.println(list.get(0).getId()) ;%>
        <% System.out.println(list.get(0).getId()) ;%>
        <% System.out.println(list.get(0).getId()) ;%>
        <title>Utilisateur</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Afficher dossier</h1>
        
        <div class="ruban_actions">
            <a href="AjouterDossier.jsp" class="button_link">Créer un dossier</a>
        </div>
    
        <TABLE border width=50%>
            <tr>
                <TD>ID</TD>
                <TD>Date d'hospitalisation</TD>
                <TD>Date d'arrivée</TD>
                <TD>Date de départ</TD>
                <td>ID du patient</td>
                <td>ID du service</td>
                <td>Nom du service</td>
                
            </tr>
            <% for (DossierHospitalisation cp : list) { %>
                <tr>
                    <!-- Dans chaque ligne du tableau, on récupère les informations du patient qu'on encapsule dans une balise <a> -->
                    <!-- Cette balise redirige vers la jsp "fichePatient.jsp" avec dans l'URL l'id du patient -->
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getId() %></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getDateHospitalisation()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getHeureArrivee()%></a>
                    </td>
                    <td Width=15%>
                        <a href="NewServlet?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getId() %>"><%= cp.getHeureDepart()%></a>
                    </td>
                    <td Width=15%>
                        <!--TROUVER LE MOYEN DE REDIRIGER VERS FICHE PATIENT/SERVICE-->
                        <!--TROUVER LE MOYEN DE REDIRIGER VERS FICHE PATIENT/SERVICE-->
                        <!--TROUVER LE MOYEN DE REDIRIGER VERS FICHE PATIENT/SERVICE-->
                        <!--TROUVER LE MOYEN DE REDIRIGER VERS FICHE PATIENT/SERVICE-->
                        <!--TROUVER LE MOYEN DE REDIRIGER VERS FICHE PATIENT/SERVICE-->
                        
                        <a href="/fiche?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getLePatient().getId()%>"><%= cp.getLePatient().getLogin()%></a>
                    </td>
                    <td Width=15%>
                        
                        
                        <a href="/fiche?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getLePatient().getId()%>"><%= cp.getLeService().getId()%></a>
                    </td>
                    <td Width=15%>
                        
                        
                        <a href="/fiche?action=afficherFicheUtilisateurMedecin&id_utilisateur=<%= cp.getLePatient().getId()%>"><%= cp.getLeService().getServiceNom()%></a>
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
