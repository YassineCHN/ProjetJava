/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import FACADE.ActeFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author charl
 */
@Stateless
public class GestionActe implements GestionActeLocal {

    @EJB
    private ActeFacadeLocal acteFacade;

    @Override
    public List<Acte> trouverTousLesActes() {
        List<Acte> result = acteFacade.trouverTousLesActes();
        return result;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Acte trouverActeParId(long id) {
        Acte result = acteFacade.trouverActeParId(id);
        return result;
    }

    @Override
    public void supprimerActe(Long id) {
        acteFacade.supprimerActe(id);
    }

    @Override
    public void creerActe(String nom, String description, double prix) {
        acteFacade.creerActe(nom, description, prix);
    }
    
    @Override
    public void modifierActe(Acte acte) {
        acteFacade.modifierActe(acte);
    }
    
}
