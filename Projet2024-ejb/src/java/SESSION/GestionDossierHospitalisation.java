/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Service;
import ENTITE.Z_PATIENT;
import ENTITE.statutDossier;
import FACADE.DossierHospitalisationFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author charl
 */
@Stateless
public class GestionDossierHospitalisation implements GestionDossierHospitalisationLocal {

    @EJB
    private DossierHospitalisationFacadeLocal dossierHospitalisationFacade;

    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<DossierHospitalisation> afficherDossier() {
        List<DossierHospitalisation> result = dossierHospitalisationFacade.trouverTousLesDossiers();
        System.out.println(result.toString());
        return result;
    }

    @Override
    public DossierHospitalisation trouverDossierParId(Long id) {
        DossierHospitalisation dossier = dossierHospitalisationFacade.trouverDossierHospitalisationParId(id);
        return dossier;
    }


    @Override
    public void annulerDossierHospitalisation(Long id) {
        dossierHospitalisationFacade.annulerDossierHospitalisation(id);
    }
    @Override
    public void creerDossier(Z_PATIENT patient, Service service, Date dateHospitalisation, Date heureArrivee, Date heureDepart) {
        dossierHospitalisationFacade.creerDossierHospitalisation(patient, service ,dateHospitalisation,heureArrivee,heureDepart );
    }

    @Override
    public List<DossierHospitalisation> trouverTousLesDossiersUnPatient(Z_PATIENT patient){
        return dossierHospitalisationFacade.trouverTousLesDossiersUnPatient(patient);
    }
    
    @Override
    public List<DossierHospitalisation> trouverTousLesDossiersUnService(Service service){
      return dossierHospitalisationFacade.trouverTousLesDossiersUnService(service);
    }
    
    @Override
    public void modifierDossier(DossierHospitalisation dossier) {
        dossierHospitalisationFacade.modifierDossier(dossier);
    }
    
    
}
