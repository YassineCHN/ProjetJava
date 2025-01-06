<%@page import="java.util.List"%>
<%@page import="ENTITE.Acte"%>
<%@page import="ENTITE.JournalActe" %>
<%@page import="SESSION.GestionJournalActe" %>
<%@ include file="navbar.jsp" %>
<% JournalActe journal = (JournalActe) request.getAttribute("journal_object"); %>
<% List<Acte> actes = (List<Acte>) request.getAttribute("listeActeJournal"); %>
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
        function addLine() {
            var container = document.getElementById('linesContainer');
            var newLine = document.createElement('div');
            newLine.className = 'line-container';
            newLine.innerHTML = `
            <hr> 
                <input type="text" name="commentaire[]" placeholder="Commentaire" required>
                <input type="date" name="date[]" required>
                <input type="number" name="quantite[]" placeholder="Quantit�" required>
                <select name="id_acte[]" required>
                    <% for (Acte acte : actes) { %>
                        <option value="<%= acte.getId() %>"><%= acte.getActeNom() %></option>
                    <% } %>
                </select>
                           
            `;
            container.appendChild(newLine);
        }
    </script>
</head>
<body>
    <h1>D�tails du journal</h1>

    <form action="NewServlet" method="post" name="entete_journal">
        <fieldset>
            <label for="idJournal_ficheJournal"> ID Journal</label>
            <input type="text" id="idJournal_ficheJournal" name="idJournal_ficheJournal"
                   value="<%= journal.getId() %>" readonly>
            <br><br>
            <label for="dateCreation_ficheJournal"> Date de cr�ation</label>
            <input type="date" id="dateCreation_ficheJournal" name="dateCreation_ficheJournal"
                   value="<%= journal.getDateCreation() %>" readonly>
            <br><br>
            <label for="dossier_ficheJournal"> Dossier </label>
            <input type="text" id="dossier_ficheJournal" name="dossier_ficheJournal"
                   value="<%= journal.getDossier().getId() %>" readonly>
            <br><br>

            <label for="utilisateur_ficheJournal"> Utilisateur </label>
            <input type="text" id="utilisateur_ficheJournal" name="utilisateur_ficheJournal"
                   value="<%= journal.getUtilisateurCreateur().getId() %>" readonly>
            <br><br>
        </fieldset>
    </form>

    <h1>Ajouter des lignes au journal</h1>

    <form id="linesContainer" action="NewServlet" method="post" class="lignes_journal" >
        <button type="button" onclick="addLine()">Ajouter Ligne</button>   
        <input type="hidden" name="id_journal" value="<%= journal.getId() %>">
        <input type="hidden" name="action" value="ajouterLignesJournal">
        <input type="submit" value="Enregistrer les Lignes">
        <div class="line-container">
            <hr>
            <input type="text" name="commentaire[]" placeholder="Commentaire" required>
            <input type="date" name="date[]" required>
            <input type="number" name="quantite[]" placeholder="Quantit�" required>
            <select name="id_acte[]" required>
                <% for (Acte acte : actes) { %>
                    <option value="<%= acte.getId() %>"><%= acte.getActeNom() %></option>
                <% } %>
            </select>
            
        </div>
        
    </form>
         
    

    <form action="NewServlet" method="post">
        
    </form>

    <%@ include file="footer.jsp" %>
</body>
</html>
