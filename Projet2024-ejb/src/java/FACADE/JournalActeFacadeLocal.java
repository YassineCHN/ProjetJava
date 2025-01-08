/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

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
public interface JournalActeFacadeLocal {

    void create(JournalActe journalActe);

    void edit(JournalActe journalActe);

    void remove(JournalActe journalActe);

    JournalActe find(Object id);

    List<JournalActe> findAll();

    List<JournalActe> findRange(int[] range);

    int count();

    JournalActe creerJournal(DossierHospitalisation dossier, Z_USER user);

    JournalActe trouverJournalParId(Long id);

    List<JournalActe> trouverTousLesJournaux();
    

    
    
    
    
}
