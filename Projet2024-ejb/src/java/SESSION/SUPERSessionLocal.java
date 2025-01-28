/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import javax.ejb.Local;

/**
 *
 * @author ychen
 */
@Local
public interface SUPERSessionLocal {
    Z_USER Z_authentificationUtilisateurAdmin(String login, String mdp);

    Z_USER Z_authentificationUtilisateur(String login, String mdp);
    
    public Z_USER trouverUserParLogin(String login);
    
    Z_USER trouverUtilisateurParId(Long id);
    
    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public Z_USER trouverUtilisateurParPers(Long id);
    
    public void modifierPersonne(Z_PERSONNE pers);
}
