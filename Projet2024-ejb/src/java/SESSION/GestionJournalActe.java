/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.JournalActe;
import ENTITE.Z_USER;
import FACADE.JournalActeFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class GestionJournalActe implements GestionJournalActeLocal {

    @EJB
    private JournalActeFacadeLocal journalActeFacade;

    @Override
    public JournalActe creerJournal(DossierHospitalisation dossier, Z_USER user) {
        JournalActe journal = journalActeFacade.creerJournal(dossier, user);
        return journal;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public JournalActe trouverJournalParId(Long id) {
        return journalActeFacade.trouverJournalParId(id);
    }

    @Override
    public List<JournalActe> trouverTousLesJournaux() {
        return journalActeFacade.trouverTousLesJournaux();
    }

    @Override
    public JournalActe trouverJournalParDossier(DossierHospitalisation dossier) {
        return journalActeFacade.trouverJournalParDossier(dossier);
    }
    
    
}
