<%@page import="ENTITE.RoleUSER"%>
<%@page import="ENTITE.Facture"%>
<%@page import="ENTITE.JournalActe"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="navbar.jsp" %>

<%
    // R�cup�ration de la facture depuis la requ�te
    Facture facture = (Facture) request.getAttribute("facture");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String statutFacture;
    if (facture.isFacturePayee()==true){
        statutFacture ="Pay�e";
    }
    else {
        statutFacture = "Impay�e";
    }
    RoleUSER role = (RoleUSER) request.getAttribute("role");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Fiche Facture</title>
</head>
<body>
    <h1>Fiche Facture</h1>
    
    <%
        // On affiche les informations uniquement si la facture existe
        if (facture != null) {
    %>
        <!-- Formulaire de modification de la facture -->
        <form action="NewServlet" method="post">
            <fieldset>
                <legend>Fiche Facture</legend>
                
                <!-- ID Facture -->
                <label for="id_facture">ID Facture :</label>
                <input type="text" id="id_facture" name="id_facture"
                       value="<%= facture.getId() %>" disabled required>
                <br><br>
                
                <!-- Montant total -->
                <label for="facture_montant">Montant Total :</label>
                <input type="number" step="0.01" id="facture_montant" name="facture_montant"
                       value="<%= facture.getFactureMontant() %>" required>
                <br><br>
                
                <!-- Date de facturation -->
                <label for="facture_date">Date Facturation :</label>
                <input type="date" id="facture_date" name="facture_date"
                       value="<%= (facture.getFactureDateEmissions() != null)
                                ? sdf.format(facture.getFactureDateEmissions())
                                : "" %>" required>
                <br><br>
                
                <!-- Journal -->
                <label for="facture_journal">Journal :</label>
                <input type="text" id="facture_journal" name="facture_journal"
                       value="<%= (facture.getLeJournal() != null)
                                ? facture.getLeJournal().getId()
                                : "" %>" disabled>
                <br><br>
                
                <!-- Patient -->
                <label for="facture_patient">Patient :</label>
                <input type="text" id="facture_patient" name="facture_patient"
                       value="<%= (facture.getLeDossier() != null
                                   && facture.getLeDossier().getLePatient() != null)
                                ? facture.getLeDossier().getLePatient().getNomPersonne()
                                : "" %>"
                       disabled>
                <br><br>
                <!-- Statut facture -->
                <label for="facture_patient">Statut de la facture :</label>
                <input type="text" id="facture_patient" name="facture_patient"
                       value="<%=statutFacture%>"
                       disabled>
                <br><br>
               
            </fieldset>
        </form>
                       
        <%if (role == RoleUSER.PERSONNEL || role == RoleUSER.ADMIN) {%>
        <!-- Formulaire de suppression de la facture -->
        <form action="NewServlet" method="post">
            <input type="hidden" name="id_supprimerFacture" value="<%= facture.getId() %>">
            <input type="hidden" name="action" value="supprimerFacture">
            <input type="submit" value="Supprimer Facture">
        </form>
            <%}%>
            <form action="NewServlet" method="post">
                <input type="hidden" name="id_payerFacture" value="<%= facture.getId()%>">
                <input type="hidden" name="action" value="payerFacture">

                <label for="mode_paiement">Mode de paiement :</label>
                <select id="mode_paiement" name="mode_paiement" 
                        <%= (facture != null && facture.isFacturePayee()) ? "disabled" : ""%> required>
                    <option value="VIREMENT">Virement</option>
                    <option value="CARTE">Carte Bancaire</option>
                    <option value="CHEQUE">Ch�que</option>
                    <option value="ESPECES">Esp�ces</option>
                </select>

                <input type="submit" value="Payer la facture"
                       <%= (facture != null && facture.isFacturePayee()) ? "disabled" : ""%>>

                <p>
                    <%= (facture != null && facture.isFacturePayee())
                ? "La facture est pay�e, repayer n'est plus possible"
                : ""%>
                </p>
            </form>

        
    <%
        } else {
    %>
        <!-- Aucune facture disponible -->
        <p>Aucune facture � afficher.</p>
    <%
        }
    %>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
