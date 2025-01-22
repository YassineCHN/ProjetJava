/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;


import ENTITE.Service;

import FACADE.ServiceFacadeLocal;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author charl
 */
@Stateless
public class GestionService implements GestionServiceLocal {

    @EJB
    private ServiceFacadeLocal serviceFacade;

    
    
    
    @Override
    public void CreerService(String nomService, String localisationService) {
       
            serviceFacade.creerService(nomService, localisationService);
       
    }
    @Override
    public void modifierService(Service serv){
        serviceFacade.modifierService(serv);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean SupprimerService(Long idService) {
    Service serv=serviceFacade.trouverServiceParId(idService);
        if (serv != null && 
            serv.getDossierHospitalisations().isEmpty() &&
            serv.getLesMedecins().isEmpty() &&
            serv.getLesPersonnels().isEmpty() )
        {
            serviceFacade.supprimerService(serv);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public List<Service> tousLesServices() {
        List<Service> result = serviceFacade.trouverTousLesServices();
        return result;
    }

    @Override
    public Service trouverServiceParID(Long id) {
        Service test = serviceFacade.trouverServiceParId(id);
        return test;
    }
    
    
    
}
