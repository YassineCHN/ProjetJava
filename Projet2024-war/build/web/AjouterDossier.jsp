<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Créer Dossier Médical</title>
        <style>
            .hidden {
                display: none;
            }
        </style>
        <script>
            function togglePatientFields() {
                var existingPatientField = document.getElementById("existingPatientField");
                var newPatientField = document.getElementById("newPatientField");
                if (document.getElementById("newPatientCheckbox").checked) {
                    existingPatientField.classList.add("hidden");
                    newPatientField.classList.remove("hidden");
                } else {
                    existingPatientField.classList.remove("hidden");
                    newPatientField.classList.add("hidden");
                }
            }
        </script>
    </head>
    <body>
        <h1>Créer un nouveau dossier médical</h1>
        <form action="NewServlet" method="post">
            <fieldset>
                <legend>Informations du Dossier Médical</legend>

                <label for="dateHospitalisation">Date d'hospitalisation :</label>
                <input type="datetime-local" id="dateHospitalisation" name="dateHospitalisation" required><br><br>
                
                <label for="heureArrivee">Heure d'arrivée :</label>
                <input type="datetime-local" id="heureArrivee" name="heureArrivee" required><br><br>

                <label for="heureDepart">Heure de départ :</label>
                <input type="datetime-local" id="heureDepart" name="heureDepart" required><br><br>
            </fieldset>

            <fieldset>
                <legend>Informations du Patient</legend>

                <input type="checkbox" id="newPatientCheckbox" name="newPatientCheckbox" onclick="togglePatientFields()">
                <label for="newPatientCheckbox">Créer un nouveau patient</label><br><br>

                <div id="existingPatientField">
                    <label for="patientId">ID du patient :</label>
                    <input type="text" id="patientId" name="patientId"><br><br>
                </div>

                <div id="newPatientField" class="hidden">
                    <label for="loginPatient">Login :</label>
                    <input type="text" autocomplete="off"  id="loginPatient" name="loginPatient"><br><br>
                    
                    <!--
                    Attention au comportement de base du navigateur
                    Par défaut, l'autocompletion est activé pour le champ password et le champ text qui le précède dans le fieldset
                    en changeant l'input pour text on désactive ça
                    sinon, autocomplete="new-password"
                    
                    -->
                    <label for="mdpPatient">Mot de passe :</label>
                    <input type="test" autocomplete="off"  id="mdpPatient" name="mdpPatient"><br><br>

                    <label for="numSecuPatient">Numéro de sécurité sociale :</label>
                    <input type="text" id="numSecuPatient"  autocomplete="off" name="numSecuPatient"><br><br>
                </div>
            </fieldset>

            <fieldset>
                <legend>Informations du Service</legend>

                <label for="serviceId">ID du service :</label>
                <input type="text" id="serviceId" name="serviceId" required><br><br>
            </fieldset>

            <input type="hidden" name="action" value="creerDossierMedical">
            <input type="submit" value="Créer">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
