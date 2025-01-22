<%@page import="ENTITE.Z_MEDECIN"%>
<%@page import="ENTITE.statutJournal"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ENTITE.Acte"%>
<%@page import="ENTITE.JournalActe"%>
<%@page import="ENTITE.LigneJournal"%>
<%@ include file="navbar.jsp" %>

<%
    // R�cup�ration du journal et de la liste d'actes
    JournalActe journal = (JournalActe) request.getAttribute("journal_object");
    List<Acte> actes = (List<Acte>) request.getAttribute("listeActeJournal");

    // R�cup�ration (�ventuelle) des lignes existantes du journal
    List<LigneJournal> lignes = (List<LigneJournal>) request.getAttribute("lignes_journals");
    // Pour formatter les dates des lignes existantes
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <% List<Z_MEDECIN> list = (List<Z_MEDECIN>) request.getAttribute("listeMedecins"); %>
       
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
   
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
                    <input type="hidden" name="idligne[]" placeholder="ID" value="999999999" required  >
                    <input type="text" name="commentaire[]" placeholder="Commentaire" required>
                    <input type="date" name="date[]" required>
                    <input type="number" name="quantite[]" placeholder="Quantit�" required>
                    
                    <select name="id_acte[]" required>
            <% if (actes != null) {
                    for (Acte acte : actes) {%>
                                 <option value="<%= acte.getId()%>"><%= acte.getActeNom()%></option>
            <%   }
                }%>
                    </select>
                        <label for="id_medecin[]">M�decin</label>
                    <select name="id_medecin[]" required>
            <% if (list != null) {
                    for (Z_MEDECIN medecin : list) {%>
                                 <option value="<%= medecin.getIdpers()%>"><%= medecin.getNomPersonne()%> - <%=medecin.getPrenomPersonne()%></option>
            <%   }
                }%>
                    </select>
                `;
                container.appendChild(newLine);
            }
        </script>
    </head>
    <body>
        <h1>D�tails du journal</h1>
        

        <form action="NewServlet">
            <!--ID du journal -->
            <input type="hidden" id="id_journalcreerFacture" name="id_journalcreerFacture" value="<%=Long.toString(journal.getId())%>">
            <input type="hidden" name="action" value="creerFacture">
            <!-- Attention � quel id on prend... -->
            <!--ID du journal -->
            <input type="hidden" name="id_dossierCreerFacture" value="<%= (journal != null) ? journal.getDossier().getId() : ""%>">
            <input type="submit" value="Acc�der � la facture" />
        </form>
            
            
            
            <!-- j'utilise la balise dialog qui est native et est tr�s astucieux
                elle permet de faire appara�tre une sorte de "pop-up" ouvrable et fermable � volont�
                dialog a une propri�t� "open" et closed (sinon absence de propri�t� par d�faut) qu'on peut manipuler
                avec du javascript.
                Ca consiste � manipuer deux m�thodes de l'objet/balise dialog :
                .close() et .showModal(). On fait �a avec les boutons
            -->
            <div class="button-form">
                <dialog id="myDialog">
            <form action="NewServlet">
                <!-- ID du journal -->
                <p>Si vous validez le journal, vous ne pourrez plus saisir de lignes.</p>
                <input type="hidden" id="id_journalValidation" name="id_journalValidation" value="<%=Long.toString(journal.getId())%>">
                <input type="hidden" name="action" value="validerJournal">
                <input type="submit" value="Valider quand m�me le journal" />
                
            </form>
            <button onclick="document.getElementById('myDialog').close()">Fermer</button>
        </dialog>
        <button onclick="document.getElementById('myDialog').showModal()">Valider le journal</button>
            </div>
            
        
            
            
        <form action="NewServlet" method="post" name="entete_journal">
            <fieldset>
                <label for="idJournal_ficheJournal">ID Journal</label>
                <input type="text" id="idJournal_ficheJournal" name="idJournal_ficheJournal"
                       value="<%= (journal != null) ? journal.getId() : ""%>" readonly>
                <br><br>

                <label for="dateCreation_ficheJournal">Date de cr�ation</label>
                <input type="date" id="dateCreation_ficheJournal" name="dateCreation_ficheJournal"
                       value="<%= (journal != null && journal.getDateCreation() != null)
                               ? sdf.format(journal.getDateCreation()) : ""%>" readonly>
                <br><br>

                <label for="dossier_ficheJournal">Dossier</label>
                <input type="text" id="dossier_ficheJournal" name="dossier_ficheJournal"
                       value="<%= (journal != null && journal.getDossier() != null)
                               ? journal.getDossier().getId() : ""%>" readonly>
                <br><br>

                <label for="statut_ficheJournal">Statut journal</label>
                <input type="text" id="statut_ficheJournal" name="statut_ficheJournal"
                       value="<%= (journal != null && journal.getStatut() != null)
                               ? journal.getStatut() : ""%>" readonly>
                <br><br>
                <label for="datevalidation_journal">Date de validation</label>
                <input type="date" id="datevalidation_journal" name="datevalidation_journal"
                       value="<%= (journal != null && journal.getDateValidation()!= null)
                               ? sdf.format(journal.getDateValidation()) : ""%>" readonly>
                <br><br>
            </fieldset>
        </form>

        <h1>Ajouter ou modifier les lignes du journal</h1>

        <!-- Formulaire pour les lignes du journal -->
        <form id="linesContainer" action="NewServlet" method="post" class="lignes_journal" >

            <!-- Bouton pour ajouter une ligne vide -->
            <button type="button" onclick="addLine()" 
                    <%= (journal != null && journal.getStatut() == statutJournal.Valid�) ? "disabled" : ""%>>
                Ajouter Ligne
            </button>

            <!-- Input pour enregistrer les lignes -->
            <input type="submit" value="Enregistrer les Lignes" 
                   <%= (journal != null && journal.getStatut() == statutJournal.Valid�) ? "disabled" : ""%>> 

            <!-- Champs cach�s pour l'action et l'id du journal -->
            <input type="hidden" name="id_journal" value="<%= (journal != null) ? journal.getId() : ""%>">
            <input type="hidden" name="action" value="ajouterLignesJournal" 
                   <%= (journal != null && journal.getStatut() == statutJournal.Valid�) ? "disabled" : ""%>>

            <p><%= (journal != null && journal.getStatut() == statutJournal.Valid�) ? "Le journal est valid�, il n'est plus possible de saisir de lignes" : ""%></p>
            <%
                // Si le journal existe et qu'il a des lignes, on les affiche.
                // Sinon, on affiche un seul block "vide" par d�faut.
                if (lignes != null && !lignes.isEmpty()) {
                    for (LigneJournal ligne : lignes) {
                        // On formate la date si n�cessaire
                        String dateActe = (ligne.getDate_acte() != null) ? sdf.format(ligne.getDate_acte()) : "";
                        boolean isDisabled = journal != null && journal.getStatut() == statutJournal.Valid�;
            %>
            <div class="line-container">
                <hr>
                <input type="hidden" name="idligne[]" placeholder="ID" 
                       value="<%= (ligne.getId() != null) ? ligne.getId() : ""%>"
                       <%= isDisabled ? "disabled" : ""%>required >
                <input type="text" name="commentaire[]" placeholder="Commentaire"
                       value="<%= (ligne.getCommentaire() != null) ? ligne.getCommentaire() : ""%>"
                       <%= isDisabled ? "disabled" : ""%> required>
                <input type="date" name="date[]" value="<%= dateActe%>"
                       <%= isDisabled ? "disabled" : ""%> required>
                <input type="number" name="quantite[]" placeholder="Quantit�"
                       value="<%= ligne.getQuantit�_Acte()%>"
                       <%= isDisabled ? "disabled" : ""%> required>

                <select name="id_acte[]" <%= isDisabled ? "disabled" : ""%> required>
                    <%
                        if (actes != null) {
                            for (Acte acte : actes) {
                                String selected = (ligne.getId_acte() != null
                                        && ligne.getId_acte().getId().equals(acte.getId()))
                                        ? "selected" : "";
                    %>
                    <option value="<%= acte.getId()%>" <%= selected%>>
                        <%= acte.getActeNom()%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
                <!-- S�lection du m�decin -->
                <label for="id_medecin[]">M�decin</label>
                <select name="id_medecin[]" required>
                    <%

                        if (list != null) {
                            for (Z_MEDECIN medecin : list) {
                    %>
                    <option value="<%= medecin.getIdpers()%>">
                        <%= medecin.getNomPersonne()%> - <%=medecin.getPrenomPersonne()%>
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

                <!--a
                l'id sera r�cup�r� par la servlet. Si il est null alors erreur NullPointer
//              Le probl�me c'est que je sais pas g�rer �a au niveau de la servlet (if (Long) idligne[i] == null then ...)
                Une solution c'est de renseigner un ID tr�s grand qui ne sera jamais renseign� dans la BD-->
                <input type="hidden" name="idligne[]" placeholder="ID" value="999999999" required>
                <input type="text" name="commentaire[]" placeholder="Commentaire" required>
                <input type="date" name="date[]" required>
                <input type="number" name="quantite[]" placeholder="Quantit�" required>
                <label for="id_acte[]">Acte</label>
                <select name="id_acte[]" required>
                    <% if (actes != null) {
                            for (Acte acte : actes) {%>
                    <option value="<%= acte.getId()%>">
                        <%= acte.getActeNom()%>
                    </option>
                    <%   }
                        } %>
                </select>
                <!-- S�lection du m�decin -->
                <label for="id_medecin[]">M�decin</label>
                <select name="id_medecin[]" required>
                    <%

                        if (list != null) {
                            for (Z_MEDECIN medecin : list) {
                    %>
                    <option value="<%= medecin.getIdpers()%>">
                        <%= medecin.getNomPersonne()%> - <%=medecin.getPrenomPersonne()%>
                    </option>
                    <%
                            }
                        }
                    %>
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
