<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>BLABLABLA</title>
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

            </h1>
            <hr>


            <div class="cards_page_content">
                <% if (utilisateurIdentifie0 != null) { %>
                <!-- Cas pour l'utilisateur ADMIN : voir toutes les cartes de gestion -->
                <% if ("ADMIN".equals(role_utilisateur)) { %>
                <%@ include file="card_GestionPatient.jsp" %>
                <%@ include file="card_GestionActe.jsp" %>
                <%@ include file="card_GestionMedecin.jsp" %>
                <%@ include file="card_GestionService.jsp" %>
                <%@ include file="card_GestionUtilisateur.jsp" %>
                <%@ include file="card_EspacePatient.jsp" %>
                <% } else if ("MEDECIN".equals(role_utilisateur)) { %>
                <!-- Cas pour l'utilisateur MEDECIN : voir certaines cartes spécifiques -->
                <%@ include file="card_GestionActe.jsp" %>
                <%@ include file="card_GestionMedecin.jsp" %>
                <%@ include file="card_GestionService.jsp" %>
                <%@ include file="card_GestionPatient.jsp" %>
                <% } else if ("PATIENT".equals(role_utilisateur)) { %>
                <!-- Cas pour l'utilisateur PATIENT : voir uniquement la carte Espace Patient -->
                <%@ include file="card_EspacePatient.jsp" %>
                <% } %>
                <% }%>
            </div>

        </div>

        <%@ include file="footer.jsp" %>

    </body>
</html>
