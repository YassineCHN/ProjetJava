/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface UtilisateurFacadeLocal {

    void create(Utilisateur utilisateur);

    void edit(Utilisateur utilisateur);

    void remove(Utilisateur utilisateur);

    Utilisateur find(Object id);

    List<Utilisateur> findAll();

    List<Utilisateur> findRange(int[] range);

    int count();
    
    Utilisateur authentification(String login, String mdp);
    void mettreAJourUtilisateur(Utilisateur utilisateur) ;
    void creerUtilisateur(Utilisateur utilisateur) ;
    void supprimerUtilisateur(Utilisateur utilisateur) ;
    Utilisateur trouverUtilisateurParId(Long id) ;
    List<Utilisateur> trouverTousLesUtilisateurs() ;
}
