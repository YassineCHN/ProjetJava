/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import java.util.List;
import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.Service;
import ENTITE.Z_PATIENT;
import ENTITE.Z_USER;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionDossierHospitalisationLocal {

    List<DossierHospitalisation> afficherDossier();

    DossierHospitalisation trouverDossierParId(Long id);

    void supprimerDossier(Long id);
    
    void annulerDossierHospitalisation(Long id) ;

    void creerDossier(Z_PATIENT patient, Service service, Date dateHospitalisation, Date heureArrivee, Date heureDepart);

    DossierHospitalisation trouverDossierParPatient(Z_USER user);

    

    

    
}
