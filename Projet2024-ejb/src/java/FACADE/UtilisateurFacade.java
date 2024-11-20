/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author charl
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
     // Méthode d'authentification
    @Override
    public Utilisateur authentification(String login, String mdp) {
//        try {
//            Utilisateur utilisateur = em.createQuery("SELECT u FROM Utilisateur as u WHERE u.login = :login AND u.mdp = :mdp", Utilisateur.class)
//                                        .setParameter("login", login)
//                                        .setParameter("mdp", mdp)
//                                        .getSingleResult();
//            return utilisateur;
//        } catch (Exception e) {
//            return null;
//        }
        Utilisateur user_returned = null;
        String txt = "SELECT u FROM Utilisateur as u WHERE u.utilisateurLogin = :login AND u.utilisateurMDP = :mdp";
        Query req = em.createQuery(txt);
        req.setParameter("login", login);
        req.setParameter("mdp", mdp);
        List<Utilisateur> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if(result.size()==1) {
            user_returned = result.get(0);
            return user_returned;
        }
        
        
        return user_returned;
    }

    // Méthode pour créer un nouvel utilisateur
    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {
        getEntityManager().persist(utilisateur);
    }

    // Méthode pour mettre à jour un utilisateur
    @Override
    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        getEntityManager().merge(utilisateur);
    }

    // Méthode pour supprimer un utilisateur
    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        getEntityManager().remove(getEntityManager().merge(utilisateur));
    }

    // Méthode pour trouver un utilisateur par son ID
    @Override
    public Utilisateur trouverUtilisateurParId(Long id) {
        Utilisateur u = null;
//        Dans la requête il faut que l'input soit du même type que la donnée (u.id est "long" donc variable doit être "long)
        String txt = "SELECT u from Utilisateur as u where u.id=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable",id);
//        return getEntityManager().find(Utilisateur.class, id);
        List<Utilisateur> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if(result.size()==1) {
            u = result.get(0);
            return u;
        }
        return u;

        
    }

    // Méthode pour obtenir une liste de tous les utilisateurs
    @Override
    public List<Utilisateur> trouverTousLesUtilisateurs() {
        return getEntityManager().createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
    }
}
