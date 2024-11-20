/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.RolesUtilisateurs;
import ENTITE.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionUtilisateurLocal {
List<Utilisateur> trouverTousLesUtilisateurs() ;
    Utilisateur trouverUtilisateurParId(Long id) ;
 void supprimerUtilisateur(Utilisateur utilisateur) ;
    void mettreAJourUtilisateur(Utilisateur utilisateur) ;
    void creerUtilisateur(Utilisateur utilisateur);
    Utilisateur authentificationUtilisateurAdmin(String login, String mdp) ;

    Object[] authentificationUtilisateur(String login, String mdp);
     
    
}
