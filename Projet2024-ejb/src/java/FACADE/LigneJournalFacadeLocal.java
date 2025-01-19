/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Acte;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface LigneJournalFacadeLocal {

    void create(LigneJournal ligneJournal);

    void edit(LigneJournal ligneJournal);

    void remove(LigneJournal ligneJournal);

    LigneJournal find(Object id);

    List<LigneJournal> findAll();

    List<LigneJournal> findRange(int[] range);

    int count();

    void creerLigneJournal(Date date_acte, int quantite, String commentaire, Acte acte, JournalActe journal);

    void supprimerLigne(long id);

    LigneJournal trouverLigneParId(long id);

    List<LigneJournal> trouverToutesLignes();

    List<LigneJournal> listerLignesParJournal(Long idJournal);

    void mettreAJourLigne(LigneJournal ligne);
    
}
