/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Service;
import ENTITE.Z_ADMIN;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNEL;
import ENTITE.Z_USER;
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
    public void creerUtilisateur(String login, String mdp) {
        Z_USER user = new Z_USER();
        user.setLogin(login);
        user.setMdp(mdp);
        
        getEntityManager().persist(user);
    }
    

    @Override
    public void mettreAJourUtilisateur(Z_USER user) {
         getEntityManager().merge(user);
    }

    @Override
    public void supprimerUtilisateur(long id_test) {
        try {
        if (id_test == 0) {
            throw new IllegalArgumentException("ID invalide");
        }
        Z_USER user = em.find(Z_USER.class, id_test);
        if (user != null) {
            em.remove(user);
        }
    } catch (IllegalArgumentException e) {
        // Gérer l'exception lorsque id est null
        System.err.println("Erreur : " + e.getMessage());
    } catch (Exception e) {
        // Gérer toute autre exception
        System.err.println("Une erreur s'est produite lors de la suppression de utilisateur : " + e.getMessage());
    }
    }

    @Override
    public Z_USER trouverUtilisateurParId(long id) {
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

    @Override
    public List<Z_USER> trouverTousLesUtilisateurs() {
        return em.createQuery("SELECT s FROM Z_USER s", Z_USER.class).getResultList();
    }

    @Override
    public List<Z_MEDECIN> trouverTousLesUtilisateursMedecin() {
        return em.createQuery("SELECT s FROM Z_MEDECIN as s where s.id IS NOT NULL", Z_MEDECIN.class).getResultList();
    }
//pas besoin de préciser le rôle
//    c'est automatique dans la stratégie single_table
//    le champ propre à médecin/admin sera rempli automatiquement

    
//    @Override
//    public void creerMedecin(String login, String mdp, String specialite) {
//        System.out.println("appel de la méthode creerMedecin");
//        Z_MEDECIN user = new Z_MEDECIN();
//        user.setLogin(login);
//        user.setMdp(mdp);
//        user.setSpecialite(specialite);
//        getEntityManager().persist(user);
//    }
    @Override
public void creerMedecin(String login, String mdp, String specialite) {
    System.out.println("appel de la méthode creerMedecin");
    Z_MEDECIN user = new Z_MEDECIN();
    user.setLogin(login);
    user.setMdp(mdp);
    user.setSpecialite(specialite);

    try {
        getEntityManager().persist(user);
        System.out.println("Utilisateur médecin créé avec succès : " + user.getLogin());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de l'utilisateur médecin : " + e.getMessage());
    }
}


    @Override
    public void creerAdmin(String login, String mdp, String adminStatus) {
        System.out.println("appel de la méthode creerAdmin");
    Z_ADMIN user = new Z_ADMIN();
        user.setLogin(login);
        user.setMdp(mdp);
        user.setADMIN_STATUS(adminStatus);
        try {
        getEntityManager().persist(user);
        System.out.println("Utilisateur ADMIN créé avec succès : " + user.getLogin());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de l'utilisateur ADMIN : " + e.getMessage());
    }
    
    }

    @Override
    public List<Z_PATIENT> trouverTousLesUtilisateursPatients() {
        return em.createQuery("SELECT s FROM Z_PATIENT as s where s.id IS NOT NULL", Z_PATIENT.class).getResultList();
    }

    @Override
    public void creerPatient(String login, String mdp, String numSecuSoc) {
          System.out.println("appel de la méthode creerPatient");
    Z_PATIENT user = new Z_PATIENT();
        user.setLogin(login);
        user.setMdp(mdp);
        user.setNumSecuSoc(numSecuSoc);
        try {
        getEntityManager().persist(user);
        System.out.println("Utilisateur PATIENT créé avec succès : " + user.getLogin());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de l'utilisateur PATIENT : " + e.getMessage());
    }
    }

    @Override
    public Z_PATIENT trouverPatientParNumSecu(String numSecu) {
        Z_PATIENT user = null;
        String txt = "SELECT u from Z_PATIENT as u where u.numSecuSoc=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable",numSecu);
        List<Z_PATIENT> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if (result.size()==1){
            user = result.get(0);
            return user;
        }
        return user;
    }
    
    
    public void creerPersonnel(String login, String mdp, Service service) {
          System.out.println("appel de la méthode creerPersonnel");
    Z_PERSONNEL user = new Z_PERSONNEL();
        user.setLogin(login);
        user.setMdp(mdp);
        user.setService(service);
        try {
        getEntityManager().persist(user);
        System.out.println("Utilisateur PERSONNEL créé avec succès : " + user.getLogin());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de l'utilisateur Personnel : " + e.getMessage());
    }
    }
    
    public List<Z_PERSONNEL> trouverTousLesUtilisateursPersonnel() {
        return em.createQuery("SELECT s FROM Z_PERSONNEL as p where p.id IS NOT NULL", Z_PERSONNEL.class).getResultList();
    }
    
    
}
