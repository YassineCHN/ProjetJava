<%-- 
    Document   : AjouterPersonne
    Created on : 12 janv. 2025, 10:26:53
    Author     : ychen
--%>


<%@page import="java.util.List"%>
<%@page import="ENTITE.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>

<% 
    String action = (String) request.getAttribute("action");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter personne</title>
        <jsp:useBean id="listeServices" scope="request" class="java.util.List"></jsp:useBean>
        <%
            List<Service> lesServ = listeServices;
        %>
    </head>
   <body>
        <h1>Ajouter une nouvelle personne </h1>
    <form action="NewServlet" method="post">

        <label for="Nom">Nom :</label>
        <input type="text" id="nomPersonne" name="nomPersonne" required><br><br>

        <label for="Prenom">Prénom :</label>
        <input type="text" id="prenomPersonne" name="prenomPersonne" required><br><br>
        
        <label for="Adresse">Adresse :</label>
        <input type="text" id="adressePersonne" name="adressePersonne"><br><br>
        
 <!-- Choix du type de la personne -->
            <label for="typePersonne">Type :</label>
            <select name="typePersonne" id="typePersonne" onchange="updateFormFields()" required>
                <option value="">Sélectionnez un type</option>
                <option value="MEDECIN">Médecin</option>
                <option value="PATIENT">Patient</option>
                <option value="PERSONNEL">Personnel</option>
            </select><br><br>

        
        <div id="patientFields" style="display:none;">    
            <label for="numSecuPatient">Numéro de sécurité sociale :</label>
            <input type="text" id="numSecuPatient" name="numSecuPatient" required><br><br>

            <label for="NomMutuelle">Mutuelle :</label>
            <input type="text" id="nomMutuelle" name="nomMutuelle"><br><br>
            
            <label for="AdresseMutuelle">Adresse Mutuelle :</label>
            <input type="text" id="adresseMutuelle" name="adresseMutuelle"><br><br>
        </div>    
            
        <div id="medecinFields" style="display:none;">
            <label for="specialiteMedecin">Spécialité :</label>
            <input type="text" id="specialiteMedecin" name="specialiteMedecin" required>
            
            <label for="MedecinServiceAjouterPersonne">Services : </label> 
                <select name="MedecinServiceAjouterPersonne"> 
                    <option value="">Aucun</option>
                    <% for (Service s :lesServ) {%> 
                    <option value ="<%=s.getId()%>"><%=s.getServiceNom()%></option> 
                    <% }%> 
                </select><br><br>
        </div>

        <div id="personnelFields" style="display:none;"> 
        <label for="PersonnelServiceAjouterPersonne">Services : </label> 
                <select name="PersonnelServiceAjouterPersonne"> 
                    <option value="">Aucun</option>
                    <% for (Service s :lesServ) {%> 
                    <option value ="<%=s.getId()%>"><%=s.getServiceNom()%></option> 
                    <% }%> 
                </select><br><br>
        </div>
 
            
        

        <input type="hidden" name="action" value="<%= action != null ? action : "creerPersonne" %>">
        <input type="submit" value="Créer">
    </form>
    
    <script>
            // Fonction JavaScript pour afficher/masquer les champs en fonction du type sélectionné
            function updateFormFields() {
                var typePersonne = document.getElementById("typePersonne").value;
                document.getElementById("medecinFields").style.display = (typePersonne == "MEDECIN") ? "block" : "none";
                document.getElementById("patientFields").style.display = (typePersonne == "PATIENT") ? "block" : "none";
                document.getElementById("personnelFields").style.display = (typePersonne == "PERSONNEL") ? "block" : "none";
                
                var specialiteField = document.getElementById("specialiteMedecin");
                if (typePersonne == "MEDECIN") {
                    specialiteField.setAttribute("required", "true");
                } 
                else {specialiteField.removeAttribute("required");                }
                
                var numSecuField = document.getElementById("numSecuPatient");
                if (typePersonne == "PATIENT") {
                    numSecuField.setAttribute("required", "true");
                } 
                else {numSecuField.removeAttribute("required");}
            }
    </script>
    </body>
    <%@ include file="footer.jsp" %>
</html>
    