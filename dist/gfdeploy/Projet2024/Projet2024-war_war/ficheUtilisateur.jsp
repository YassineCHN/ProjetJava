<%@page import="java.util.List"%>
<%@page import="ENTITE.Z_PERSONNE"%>
<%@page import="ENTITE.Z_USER"%>

<!DOCTYPE html>
<%@ include file="navbar.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%Z_USER user1 = (Z_USER) request.getAttribute("utilisateurFicheUtilisateur");%>
    <jsp:useBean id="listepersonnes" scope="request" class="java.util.List"></jsp:useBean> 
    
    <form action="NewServlet" method="post">
    <fieldset>
        <label for="id_utilisateurFicheUtilisateur">ID utilisateur :</label>
        <input type="text" id="id_utilisateurFicheUtilisateur" name="id_utilisateurFicheUtilisateur" value="<%=Long.toString(user1.getId())%>" readonly>
        <br><br>
        <label for="login">Login :</label>
        <input type="text" id="login" name="login_ficheUtilisateur" value="<%=user1.getLogin()%>" required>
        <br><br>
        <label for="password">Mot de passe :</label>
        <input type="text" id="password_ficheUtilisateur" name="password_ficheUtilisateur" value="<%=user1.getMdp()%>" required>
        <br><br>
        <label for="role">R�le de l'utilisateur :</label>
        <input type="text" id="role_ficheUtilisateur" name="role_ficheUtilisateur" value="<%=user1.getRole()%>" readonly>
        <br><br>
        <%List<Z_PERSONNE> lesPers=listepersonnes;%>
        <div id="personneField" class="">
        <label for="UserPersonne">Personne : </label> 
                <select name="UserPersonne"> 
                    <option value="">Aucun</option>
                    <% for (Z_PERSONNE p :lesPers) {%> 
                    <option value ="<%=p.getIdpers() %>"> <%= p.getIdpers() %> - <%= p.getNomPersonne() %> <%= p.getPrenomPersonne() %></option> 
                    <% }%> 
                </select><br><br>
        </div>
        <input type="hidden" name="action" value="modifierUtilisateur">
    </fieldset>
    <input type="submit" value="Valider" />
</form>
 <form action="NewServlet">
        <input type="hidden" id="supprimerService" name="supprimerUtilisateur" value="<%=Long.toString(user1.getId())%>">
        <input type="hidden" name="action" value="supprimerUtilisateur">
        <input type="submit" value="Supprimer utilisateur" />
    </form>
    
    <%@ include file="footer.jsp" %>
</body>
</html>