<%@ page import="ENTITE.Z_PATIENT" %>
<%@ page import="ENTITE.DossierHospitalisation" %>
<%@ page import="ENTITE.Facture" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="navbar.jsp" %>

<%
    // On suppose que vous avez placé dans la requête (ou la session) l?objet patient
    // et son dossier, sa facture, etc.
    // Par exemple :
    // request.setAttribute("patient", patientObj);
    // request.setAttribute("dossier", dossierHospitalisationObj);
    // request.setAttribute("facture", factureObj);

    Z_PATIENT patient = (Z_PATIENT) request.getAttribute("patient");
    DossierHospitalisation dossier = (DossierHospitalisation) request.getAttribute("dossier");
    Facture facture = (Facture) request.getAttribute("facture");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Espace Patient</title>
    <style>
        fieldset {
            margin: 20px 0;
            padding: 20px;
            border: 1px solid #444;
        }
        legend {
            font-weight: bold;
            padding: 0 10px;
        }
        .info-item {
            margin-bottom: 10px;
        }
        label {
            display: inline-block;
            width: 200px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Mon Espace Patient</h1>

    <!-- Bloc d'informations patient -->
    <h1>ATTENTION <br><br> LE DOSSIER ET LA FACTURE S'AFFICHE PAS SI PLUSIEURS DOSSIERS</h1>
    <fieldset>
        <legend>Informations du patient</legend>
        <% if (patient != null) { %>
            <div class="info-item">
                <label>ID Patient :</label>
                <span><%= patient.getId() %></span>
            </div>
            <div class="info-item">
                <label>Login :</label>
                <span><%= (patient.getLogin() != null) ? patient.getLogin() : "N/A" %></span>
            </div>
            <div class="info-item">
                <label>Numéro de Sécurité Sociale :</label>
                <span><%= (patient.getNumSecuSoc() != null) ? patient.getNumSecuSoc() : "N/A" %></span>
            </div>
        <% } else { %>
            <p>Aucune information de patient n?est disponible.</p>
        <% } %>
    </fieldset>

    <!-- Bloc d'informations sur le dossier d'hospitalisation -->
    <fieldset>
        <legend>Dossier d'hospitalisation</legend>
        <% if (dossier != null) { %>
            <div class="info-item">
                <label>ID Dossier :</label>
                <span><%= dossier.getId() %></span>
            </div>
            <div class="info-item">
                <label>Date d?hospitalisation :</label>
                <span>
                    <%= (dossier.getDateHospitalisation() != null) 
                         ? sdf.format(dossier.getDateHospitalisation())
                         : "N/A" %>
                </span>
            </div>
            <div class="info-item">
                <label>Heure d'arrivée :</label>
                <span>
                    <%= (dossier.getHeureArrivee() != null)
                         ? sdf.format(dossier.getHeureArrivee())
                         : "N/A" %>
                </span>
            </div>
            <div class="info-item">
                <label>Heure de départ :</label>
                <span>
                    <%= (dossier.getHeureDepart() != null)
                         ? sdf.format(dossier.getHeureDepart())
                         : "N/A" %>
                </span>
            </div>
            <div class="info-item">
                <label>Service :</label>
                <span>
                    <%= (dossier.getLeService() != null)
                         ? dossier.getLeService().getServiceNom()
                         : "N/A" %>
                </span>
            </div>
        <% } else { %>
            <p>Aucun dossier d?hospitalisation n?est disponible.</p>
        <% } %>
    </fieldset>

    <!-- Bloc facture -->
    <fieldset>
        <legend>Facture</legend>
        <% if (facture != null) { %>
            <div class="info-item">
                <label>ID Facture :</label>
                <span><%= facture.getId() %></span>
            </div>
            <div class="info-item">
                <label>Montant Total :</label>
                <span><%= facture.getFactureMontant() %></span>
            </div>
            <div class="info-item">
                <label>Date d?émission :</label>
                <span>
                    <%= (facture.getFactureDateEmissions() != null)
                         ? sdf.format(facture.getFactureDateEmissions())
                         : "N/A" %>
                </span>
            </div>
        <% } else { %>
            <p>Aucune facture disponible pour ce dossier.</p>
        <% } %>
    </fieldset>

    <%@ include file="footer.jsp" %>
</body>
</html>
