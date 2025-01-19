<%-- 
    Document   : EspacePersonnel
    Created on : 12 janv. 2025, 22:10:37
    Author     : ychen
--%>

<%@page import="ENTITE.RoleUSER"%>
<%@page import="ENTITE.Z_MEDECIN"%>
<%@page import="ENTITE.Z_PERSONNEL"%>
<%@page import="ENTITE.Z_PATIENT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ENTITE.Z_PERSONNE"%>
<!DOCTYPE html>
<%@ include file="navbar.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Espace Personnel</title>
</head>
<body>
    <h1>Mes informations personnelles</h1>
<%
    Z_PERSONNE personne = (Z_PERSONNE) request.getAttribute("personne");
    RoleUSER role = (RoleUSER) request.getAttribute("role");
%>  

    <form action="NewServlet" method="post">
        <fieldset>
            <legend>Informations personnelles</legend>
            
            <input type="hidden" id="id_personne" name="id_personne" value="<%= personne.getIdpers()%>">
            
            <!-- Nom -->
            <label for="nom">Nom :</label>
            <input type="text" id="nomPersonne" name="nomPersonne" value="<%= personne.getNomPersonne() %>" required>
            <br><br>

            <!-- Prénom -->
            <label for="prenom">Prénom :</label>
            <input type="text" id="prenomPersonne" name="prenomPersonne" value="<%= personne.getPrenomPersonne() %>" required>
            <br><br>

            <!-- Adresse -->
            <label for="adresse">Adresse :</label>
            <input type="text" id="adressePersonne" name="adressePersonne" value="<%= personne.getAdressePersonne() %>" required>
            <br><br>
            
            <input type="hidden" id="typePersonne" name="typePersonne" value="<%= personne.getTYPE()%>">
            
            
            
            <!-- Champs spécifiques pour les patients -->
            <% if (role == RoleUSER.PATIENT) { %>
                <label for="numSecuPatient">Numéro de sécurité sociale :</label>
                <input type="text" id="numSecuPatient" name="numSecuPatient" 
                       value="<%= ((Z_PATIENT) personne).getNumSecuSoc()%>">
                <br><br>
                
                <label for="nomMutuelle">Nom de la mutuelle :</label>
                <input type="text" id="nomMutuelle" name="nomMutuelle" value="<%=((Z_PATIENT)personne).getNomMutuelle()%>">
                <br><br>

                <label for="adresseMutuelle">Adresse de la mutuelle :</label>
                <input type="text" id="adresseMutuelle" name="adresseMutuelle" value="<%= ((Z_PATIENT)personne).getAdresseMutuelle() %>">
                <br><br>
            <% } else if (role == RoleUSER.PERSONNEL) { %>
                <label for="servicePersonnel">Service :</label>
                <input type="text" id="servicePersonnel" value="<%= ((Z_PERSONNEL)personne).getService().getServiceNom()%>" readonly>
                <input type="hidden" name="servicePersonnel" value="<%= ((Z_PERSONNEL)personne).getService().getId() %>">
                <br><br>
            <% } else if (role == RoleUSER.MEDECIN) { %>
                <label for="specialite">Spécialité :</label>
                <input type="text" id="specialiteMedecin" name="specialiteMedecin" value="<%= ((Z_MEDECIN)personne).getSpecialite()%>" readonly>
                <br><br>
                
                <label for="serviceMedecin">Service :</label>
                <input type="text" id="serviceMedecin" value="<%= ((Z_MEDECIN)personne).getService().getServiceNom() %>" readonly>
                <input type="hidden" name="serviceMedecin" value="<%= ((Z_MEDECIN)personne).getService().getId() %>">

                <br><br>
            <% } else { %>
                <p>Rôle non reconnu. Veuillez contacter l'administrateur.</p>
                <% } %>

            <input type="hidden" name="action" value="modifierPersonne">
        </fieldset>

        <input type="submit" value="Mettre à jour mes informations">
    </form>
</body>
</html>
