/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Service;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionServiceLocal {

    void CreerService(String login, String mdp,String nomService, String localisationService) ;

    void SupprimerService(Long idService);

    List<Service> tousLesServices();

    Service trouverServiceParID(Long id);
    
}
