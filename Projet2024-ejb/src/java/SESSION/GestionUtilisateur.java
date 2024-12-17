/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.RolesUtilisateurs;
import ENTITE.Utilisateur;
import FACADE.UtilisateurFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class GestionUtilisateur implements GestionUtilisateurLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    // Méthode d'authentification
    @Override
    public Utilisateur authentificationUtilisateurAdmin(String login, String mdp) {
        String test = "ADMIN";
        Utilisateur user = utilisateurFacade.authentification(login, mdp);
        if (user != null && user.getUtilisateurRole()== RolesUtilisateurs.valueOf(test.toUpperCase()) ) {
//            Il semblerait que la ligne c-dessus ne marche pas pour vérifier la valeur de l'enum, ça retournait constamment un NULL
//            if (user != null && user.getRole().equals(RolesUtilisateurs.ADMIN.toString()) ) { 
            return user;
        } else {
            return null;
        }
    }
    
    

    // Méthode pour créer un nouvel utilisateur
    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {
        utilisateurFacade.creerUtilisateur(utilisateur);
    }

    // Méthode pour mettre à jour un utilisateur
    @Override
    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        utilisateurFacade.mettreAJourUtilisateur(utilisateur);
    }

    // Méthode pour supprimer un utilisateur
    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        utilisateurFacade.supprimerUtilisateur(utilisateur);
    }

    // Méthode pour trouver un utilisateur par son ID
    @Override
    public Utilisateur trouverUtilisateurParId(Long id) {
        return utilisateurFacade.trouverUtilisateurParId(id);
    }

    // Méthode pour obtenir une liste de tous les utilisateurs
    @Override
    public List<Utilisateur> trouverTousLesUtilisateurs() {
        return utilisateurFacade.trouverTousLesUtilisateurs();
    }

    @Override
    public Object[] authentificationUtilisateur(String login, String mdp) {
        
        // j'utilise un object plutôt qu'un Utilisateur parce que j'avais des problèmes pour manipuler le rôle de l'user dans la servlet
        // avec ça c'est plus simple
        Utilisateur user = utilisateurFacade.authentification(login, mdp);
        RolesUtilisateurs role =null;
        
        
        if(user != null){
            role = user.getUtilisateurRole();
            return new Object[]{user,role};
        }else {
            return null;
        }
        
        
        
        
    }
}
