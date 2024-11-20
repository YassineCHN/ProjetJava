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
    <%Utilisateur user = (Utilisateur) request.getAttribute("utilisateurFicheUtilisateur");%>
    <label for ="id_utilisateur"> ID utilisateur : </label>
            <input type ="text" id="id_utilisateur" name="id_utilisateur" value="<%=Long.toString(user.getId())%>" disabled required>
    <form action="NewServlet">
        <fieldset>
            
            <br><br>
            <label for="login">Login :</label>
            <input type="text" id="login" name="login_ficheUtilisateur" value="<%=user.getUtilisateurLogin()%>" required>
            <br><br>
            <label for="password">Mot de passe :</label>
            <input type="text" id="password" name="password_ficheUtilisateur" value="<%=user.getUtilisateurMDP()%>" required>
            <label for="role">Role de l'utilisateur :</label>
            <input type="text" id="role" name="role_ficheUtilisateur" value="<%=user.getUtilisateurRole()%>" required>
            <input type="hidden" name="action" value="modifierUtilisateur">
        </fieldset>
        <input type="submit" value="Valider"  />
    </form>

    
    <%@ include file="footer.jsp" %>
</body>
</html>