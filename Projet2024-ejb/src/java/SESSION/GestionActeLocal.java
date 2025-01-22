/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionActeLocal {

    List<Acte> trouverTousLesActes();

    Acte trouverActeParId(long id);

    boolean supprimerActe(Long id);

    void creerActe(String nom, String description, double prix,double coefSecu, double coefMutuelle);
    
    void modifierActe(Acte acte);
    
}
