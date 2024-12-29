/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import FACADE.DossierHospitalisationFacadeLocal;
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

    
    
}
