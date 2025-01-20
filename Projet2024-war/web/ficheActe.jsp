<%@page import="ENTITE.Acte"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fiche Acte</title>
</head>
<body>
    <% Acte acte = (Acte) request.getAttribute("ficheActe"); %>
    
    <form action="NewServlet" method="post">
    <fieldset>
        <legend>Fiche Acte</legend>
        
        <label for="id_acte">ID Acte :</label>
        <input type="text" id="id_acte" name="id_acte" value="<%= Long.toString(acte.getId()) %>" readonly>
        <br><br>
        
        <label for="acte_nom">Nom de l'Acte :</label>
        <input type="text" id="acte_nom" name="acte_nom" value="<%= acte.getActeNom() %>" required>
        <br><br>
        
        <label for="acte_description">Description de l'Acte :</label>
        <input type="text" id="acte_description" name="acte_description" value="<%= acte.getActeDescription() %>" required>
        <br><br>
        
        <label for="acte_prix">Prix de l'Acte :</label>
        <input type="number" step="1" id="acte_prix" name="acte_prix" value="<%= acte.getActePrix() %>" required>
        <br><br>
        <label for="coefSecu">Coefficient de la sécurité sociale :</label>
        <input type="number" step="0.01" max="1" min="0.00" id= "coefSecu" name="coefSecu" value="<%= acte.getCoefficient_SecuriteSociale()%>" required>
        <br><br>
        <label for="coefMutuelle">Coefficient de la mutuelle :</label>
        <input type="number" step="0.01" max="1" min="0.00" id="coefMutuelle" name="coefMutuelle" value="<%= acte.getCoefficient_Mutuelle()%>" required>
        <br><br>
        
        <input type="hidden" name="action" value="modifierActe">
        <input type="submit" value="Valider">
    </fieldset>
    </form>
    
    <form action="NewServlet" method="post">
        <input type="hidden" name="id_supprimerActe" value="<%= Long.toString(acte.getId()) %>">
        <input type="hidden" name="action" value="supprimerActe">
        <input type="submit" value="Supprimer Acte">
    </form>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
