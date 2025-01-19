<%-- 
    Document   : fichePersonne
    Created on : 12 janv. 2025, 13:44:31
    Author     : ychen
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ENTITE.Z_PERSONNE"%>
<%@page import="ENTITE.Z_PATIENT"%>
<%@page import="ENTITE.Z_MEDECIN"%>
<%@page import="ENTITE.Z_PERSONNEL"%>
<%@page import="ENTITE.Service"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fiche Personne</title>
</head>
<body>
    <% 
        Z_PERSONNE pers = (Z_PERSONNE) request.getAttribute("personneFichePersonne");
        List<Service> services = (List<Service>) request.getAttribute("listeServices");
        String typePersonne = "";
        if (pers instanceof Z_MEDECIN) {
            typePersonne = "MEDECIN";
        } else if (pers instanceof Z_PATIENT) {
            typePersonne = "PATIENT";
        } else if (pers instanceof Z_PERSONNEL) {
            typePersonne = "PERSONNEL";
        }
    %>

    <form action="NewServlet" method="post">
        <fieldset>
            <!-- Identifiant -->
            <label for="id_personne">ID personne :</label>
            <input type="text" id="id_personne" name="id_personne" value="<%=Long.toString(pers.getIdpers())%>" readonly>
            <br><br>

            <!-- Nom et prénom -->
            <label for="nomPersonne">Nom :</label>
            <input type="text" id="nomPersonne" name="nomPersonne" value="<%=pers.getNomPersonne() %>" required>
            <br><br>

            <label for="prenomPersonne">Prénom :</label>
            <input type="text" id="prenomPersonne" name="prenomPersonne" value="<%=pers.getPrenomPersonne() %>" required>
            <br><br>

            <!-- Adresse -->
            <label for="adressePersonne">Adresse :</label>
            <input type="text" id="adressePersonne" name="adressePersonne" value="<%=pers.getAdressePersonne() %>" required>
            <br><br>

            <!-- Type de personne -->
            <label for="typePersonne">Type de personne :</label>
            <input type="text" id="typePersonne" name="typePersonne" value="<%= typePersonne %>" readonly>
            <br><br>

            <!-- Champs spécifiques en fonction du type -->
            <% if (pers instanceof Z_MEDECIN) { %>
                <label for="specialiteMedecin">Spécialité :</label>
                <input type="text" id="specialiteMedecin" name="specialiteMedecin" 
                       value="<%= ((Z_MEDECIN) pers).getSpecialite()%>" required>
                <br><br>
                
                <label for="serviceMedecin">Service :</label>
                <select name="serviceMedecin" id="serviceMedecin" required>
                    <option value="">Sélectionnez un service</option>
                    <% for (Service s : services) {%>
                    <option value="<%= s.getId()%>" <%= ((Z_MEDECIN) pers).getService() != null && ((Z_MEDECIN) pers).getService().getId() == s.getId() ? "selected" : ""%>>
                        <%= s.getServiceNom()%>
                    </option>
                    <% } %>
                </select>
            <% } else if (pers instanceof Z_PATIENT) { %>
                <label for="numSecuPatient">Numéro de sécurité sociale :</label>
                <input type="text" id="numSecuPatient" name="numSecuPatient" 
                       value="<%= ((Z_PATIENT) pers).getNumSecuSoc()%>" required>
                <br><br>

                <label for="nomMutuelle">Mutuelle :</label>
                <input type="text" id="nomMutuelle" name="nomMutuelle" 
                       value="<%= ((Z_PATIENT) pers).getNomMutuelle() %>">
                <br><br>

                <label for="adresseMutuelle">Adresse Mutuelle :</label>
                <input type="text" id="adresseMutuelle" name="adresseMutuelle" 
                       value="<%= ((Z_PATIENT) pers).getAdresseMutuelle() %>">
                <br><br>
            <% } else if (pers instanceof Z_PERSONNEL) { %>
                <label for="servicePersonnel">Service :</label>
                <select name="servicePersonnel" id="servicePersonnel" required>
                    <option value="">Sélectionnez un service</option>
                    <% for (Service s : services) { %>
                        <option value="<%= s.getId() %>" <%= ((Z_PERSONNEL) pers).getService() != null && ((Z_PERSONNEL) pers).getService().getId() == s.getId() ? "selected" : "" %>>
                            <%= s.getServiceNom() %>
                        </option>
                    <% } %>
                </select>
                <br><br>
            <% } %>

            <input type="hidden" name="action" value="modifierPersonne">
            <input type="submit" value="Valider">
        </fieldset>
    </form>

    <!-- Formulaire de suppression -->
    <form action="NewServlet" method="post">
        <input type="hidden" name="supprimerPersonne" value="<%=Long.toString(pers.getIdpers())%>">
        <input type="hidden" name="action" value="supprimerPersonne">
        <input type="submit" value="Supprimer la personne">
    </form>

    <%@ include file="footer.jsp" %>
</body>
</html>
