/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Z_MEDECIN;
import ENTITE.Z_USER;
import javax.ejb.Local;
import java.util.List;
/**
 *
 * @author charl
 */
@Local
public interface Z_USER_BEANLocal {


    Z_USER Z_authentificationUtilisateurAdmin(String login, String mdp);
//    Object[] Z_authentificationUtilisateur(String login, String mdp);
    Z_USER Z_authentificationUtilisateur(String login, String mdp);

//    Z_USER Z_authentificationUtilisateur2(String login, String mdp);

    List<Z_USER> trouverTousLesUtilisateurs();

    List<Z_MEDECIN> trouverTousLesUtilisateursMedecins();
}
