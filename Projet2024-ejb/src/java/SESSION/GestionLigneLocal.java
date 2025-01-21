/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.Z_MEDECIN;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionLigneLocal {

    void creerLigne(Date date_acte, int quantite, String commentaire, Acte acte, JournalActe journal, Z_MEDECIN leMedecin);

    List<LigneJournal> trouverToutesLignes();

    void supprimerLigne(long id);

    List<LigneJournal> listerLignesParJournal(Long idJournal);

    LigneJournal trouverLigneParId(Long id);

    void mettreAJourLigne(LigneJournal ligne);
    
}
