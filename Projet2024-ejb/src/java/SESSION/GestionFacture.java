/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.statutJournal;
import FACADE.FactureFacadeLocal;
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
    private FactureFacadeLocal factureFacade;

    
    
    

    @EJB
    private GestionActeLocal gestionActe;

    @EJB
    private GestionJournalActeLocal gestionJournalActe;

    

    
    @Override
    public Facture creerFacturePourJournal(Long idJournal) {
        JournalActe journal = gestionJournalActe.trouverJournalParId(idJournal);
         System.out.println("dans GestionFacture.java en entré");
        System.out.println(idJournal);
        System.out.println("dans GestionFacture.java en entré");
        
        System.out.println("==========================================");
        System.out.println("JOURNAL TROUVE ????? : ID DOSSIER");
        System.out.println(journal.getId());        
        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("JOURNAL TROUVE ????? : ID DOSSIER");
//        System.out.println(journal.getId());        
//        System.out.println("==========================================");
        if (journal == null) {
         return null; 
        }
        if (!journal.getStatut().equals(statutJournal.Validé)) {
        //creer que si Journal valide
        System.out.println("==========================================");
        System.out.println("JOURNAL STATUS ????? : STATUS");
        System.out.println(journal.getStatut());        
        System.out.println("==========================================");
        return null; 
    }
        
         double total = 0.0;
    // Récupérer les lignes
    List<LigneJournal> lignes = journal.getLigneJournals();
    for (LigneJournal ligne : lignes) {
        // Prix unitaire de l'acte * quantité
//        double prixUnitaire =  gestionActe.trouverActeParId(ligne.getId_acte().getId()).getActePrix();
        double prixUnitaire = ligne.getId_acte().getActePrix();
        int quantite = ligne.getQuantité_Acte();
        total += prixUnitaire * quantite;
    }
//        System.out.println("==========================================");
//        System.out.println("JOURNAL montant total ????? : monntant");
//        System.out.println(total);        
//        System.out.println("==========================================");
        Facture laFacture = factureFacade.creerFacturePourJournal(new Date(), total, false,  journal.getDossier(), journal);
//                System.out.println("==========================================");
//        System.out.println("FACTURE CREE ???? ????? : id facture");
//        System.out.println(laFacture.getId());        
//        System.out.println("==========================================");

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

    
    
}
