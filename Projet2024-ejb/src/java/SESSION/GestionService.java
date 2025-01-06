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
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
public void SupprimerService(Long idService) {
    try {
        if (idService == null) {
            throw new IllegalArgumentException("L'identifiant du service ne peut pas être null.");
        }
        serviceFacade.supprimerService(idService);
    } catch (IllegalArgumentException e) {
        // Gérer l'exception lorsque idService est null
        System.err.println("Erreur : " + e.getMessage());
    } catch (Exception e) {
        // Gérer toute autre exception
        System.err.println("Une erreur s'est produite lors de la suppression du service : " + e.getMessage());
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
