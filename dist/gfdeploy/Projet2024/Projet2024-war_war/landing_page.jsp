<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Projet 2024</title>
        <link rel="stylesheet" href="newcss.css">
        <%
            String message_affichage = (String) request.getAttribute("message");
            
        %>
    </head>
    <body>
        <div class="container">
            <%@ include file="navbar.jsp" %>
        </div>

        <div class="main_content">
            <h2></h2>
            <hr>
            <h1>
                
                Projet 2024 - Cas hospitalisation. <br>
                <%=(message_affichage==null) ? "" : message_affichage %>

            </h1>
            <hr>
            

            <div class="cards_page_content">
                <% if (utilisateurIdentifie0 != null) { %>
                <!-- Cas pour l'utilisateur ADMIN : voir toutes les cartes de gestion -->
                <% if ("ADMIN".equals(role_utilisateur)) { %>
                <%@ include file="card_GestionActe.jsp" %>
                <%@ include file="card_GestionService.jsp" %>
                <%@ include file="card_GestionUtilisateur.jsp" %>
                <%@ include file="card_GestionPersonne.jsp" %>
                <%@ include file="card_GestionDossier.jsp" %>
                <%@ include file="card_GestionFacturation.jsp" %>
                <%@ include file="card_afficherLignes.jsp" %>
                <%@ include file="card_GestionJournal.jsp" %>
                
                <% } else if ("MEDECIN".equals(role_utilisateur)) { %>
                <!-- Cas pour l'utilisateur MEDECIN : voir certaines cartes spécifiques -->
                <%@ include file="card_GestionActe.jsp" %>
                <%@ include file="card_GestionDossier.jsp" %>
                <%@ include file="card_EspacePersonnel.jsp" %>
                <% } else if ("PATIENT".equals(role_utilisateur)) { %>
                <!-- Cas pour l'utilisateur PATIENT :  -->
                <%@ include file="card_EspacePersonnel.jsp" %>
                <%@ include file="card_GestionDossier.jsp" %>
                <%@ include file="card_GestionFacturation.jsp" %>
                <% } else if ("PERSONNEL".equals(role_utilisateur) && serviceFinancierTest!="ServiceFinancier") { %>
                <!-- Cas pour l'utilisateur PERSONNEL -->
                <%@ include file="card_EspacePersonnel.jsp" %>
                <%@ include file="card_GestionDossier.jsp" %>
                <% } else if ("PERSONNEL".equals(role_utilisateur) && serviceFinancierTest=="ServiceFinancier") { %>
                                <%@ include file="card_EspacePersonnel.jsp" %>
                <!-- Dans gestion facturation on peut aussi voir via un bouton les factures en retard -->
                <%@ include file="card_GestionFacturation.jsp" %>
                <%@ include file="card_GestionJournal.jsp" %>
                <% }}%>
            </div>

        </div>

        <%@ include file="footer.jsp" %>

    </body>
</html>
