/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Z_MEDECIN;
import java.util.List;
import ENTITE.Z_USER;
import FACADE.Z_USERFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author charl
 */
@Stateless
public class Z_USER_BEAN implements Z_USER_BEANLocal {

    @EJB
    private Z_USERFacadeLocal z_USERFacade;

    @Override
    public Z_USER Z_authentificationUtilisateurAdmin(String login, String mdp) {
        String test = "ADMIN";
        Z_USER user = z_USERFacade.authentification(login, mdp);
        if (user != null && user.getRole().equals(test.toUpperCase())) {
            return user;
        } else {
            return null;
        }
    }
    // Nouvelle méthode pour simplement retourner l'utilisateur
//    @Override
//    public Object[] Z_authentificationUtilisateur(String login, String mdp) {
//        Z_USER user = z_USERFacade.authentification(login, mdp);
//        String role = null;
//        if (user != null) {
//            role = user.getRole(); 
//            return new Object[]{user, role};
//
//        } else {
//            return null;
//        }
//    }

    @Override
    public Z_USER Z_authentificationUtilisateur(String login, String mdp) {
        Z_USER user = z_USERFacade.authentification(login, mdp);
        if (user != null) {
            System.out.println("AUCUN RESULTAT PAR LA RECHERCHE ");
//            putain la folie, j'avais écrit "if (user != null)"
//          alors return null
            return user;

        } else {
            System.out.println("OUI RESULTAT ");
            return null;
        }

    }

//    @Override
//    public Object[] Z_authentificationUtilisateur(String login, String mdp) {
//        Z_USER user = z_USERFacade.authentification(login, mdp);
//        String role = null;
//        if (user != null) {
//            role = user.getRole(); 
//            return new Object[]{user, role};
//
//        } else {
//            return null;
//        }
//    }

    @Override
    public List<Z_USER> trouverTousLesUtilisateurs() {
        return z_USERFacade.trouverTousLesUtilisateurs();
    }

    @Override
    public List<Z_MEDECIN> trouverTousLesUtilisateursMedecins() {
        return z_USERFacade.trouverTousLesUtilisateursMedecin();
    }
    
    
    
    
    
    
    
}
