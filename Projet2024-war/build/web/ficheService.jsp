<%@page import="ENTITE.Service"%>
//
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
    
    <form action="NewServlet">
        <fieldset>
            <label for ="id_service"> ID : </label>
            <input type ="text" id="id_service" name="id_service" value="<%=Long.toString(service.getId())%>" readonly>
            <br><br>
            <label for="localisation">Localisation :</label>
            <input type="text" id="localisation" name="localisation" value="<%=service.getServiceLocalisation()%>" required>
            <br><br>
            <label for="nomService">Nom :</label>
            <input type="text" id="nomService" name="nomService" value="<%=service.getServiceNom()%>" required>
            
            <input type="hidden" name="action" value="modifierService">
        </fieldset>
        <input type="submit" value="Valider"  />
        
    </form>
    <form action="NewServlet">
        <input type="hidden" id="id_supprimerService" name="id_supprimerService" value="<%=Long.toString(service.getId())%>">
        <input type="hidden" name="action" value="supprimerService">
        <input type="submit" value="Supprimer Service" />
    </form>
    
    <%@ include file="footer.jsp" %>
</body>
</html>