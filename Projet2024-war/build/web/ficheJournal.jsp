<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ENTITE.Acte"%>
<%@page import="ENTITE.JournalActe"%>
<%@page import="ENTITE.LigneJournal"%>
<%@ include file="navbar.jsp" %>

<%
    // Récupération du journal et de la liste d'actes
    JournalActe journal = (JournalActe) request.getAttribute("journal_object");
    List<Acte> actes = (List<Acte>) request.getAttribute("listeActeJournal");

    // Récupération (éventuelle) des lignes existantes du journal
    List<LigneJournal> lignes = (List<LigneJournal>) request.getAttribute("lignes_journals");
    
    // Pour formatter les dates des lignes existantes
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
                System.out.println(lignes.size());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fiche Journal</title>
    <style>
        .line-container {
            display: flex;
            gap: 10px;
            margin-bottom: 10px;
        }
        .line-container input,
        .line-container select {
            width: 150px;
        }
    </style>
    <script>
        // Fonction pour ajouter dynamiquement une nouvelle ligne vide
        function addLine() {
            var container = document.getElementById('linesContainer');
            var newLine = document.createElement('div');
            newLine.className = 'line-container';
            newLine.innerHTML = `
                <hr>
                <input type="text" name="commentaire[]" placeholder="Commentaire" required>
                <input type="date" name="date[]" required>
                <input type="number" name="quantite[]" placeholder="Quantité" required>
                <select name="id_acte[]" required>
                    <% if (actes != null) {
                         for (Acte acte : actes) { %>
                             <option value="<%= acte.getId() %>"><%= acte.getActeNom() %></option>
                    <%   }
                       } %>
                </select>
            `;
            container.appendChild(newLine);
        }
    </script>
</head>
<body>
    <h1>Détails du journal</h1>

    <form action="NewServlet" method="post" name="entete_journal">
        <fieldset>
            <label for="idJournal_ficheJournal">ID Journal</label>
            <input type="text" id="idJournal_ficheJournal" name="idJournal_ficheJournal"
                   value="<%= (journal != null) ? journal.getId() : "" %>" readonly>
            <br><br>
            
            <label for="dateCreation_ficheJournal">Date de création</label>
            <input type="date" id="dateCreation_ficheJournal" name="dateCreation_ficheJournal"
                   value="<%= (journal != null && journal.getDateCreation() != null)
                                ? sdf.format(journal.getDateCreation()) : "" %>" readonly>
            <br><br>
            
            <label for="dossier_ficheJournal">Dossier</label>
            <input type="text" id="dossier_ficheJournal" name="dossier_ficheJournal"
                   value="<%= (journal != null && journal.getDossier() != null)
                                ? journal.getDossier().getId() : "" %>" readonly>
            <br><br>

            <label for="utilisateur_ficheJournal">Utilisateur</label>
            <input type="text" id="utilisateur_ficheJournal" name="utilisateur_ficheJournal"
                   value="<%= (journal != null && journal.getUtilisateurCreateur() != null)
                                ? journal.getUtilisateurCreateur().getId() : "" %>" readonly>
            <br><br>
        </fieldset>
    </form>

    <h1>Ajouter ou modifier les lignes du journal</h1>

    <!-- Formulaire pour les lignes du journal -->
    <form id="linesContainer" action="NewServlet" method="post" class="lignes_journal" >
        
        <!-- Bouton pour ajouter une ligne vide -->
        <button type="button" onclick="addLine()">Ajouter Ligne</button>
        <input type="submit" value="Enregistrer les Lignes">
        <!-- Champs cachés pour l'action et l'id du journal -->
        <input type="hidden" name="id_journal" value="<%= (journal != null) ? journal.getId() : "" %>">
        <input type="hidden" name="action" value="ajouterLignesJournal">

        <%
            // Si le journal existe et qu'il a des lignes, on les affiche.
            // Sinon, on affiche un seul block "vide" par défaut.
            if (lignes != null && !lignes.isEmpty()) {
                for (LigneJournal ligne : lignes) {
                
                    // On formate la date si nécessaire
                    String dateActe = (ligne.getDate_acte()!= null) ? sdf.format(ligne.getDate_acte()) : "";
        %>
                    <div class="line-container">
                        <hr>
                        <input type="text" name="commentaire[]" placeholder="Commentaire"
                               value="<%= (ligne.getCommentaire() != null) ? ligne.getCommentaire() : "" %>" required>
                        <input type="date" name="date[]" value="<%= dateActe %>" required>
                        <input type="number" name="quantite[]" placeholder="Quantité"
                               value="<%= ligne.getQuantité_Acte()%>" required>
                        <select name="id_acte[]" required>
                            <%
                                if (actes != null) {
                                    for (Acte acte : actes) {
                                        String selected = (ligne.getId_acte()!= null
                                                        && ligne.getId_acte().getId().equals(acte.getId()))
                                                          ? "selected" : "";
                            %>
                                <option value="<%= acte.getId() %>" <%= selected %>>
                                    <%= acte.getActeNom() %>
                                </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
        <%
                } // end for
            } else {
                // Aucune ligne existante => on propose un seul bloc vierge
        %>
                <div class="line-container">
                    <hr>
                    <input type="text" name="commentaire[]" placeholder="Commentaire" required>
                    <input type="date" name="date[]" required>
                    <input type="number" name="quantite[]" placeholder="Quantité" required>
                    <select name="id_acte[]" required>
                        <% if (actes != null) {
                             for (Acte acte : actes) { %>
                                <option value="<%= acte.getId() %>">
                                    <%= acte.getActeNom() %>
                                </option>
                        <%   }
                           } %>
                    </select>
                </div>
        <%
            }
        %>

        <!-- Bouton de validation du formulaire -->
       <!-- <input type="submit" value="Enregistrer les Lignes"> -->
    </form>

    <%@ include file="footer.jsp" %>
</body>
</html>
