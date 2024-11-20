/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.RolesUtilisateurs;
import ENTITE.Utilisateur;
import FACADE.ServiceFacadeLocal;
import FACADE.UtilisateurFacadeLocal;
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

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    
    
//    @Override
//    public void CreerService(String login, String mdp,String nomService, String localisationService) {
//        Utilisateur user = utilisateurFacade.authentification(login, mdp);
//        if(user!=null && user.getUtilisateurRole() == RolesUtilisateurs.ADMIN) {
//            serviceFacade.creerService(nomService, localisationService);
//        }
//        else {
//            System.out.println("utilisateur pas admin ou inexistant");
//        }
//            
//    }
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

//    @Override
//    public void SupprimerService(String login, String mdp, Long idService) {
//        Utilisateur user = utilisateurFacade.authentification(login, mdp);
//        if(user!=null && user.getUtilisateurRole()==RolesUtilisateurs.ADMIN) {
//            serviceFacade.supprimerService(idService);
//        }
//    }
    
    
}
