<%@page import="ENTITE.Z_USER"%>
<%@page import="ENTITE.Utilisateur"%>
<!DOCTYPE html>
<%@ include file="navbar.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%Z_USER user = (Z_USER) request.getAttribute("utilisateurFicheUtilisateur");%>
    
    <form action="NewServlet" method="post">
    <fieldset>
        <label for="id_utilisateurFicheUtilisateur">ID utilisateur :</label>
        <input type="text" id="id_utilisateurFicheUtilisateur" name="id_utilisateurFicheUtilisateur" value="<%=Long.toString(user.getId())%>" disabled required>
        <br><br>
        <label for="login">Login :</label>
        <input type="text" id="login" name="login_ficheUtilisateur" value="<%=user.getLogin()%>" required>
        <br><br>
        <label for="password">Mot de passe :</label>
        <input type="text" id="password" name="password_ficheUtilisateur" value="<%=user.getMdp()%>" required>
        <br><br>
        <label for="role">Rôle de l'utilisateur :</label>
        <select id="role" name="role_ficheUtilisateur" required>
            
<!--            On utilise l'opérateur conditinnel/ternaire comme un if else
            On modifie/active l'attribut "selected" d'un des 3 tags "option"
            Ca permet de préselectionner la valeur de la liste déroulante.
            Si l'user change la valeur sur la page, l'attribut selected n'a pas d'impact et ne fige pas la liste
           
-->
            <option value="ADMIN" <%= "ADMIN".equals(user.getRole()) ? "selected" : "" %>>ADMIN</option>
            <option value="MEDECIN" <%= "MEDECIN".equals(user.getRole()) ? "selected" : "" %>>MEDECIN</option>
            <option value="PATIENT" <%= "PATIENT".equals(user.getRole()) ? "selected" : "" %>>PATIENT</option>
        </select>
        <br><br>
        <input type="hidden" name="action" value="modifierUtilisateur">
    </fieldset>
    <input type="submit" value="Valider" />
</form>


    
    <%@ include file="footer.jsp" %>
</body>
</html>