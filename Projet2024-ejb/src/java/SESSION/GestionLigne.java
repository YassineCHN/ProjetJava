/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.Z_MEDECIN;
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
public class GestionLigne implements GestionLigneLocal {

    @EJB
    private LigneJournalFacadeLocal ligneJournalFacade;

    
    
    
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
    
    
    
    
    
}
