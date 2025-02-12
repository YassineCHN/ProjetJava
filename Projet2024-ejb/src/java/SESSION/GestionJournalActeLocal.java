/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.JournalActe;
import ENTITE.Z_USER;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author charl
 */
@Local
public interface GestionJournalActeLocal {
    


    JournalActe creerJournal(DossierHospitalisation dossier);

    JournalActe trouverJournalParId(Long id);

    List<JournalActe> trouverTousLesJournaux();

    JournalActe trouverJournalParDossier(DossierHospitalisation dossier);

    void validerJournal(JournalActe journal);
    
}
