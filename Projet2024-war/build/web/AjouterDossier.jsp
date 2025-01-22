<%@page import="ENTITE.Service"%>
<%@page import="ENTITE.Z_PATIENT"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <!--listeServicesAjoutDossier-->
        <% List<Z_PATIENT> listPatient = (List<Z_PATIENT>) request.getAttribute("listePatientsAjoutDossier"); %>
        <% List<Service> listService = (List<Service>) request.getAttribute("listeServicesAjoutDossier"); %>
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
                var patientIdField = document.getElementById("patientId");
                var nomField = document.getElementById("nomPersonne");
                var prenomField = document.getElementById("prenomPersonne");
                var numSecuField = document.getElementById("numSecuPatient");
                
                if (document.getElementById("newPatientCheckbox").checked) {
                    existingPatientField.classList.add("hidden");
                    newPatientField.classList.remove("hidden");
                    nomField.setAttribute("required", "required");
                    prenomField.setAttribute("required", "required");
                    numSecuField.setAttribute("required", "required");
                    patientIdField.removeAttribute("required");
                } else {
                    existingPatientField.classList.remove("hidden");
                    newPatientField.classList.add("hidden");
                    nomField.removeAttribute("required");
                    prenomField.removeAttribute("required");
                    numSecuField.removeAttribute("required");
                    patientIdField.setAttribute("required", "required");
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
                
            </fieldset>

            <fieldset>
                <legend>Informations du Patient</legend>

                <input type="checkbox" id="newPatientCheckbox" name="newPatientCheckbox" onclick="togglePatientFields()">
                <label for="newPatientCheckbox">Créer un nouveau patient</label><br><br>

                <div id="existingPatientField">
                    <label for="patientId">ID du patient :</label>
                    
                    <!--                    <input type="text" id="patientId" name="patientId"><br><br>-->
                    <select id="patientId" name="patientId" required>
                        <option value="" disabled selected>ID - Nom patient</option>
                        A l'avenir faudra récupérer le nom patient
                        <%for (Z_PATIENT cp : listPatient) {%>
                        <option value="<%= cp.getIdpers()%>"><p><%=cp.getIdpers() %> - <%=cp.getNomPersonne() %><%=" "%><%=cp.getPrenomPersonne() %></p></option>

                    

                    <%}%>
                    </select>
                </div>

                <div id="newPatientField" class="hidden">
                    <label for="Nom">Nom :</label>
                    <input type="text" id="nomPersonne" name="nomPersonne" ><br><br>
                    
                    <label for="Prenom">Prénom :</label>
                    <input type="text" id="prenomPersonne" name="prenomPersonne" ><br><br>
                    
                    <label for="Adresse">Adresse :</label>
                    <input type="text" id="adressePersonne" name="adressePersonne"><br><br>
                    
                    <label for="numSecuPatient">Numéro de sécurité sociale :</label>
                    <input type="text" id="numSecuPatient" name="numSecuPatient" ><br><br>
                    
                    <label for="NomMutuelle">Mutuelle :</label>
                    <input type="text" id="nomMutuelle" name="nomMutuelle"><br><br>
                    
                    <label for="AdresseMutuelle">Adresse Mutuelle :</label>
                    <input type="text" id="adresseMutuelle" name="adresseMutuelle"><br><br>
                </div>
            </fieldset>

                    <fieldset>
                        <legend>Informations du Service</legend>
                        <label for="serviceId">ID du service :</label>

                        <!--                
                                        <input type="text" id="serviceId" name="serviceId" required><br><br>-->

                        <select id="serviceId" name="serviceId" required>
                            <option value="" disabled selected>ID - Nom du service</option>
                            <%for (Service cp : listService) {%>
                            <option value="<%= cp.getId()%>"><p><%=cp.getId()%> — <%=cp.getServiceNom()%></p></option>

                       
                        <%}%>
                         </select>
                    </fieldset>

            <input type="hidden" name="action" value="creerDossierMedical">
            <input type="submit" value="Créer">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
