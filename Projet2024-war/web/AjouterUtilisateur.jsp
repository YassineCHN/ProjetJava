<%-- 
    Document   : AjouterUtilisateur
    Created on : 26 déc. 2024, 21:55:46
    Author     : charl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter utilisateur</title>
        <style>
        .hidden {
            display: none;
            /*Noter que display none "supprime" la balise de l'affichage'
            elle existe quand même dans le code*/
        }
    </style>
    <script>
//        petite fonction java qui permet de modifier la propriété CSS
//        de l'élément 
//        chatGPT/Copilot
//        je la désactive parce que je n'arrive pas à faire systématiquement fonction javascript
//        function afficherChampSupplementaire() {
//            var roleSelect = document.getElementById("role");
//            var adminField = document.getElementById("adminField");
//            var medecinField = document.getElementById("medecinField");
//
//            adminField.classList.add("hidden");
//            medecinField.classList.add("hidden");
//
//            if (roleSelect.value === "ADMIN") {
//                adminField.classList.remove("hidden");
//            } else if (roleSelect.value === "MEDECIN") {
//                medecinField.classList.remove("hidden");
//            }
//        }
    </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="NewServlet" method="post">
        <label for="loginAjouterUser">Login :</label>
        <input type="text" id="loginAjouterUser" name="loginAjouterUser" required><br><br>
        
        <label for="passwordAjouterUser">Mot de passe :</label>
        <input type="passwordAjouterUser" id="passwordAjouterUser" name="passwordAjouterUser" required><br><br>

        <label for="roleAjouterUser">Rôle :</label>
        <select id="roleAjouterUser" name="roleAjouterUser" onchange="afficherChampSupplementaire()" required>
            <option value="">Sélectionner un rôle</option>
            <option value="ADMIN">ADMIN</option>
            <option value="MEDECIN">MEDECIN</option>
            <option value="PATIENT">PATIENT</option>
        </select><br><br>

        <div id="adminField" class="">
<!--            hidden si on veut utiliser javascript-->
            <label for="adminStatusAjouterUser">ADMIN_STATUS :</label>
            <input type="text" id="adminStatusAjouterUser" name="adminStatusAjouterUser"><br><br>
        </div>

        <div id="medecinField" class="">
            <label for="specialiteAjouterUser">SPECIALITE :</label>
            <input type="text" id="specialiteAjouterUser" name="specialiteAjouterUser"><br><br>
        </div>

        <input type="hidden" name="action" value="creerUtilisateur">
        <input type="submit" value="Créer">
        
        
    </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
