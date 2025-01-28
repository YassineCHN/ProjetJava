<%@page import="ENTITE.RoleUSER"%>
<%@page import="ENTITE.Z_PERSONNE"%>
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
    <%DossierHospitalisation dossier = (DossierHospitalisation) request.getAttribute("ficheDossier");
    Z_PERSONNE personne = (Z_PERSONNE) request.getAttribute("personne");
    RoleUSER role = (RoleUSER) request.getAttribute("role");  
        
//         Pour correctement afficher les dates dans les input de type "date" il faut les formater
       Date test1 = dossier.getDateHospitalisation();
       SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
       String dateHospitalisation = (test1 != null) ? sdf1.format(test1) : "";
       Date test2 = dossier.getHeureArrivee();
       SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
       String heureArrivee = (test2 != null) ? sdf2.format(test2) : "";
       Date test3 = dossier.getHeureDepart();
       SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
       String heureDepart = (test3 != null) ? sdf3.format(test3) : "";
       

       String message = (String) request.getAttribute("message");
        
    %>
    
    <h1>Dossier d'Hospitalisation</h1>
    <% if (message != null) { %>
        <p style="color: green;"><%= message %></p>
    <% } %>
            
    <div class="button-container">
        <form action="NewServlet" method="post" class="formulairebouton">
            <input type="hidden" id="id_annulerDossier" name="id_annulerDossier" value="<%=dossier.getId()%>">
            <input type="hidden" name="action" value="annulerDossier">
            <button type="submit">Annuler le Dossier</button>
        </form>
        <%if (role == RoleUSER.MEDECIN ) {%>

        <form action="NewServlet" method="post"class="formulairebouton">
            <input type="hidden" id="id_ajouterJournal" name="id_ajouterJournal" value="<%=Long.toString(dossier.getId())%>">
            <input type="hidden" name="action" value="ajouterJournal">
            <button type="submit">Acc�der au journal d'actes</button>
        </form>
        <%}%>
    </div>
                
        <form action="NewServlet" method="post">
        <fieldset>
            <label for="id_dossier"> ID dossier : </label>
            <input type="text" id="id_dossierFiche" name="id_dossierFiche" value="<%=dossier.getId()%>" readonly>
            <br><br>
            <label for="statutDossier">Statut Dossier :</label>
            <input type="text" id="StatutDossier" name="StatutDossier" value="<%=dossier.getStatutD()%>" readonly>
            <br><br>
            <label for="dateHospit">DateHospitalisation :</label>
            <input type="datetime-local" id="DateHospitalisation_ficheDossier" name="DateHospitalisation_ficheDossier" value="<%=dateHospitalisation%>" 
            <% if (role!=RoleUSER.MEDECIN) { %> readonly <% } %>>
            <br><br>
            <label for="dateIN">Date d'arriv�e :</label>
            <input type="datetime-local" id="DateArrivee_ficheDossier" name="DateArrivee_ficheDossier" value="<%=heureArrivee%>" 
            <% if (role!=RoleUSER.PERSONNEL) { %> readonly <% } %>>
            <br><br>
            <label for="dateOUT">Date de d�part :</label>
            <input type="datetime-local" id="DateDepart_ficheDossier" name="DateDepart_ficheDossier" value="<%=heureDepart%>" 
            <% if (role!=RoleUSER.PERSONNEL ) { %> readonly <% } %>>
            <br><br>
            <label for="idPatient">ID du patient :</label>
            <input type="text" id="IdPatient_ficheDossier" name="IdPatient_ficheDossier" value="<%=dossier.getLePatient().getIdpers()%> - <%=dossier.getLePatient().getNomPersonne()%> <%=dossier.getLePatient().getPrenomPersonne() %>" readonly>
            <br><br>
            <label for="ID_SERVICE">ID du service :</label>
            <input type="text" id="IdService_ficheDossier" name="IdService_ficheDossier" value="<%=dossier.getLeService().getId() %> - <%=dossier.getLeService().getServiceNom()%>" readonly>
            <br><br>
        
            
            <% if (role==RoleUSER.MEDECIN) { %>
            <input type="hidden" name="action" value="modifierDossierMedecin">
            <input type="submit" value="Valider les modifications"  />
            <% } else if(role==RoleUSER.PERSONNEL) { %>
            <input type="hidden" name="action" value="modifierDossierPersonnel">
            <input type="submit" value="Valider les modifications"  />
            <%}%>
        </fieldset>
        </form>
        
        
            
            
        

    <%@ include file="footer.jsp" %>
</body>
</html>