/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.RoleUSER;
import ENTITE.Service;
import ENTITE.Z_ADMIN;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_PERSONNEL;
import ENTITE.Z_USER;
import java.util.List;
import javax.ejb.ApplicationException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author charl
 */
@Stateless
@ApplicationException(rollback = false)
public class Z_USERFacade extends AbstractFacade<Z_USER> implements Z_USERFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Z_USERFacade() {
        super(Z_USER.class);
    }

    @Override
    public Z_USER authentification(String login, String mdp) {
        Z_USER user_returned = null;
        String txt = "SELECT u FROM Z_USER u WHERE u.login = :login AND u.mdp = :mdp";
        Query req = em.createQuery(txt);
        req.setParameter("login", login);
        req.setParameter("mdp", mdp);
        List<Z_USER> result = req.getResultList();
        
        if (result == null || result.isEmpty()) {
             System.out.println("LA FACADE NA PAS TROUVE DE USER");
             
            return null;
           
        } else {
            user_returned = result.get(0);
             System.out.println("LA FACADE A BIEN TROUVE UN USER");
             System.out.println(user_returned.getLogin());
             System.out.println(user_returned.getMdp());
            return user_returned;
        }
    }

    @Override
    public boolean creerUtilisateur(String login, String mdp,RoleUSER role, Z_PERSONNE personne) {
        String txt = "SELECT u FROM Z_USER u WHERE u.login = :login";
        Query req = em.createQuery(txt);
        req.setParameter("login", login);
        List<Z_USER> result = req.getResultList();
        if (result != null && !result.isEmpty()) {
            return false;
        }
        Z_USER user = new Z_USER();
        user.setLogin(login);
        user.setMdp(mdp);
        user.setRole(role);
        user.setPersonne(personne);
        
        getEntityManager().persist(user);
        return true;
    }
    

    @Override
    public void mettreAJourUtilisateur(Z_USER user) {
         getEntityManager().merge(user);
    }

    @Override
    public void supprimerUtilisateur(Z_USER userSupp) {
        if (userSupp.getPersonne() != null) {
                userSupp.setPersonne(null);
            }
        em.remove(userSupp);
    }

    @Override
    public Z_USER trouverUtilisateurParId(Long id) {
        Z_USER user = null;
        String txt = "SELECT u from Z_USER as u where u.id=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable",id);
        List<Z_USER> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if (result.size()==1){
            user = result.get(0);
            return user;
        }
        return user;
    }
    
        public Z_USER trouverUtilisateurParPersonne(Long id) {
        Z_USER user = null;
        String txt = "SELECT u from Z_USER as u where u.personne.idpers=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable",id);
        List<Z_USER> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if (result.size()==1){
            user = result.get(0);
            return user;
        }
        return user;
    }

    @Override
    public List<Z_USER> trouverTousLesUtilisateurs() {
        return em.createQuery("SELECT s FROM Z_USER s", Z_USER.class).getResultList();
    }

//pas besoin de préciser le rôle
//    c'est automatique dans la stratégie single_table
//    le champ propre à médecin/admin sera rempli automatiquement

    
    
    public Z_USER trouverUserParLogin(String login) {
    Z_USER user = null;
        String txt = "SELECT u from Z_USER as u where u.login=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable",login);
        List<Z_USER> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if (result.size()==1){
            user = result.get(0);
            return user;
        }
        return user;
    }

    
    @Override
    public void creerAdmin(String login, String mdp) {
        System.out.println("appel de la méthode creerAdmin");
    Z_ADMIN user = new Z_ADMIN();
        user.setLogin(login);
        user.setMdp(mdp);
        try {
        getEntityManager().persist(user);
        System.out.println("Utilisateur ADMIN créé avec succès : " + user.getLogin());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de l'utilisateur ADMIN : " + e.getMessage());
    }
    
    }
    
}
