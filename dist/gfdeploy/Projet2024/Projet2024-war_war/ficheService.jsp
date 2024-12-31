<%@page import="ENTITE.Service"%>
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
    <%Service service = (Service) request.getAttribute("ficheService");%>
    <label for ="id_service"> ID utilisateur : </label>
            <input type ="text" id="id_service" name="id_service" value="<%=Long.toString(service.getId())%>" disabled required>
    <form action="NewServlet">
        <fieldset>
            
            <br><br>
            <label for="login">Localisation :</label>
            <input type="text" id="localisation" name="localisation_ficheService" value="<%=service.getServiceLocalisation()%>" required>
            <br><br>
            <label for="password">Nom :</label>
            <input type="text" id="nomService" name="nomService_ficheService" value="<%=service.getServiceNom()%>" required>
            
            <input type="hidden" name="action" value="modifierService">
        </fieldset>
        <input type="submit" value="Valider"  />
        
    </form>
    <form action="NewServlet">
        <input type="hidden" id="supprimerService" name="supprimerService" value="<%=Long.toString(service.getId())%>">
        <input type="hidden" name="action" value="supprimerService">
        <input type="submit" value="Supprimer Service" />
    </form>
    
    <%@ include file="footer.jsp" %>
</body>
</html>