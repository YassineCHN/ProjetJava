/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.Z_PATIENT;
import ENTITE.statutJournal;
import FACADE.FactureFacadeLocal;
import FACADE.LigneJournalFacadeLocal;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author charl
 */
@Stateless
public class GestionFacture implements GestionFactureLocal {

    @EJB
    private LigneJournalFacadeLocal ligneJournalFacade;

    @EJB
    private FactureFacadeLocal factureFacade;

    @EJB
    private GestionActeLocal gestionActe;

    @EJB
    private GestionJournalActeLocal gestionJournalActe;
    

    @Override
    public Facture creerFacturePourJournal(Long idJournal) {
        JournalActe journal = gestionJournalActe.trouverJournalParId(idJournal);
        if (journal == null) {
            System.out.println("[GestionFacture] Aucune facture créée : journal introuvable (id=" + idJournal + ").");
            return null;
        }

        // Vérifie si le journal est "Validé".
        if (journal.getStatut() != statutJournal.Validé) {
            System.out.println("[GestionFacture] Aucune facture créée : statut du journal non valide (" + journal.getStatut() + ").");
            return null;
        }

        // Calcule le total en parcourant les lignes
        double total = 0.0;
        System.out.println("=== Lignes du journal ID = " + journal.getId() + " ===");

        
//        Ca marche pas de récupérer les lignes à partir de l'attribut
//        List<LigneJournal> lignes = journal.getLigneJournals();
        List<LigneJournal> lignes = ligneJournalFacade.listerLignesParJournal(idJournal);

        if (lignes == null || lignes.isEmpty()) {
            System.out.println("Aucune ligne n'est associée à ce journal.");
        } else {
            for (LigneJournal ligne : lignes) {
                System.out.println(
                        "Ligne ID = " + ligne.getId()
                        + ", Acte = " + ((ligne.getId_acte() != null) ? ligne.getId_acte().getActeNom() : "N/A")
                        + ", Quantité = " + ligne.getQuantité_Acte()
                        + ", Commentaire = " + (ligne.getCommentaire() != null ? ligne.getCommentaire() : "Aucun")
                );
            }
        }
        for (LigneJournal ligne : lignes) {
            double prixUnitaire = ligne.getId_acte().getActePrix();
            int quantite = ligne.getQuantité_Acte();
            double coefSecu = ligne.getId_acte().getCoefficient_SecuriteSociale();
            double coefMutuelle = ligne.getId_acte().getCoefficient_Mutuelle();
            System.out.println("============================");
            System.out.println("ITERATION N");
            System.out.println("le prix unitaire : ");
            System.out.println(prixUnitaire);
            System.out.println("la quantité ");
            System.out.println(quantite);
            System.out.println("le coeff de la secu");
            System.out.println(coefSecu);
            System.out.println("le coef de la mutuelle");
            System.out.println(coefMutuelle);
            
            total += prixUnitaire * quantite*coefSecu*coefMutuelle;
            
            System.out.println("Total par itération");
            System.out.println(total);
        }

        // Création de la facture
        Facture laFacture = factureFacade.creerFacturePourJournal(new Date(), total, false,
                journal.getDossier(), journal);

        // Sorties console (vous pouvez envisager un vrai logger au lieu de System.out)
        System.out.println("[GestionFacture] Facture créée avec succès :");
        System.out.println(" - ID journal : " + journal.getId());
        System.out.println(" - Montant total : " + total);
        System.out.println(" - ID facture : " + laFacture.getId());

        return laFacture;
    }

    @Override
    public List<Facture> trouverToutesFactures() {
        return factureFacade.trouverToutesFactures();
    }

    @Override
    public Facture trouverFactureParDossier(DossierHospitalisation dossier) {
        return factureFacade.trouverFactureParDossier(dossier);
    }

    @Override
    public Facture trouverFactureParID(Long id) {
        return factureFacade.trouverFactureParID(id);
    }

    @Override
    public void validerFacturePaiement(Facture facture) {
        factureFacade.validerFacturePaiement(facture);
    }

    @Override
    public List<Facture> trouverFacturesPatient(Z_PATIENT patient) {
       return factureFacade.trouverFacturesPatient(patient);
        
    }

}
