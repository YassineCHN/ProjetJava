/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.JournalActe;
import ENTITE.Z_USER;
import javax.ejb.Local;


/**
 *
 * @author charl
 */
@Local
public interface GestionJournalActeLocal {
    


    JournalActe creerJournal(DossierHospitalisation dossier, Z_USER user);

    JournalActe trouverJournalParId(Long id);
    
}
