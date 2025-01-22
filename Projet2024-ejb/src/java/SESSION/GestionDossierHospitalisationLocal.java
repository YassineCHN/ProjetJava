/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import java.util.List;
import ENTITE.DossierHospitalisation;
import ENTITE.Service;
import ENTITE.Z_PATIENT;
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

    
    void annulerDossierHospitalisation(Long id) ;

    void creerDossier(Z_PATIENT patient, Service service, Date dateHospitalisation, Date heureArrivee, Date heureDepart);
    
    public List<DossierHospitalisation> trouverTousLesDossiersUnPatient(Z_PATIENT patient);
    
    public List<DossierHospitalisation> trouverTousLesDossiersUnService(Service service);
    
    public void modifierDossier(DossierHospitalisation dossier) ;

    

    
}
