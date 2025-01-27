/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import ENTITE.Service;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import ENTITE.statutJournal;
import FACADE.ActeFacadeLocal;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.FactureFacadeLocal;
import FACADE.JournalActeFacadeLocal;
import FACADE.LigneJournalFacadeLocal;
import FACADE.PaiementFacadeLocal;
import FACADE.ServiceFacadeLocal;
import FACADE.Z_PERSONNEFacadeLocal;
import FACADE.Z_USERFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ychen
 */
@Stateless
public class SessionPersonnelFinancier implements SessionPersonnelFinancierLocal {

    @EJB
    private JournalActeFacadeLocal journalActeFacade;
    
    @EJB
    private ActeFacadeLocal acteFacade;
    
    @EJB
    private FactureFacadeLocal factureFacade;
    
    @EJB
    private ServiceFacadeLocal serviceFacade;
    
    @EJB
    private PaiementFacadeLocal paiementFacade;
    
    @EJB
    private DossierHospitalisationFacadeLocal dossierHospitalisationFacade;
    
    @EJB
    private LigneJournalFacadeLocal ligneJournalFacade;
    
    @EJB
    private Z_PERSONNEFacadeLocal z_PERSONNEFacade;

    @EJB
    private Z_USERFacadeLocal z_USERFacade;
    
    @Override
    public Acte trouverActeParId(long id) {
        Acte result = acteFacade.trouverActeParId(id);
        return result;
    }
    
    @Override
    public DossierHospitalisation trouverDossierParId(Long id) {
        DossierHospitalisation dossier = dossierHospitalisationFacade.trouverDossierHospitalisationParId(id);
        return dossier;
    }
    
    @Override
    public JournalActe trouverJournalParId(Long id) {
        return journalActeFacade.trouverJournalParId(id);
    }
    
    @Override
    public List<JournalActe> trouverTousLesJournaux() {
        return journalActeFacade.trouverTousLesJournaux();
    }
    
    @Override
    public void validerJournal(JournalActe journal) {
        journalActeFacade.validerJournal(journal);
    }
    
    @Override
    public Facture creerFacturePourJournal(Long idJournal) {
        JournalActe journal = trouverJournalParId(idJournal);
        if (journal == null) {
            System.out.println("[GestionFacture] Aucune facture créée : journal introuvable (id=" + idJournal + ").");
            return null;
        }

        // vérifier que le journal est différent de validé
        // si oui alors on peut pas créer de facture.
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
            
//            pour le débogage
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
            
//            La secu et la mutuelle prenne en charge une part du total
//          Que ce passe t il si les coeffs sont nuls ? edge case
            total += (prixUnitaire * quantite)*(1-coefSecu-coefMutuelle) ;
            
            System.out.println("Total par itération");
            System.out.println(total);
        }

        // Création de la facture
        Facture laFacture = factureFacade.creerFacturePourJournal(new Date(), total, false,
                journal.getDossier(), journal);

        // débogage
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

    @Override
    public List<Facture> trouverFacturesNonPayeesAvecEmissionDepassee() {
         
        List<Facture> result = factureFacade.trouverFacturesNonPayeesAvecEmissionDepassee();
        
        if (result != null) {
            return result;
        } else {
            return null;
        }
    }
    @Override
    public void creerLigne(Date date_acte, int quantite, String commentaire, Acte acte, JournalActe journal, Z_MEDECIN leMedecin) {
        ligneJournalFacade.creerLigneJournal(date_acte, quantite, commentaire, acte, journal, leMedecin);
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<LigneJournal> trouverToutesLignes() {
      return  ligneJournalFacade.trouverToutesLignes();
    }

    @Override
    public void supprimerLigne(long id) {
        ligneJournalFacade.supprimerLigne(id);
    }

    @Override
    public List<LigneJournal> listerLignesParJournal(Long idJournal) {
        return ligneJournalFacade.listerLignesParJournal(idJournal);
    }

    @Override
    public LigneJournal trouverLigneParId(Long id) {
        return ligneJournalFacade.trouverLigneParId(id);
    }

    @Override
    public void mettreAJourLigne(LigneJournal ligne) {
        ligneJournalFacade.mettreAJourLigne(ligne);
    }
    
    @Override
    public Paiement enregistrerPaiement(Double montantPaiement, ModePaiement modePaiement, Facture laFacture) {
        return paiementFacade.enregistrerPaiement(montantPaiement, modePaiement, laFacture);
    }
    
    @Override
    public Service trouverServiceParID(Long id) {
        Service test = serviceFacade.trouverServiceParId(id);
        return test;
    }
    
    @Override
    public void modifierPersonne(Z_PERSONNE pers) {
        z_PERSONNEFacade.mettreAJourPersonne(pers);
    }
    
    @Override
    public Z_USER trouverUtilisateurParPers(Long id) {
        Z_USER user = z_USERFacade.trouverUtilisateurParPersonne(id);
        return user;
    }
    
    @Override
    public Z_PERSONNE trouverPersonneParId(Long id) {
        Z_PERSONNE pers = z_PERSONNEFacade.trouverPersonneParId(id);
        return pers;
    }
    
    @Override
    public Z_USER trouverUtilisateurParId(Long id) {
        Z_USER user = z_USERFacade.trouverUtilisateurParId(id);
        return user;
    }
    
    
}
