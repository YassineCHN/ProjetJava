<%@page import="ENTITE.DossierHospitalisation"%>
<%@page import="ENTITE.Service"%>

<%@page import ="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<%@ include file="navbar.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%DossierHospitalisation dossier = (DossierHospitalisation) request.getAttribute("ficheDossier");%>
    <% //   
//         Pour correctement afficher les dates dans les input de type "date" il faut les formater
        Date test1 = dossier.getDateHospitalisation(); SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); String dateHospitalisation = sdf1.format(test1);
        Date test2 = dossier.getHeureArrivee(); SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); String HeureArrivee = sdf2.format(test2);
       Date test3 = dossier.getHeureDepart(); SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); String HeureDepart = sdf3.format(test3);
        
    %>
    <form action="NewServlet">
        <input type="hidden" id="id_supprimerDossier" name="id_supprimerDossier" value="<%=Long.toString(dossier.getId())%>">
        <input type="hidden" name="action" value="supprimerDossier">
        <input type="submit" value="Supprimer le dossier" />
    </form>
    
        
    <form action="NewServlet">
        <input type="hidden" id="id_ajouterJournal" name="id_ajouterJournal" value="<%=Long.toString(dossier.getId())%>">
        <input type="hidden" name="action" value="ajouterJournal">
        <input type="submit" value="Accéder au journal d'actes">
    </form>
            
    <form action="NewServlet">
        <input type="hidden" id="id_annulerDossier" name="id_annulerDossier" value="<%=Long.toString(dossier.getId())%>">
        <input type="hidden" name="action" value="annulerDossier">
        <input type="submit" value="Annuler le Dossier">
    </form>
            
            
    <form action="">
        <fieldset>
            <label for="id_dossier"> ID utilisateur : </label>
            <input type="text" id="id_dossierFiche" name="id_dossierFiche" value="<%=Long.toString(dossier.getId())%>"
                disabled required>
        </fieldset>
    </form>

   
        <form action="NewServlet">
        <fieldset>
            
            <br><br>
            <label for="login">DateHospitalisation :</label>
            <input type="datetime-local" id="DateHospitalisation_ficheDossier" name="DateHospitalisation_ficheDossier" value="<%=dateHospitalisation%>" required>
            <br><br>
            <label for="login">Date d'arrivï¿½e :</label>
            <input type="datetime-local" id="DateArrivee_ficheDossier" name="DateArrivee_ficheDossier" value="<%=HeureArrivee%>" required>
            <br><br>
            <label for="login">Date de dï¿½part :</label>
            <input type="datetime-local" id="DateDepart_ficheDossier" name="DateDepart_ficheDossier" value="<%=HeureDepart%>" required>
            <br><br>
            <label for="login">ID du patient :</label>
            <input type="text" id="IdPatient_ficheDossier" name="IdPatient_ficheDossier" value="<%=dossier.getLePatient().getIdpers()%>" readonly>
            <br><br>
            <label for="login">ID du service :</label>
            <input type="text" id="IdService_ficheDossier" name="IdService_ficheDossier" value="<%=dossier.getLeService().getId() %>" readonly>
            <br><br>
            
            
            <input type="hidden" name="action" value="modifierDossier">
        </fieldset>
        <input type="submit" value="Valider"  />
        
    </form>
    
    
    <%@ include file="footer.jsp" %>
</body>
</html>