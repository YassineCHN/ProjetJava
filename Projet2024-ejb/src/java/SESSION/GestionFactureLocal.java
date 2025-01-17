/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionFactureLocal {

    Facture creerFacturePourJournal(Long idJournal);

    List<Facture> trouverToutesFactures();

    Facture trouverFactureParDossier(DossierHospitalisation dossier);

    Facture trouverFactureParID(Long id);

    void validerFacturePaiement(Facture facture);
    
}
