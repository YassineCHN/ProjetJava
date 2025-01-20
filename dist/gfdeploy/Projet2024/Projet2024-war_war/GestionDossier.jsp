<%-- 
    Document   : GestionUtilisateur
    Created on : 20 nov. 2024, 15:17:06
    Author     : charl
--%>

   
<%@page import="ENTITE.Acte"%>
<%@page import="ENTITE.LigneJournal"%>
<%@page import="ENTITE.JournalActe"%>
<%@page import="ENTITE.DossierHospitalisation"%>
<%@page import="ENTITE.Z_MEDECIN"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% List<DossierHospitalisation> list = (List<DossierHospitalisation>) request.getAttribute("listeDossiers"); %>
        
        <title>Utilisateur</title>
    </head>
    <body>
        <div class="main_content">
        <h1>Gestion des dossiers médicaux</h1>
        
        <div class="ruban_actions">
            <a href="NewServlet?action=ajouterDossierForm" class="button_link">Créer un dossier</a>
        </div>
    
      


    <TABLE border width=50%>
        <tr>
            <TD>ID</TD>
            <TD>Statut du dossier</TD>
            <TD>Date d'hospitalisation</TD>
            <TD>Date d'arrivée</TD>
            <TD>Date de départ</TD>
            <td>Patient</td>
            <td>Service</td>
            <td>Acte(s)</td>
        </tr>
        <% if (list != null || !list.isEmpty()) { %>
        <% for (DossierHospitalisation cp : list) { %>
            <tr>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheDossier&id_dossier=<%= cp.getId() %>"><%= cp.getId() %></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheDossier&id_dossier=<%= cp.getId() %>"><%= cp.getStatutD() %></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheDossier&id_dossier=<%= cp.getId() %>"><%= cp.getDateHospitalisation()%></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheDossier&id_dossier=<%= cp.getId() %>"><%= cp.getHeureArrivee()%></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheDossier&id_dossier=<%= cp.getId() %>"><%= cp.getHeureDepart()%></a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFichePersonne&id_personne=<%= cp.getLePatient().getIdpers() %>"><%= cp.getLePatient().getIdpers()%>-<%= cp.getLePatient().getNomPersonne()%><%=" "%><%=cp.getLePatient().getPrenomPersonne() %> </a>
                </td>
                <td Width=15%>
                    <a href="NewServlet?action=afficherFicheService&id_service=<%= cp.getLeService()%>"><%= cp.getLeService().getId()%>-<%= cp.getLeService().getServiceNom()%></a>
                </td>
                <td Width=25%>
                    <%
                        List<JournalActe> journalActes = cp.getJournalActes();
                        if (journalActes != null && !journalActes.isEmpty()) {
                            StringBuilder actes = new StringBuilder();
                            for (JournalActe journal : journalActes) {
                                List<LigneJournal> lignes = journal.getLigneJournals();
                                for (LigneJournal ligne : lignes) {
                                    Acte acte = ligne.getId_acte();
                                    if (acte != null) {
                                        actes.append(acte.getActeNom()).append(", "); // Affiche le nom de l'acte
                                    }
                                }
                            }
                            if (actes.length() > 0) {
                                actes.setLength(actes.length() - 2); // Enlève la dernière virgule et l'espace
                            }
                            out.print(actes.toString());
                        } else {
                            out.print("Aucun acte");
                        }
                    %>
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
