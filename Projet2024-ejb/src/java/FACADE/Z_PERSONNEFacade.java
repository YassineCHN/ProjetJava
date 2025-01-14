/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Service;
import ENTITE.Z_ADMIN;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_PERSONNEL;
import ENTITE.Z_PERSONNE;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ychen
 */
@Stateless
public class Z_PERSONNEFacade extends AbstractFacade<Z_PERSONNE> implements Z_PERSONNEFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Z_PERSONNEFacade() {
        super(Z_PERSONNE.class);
    }
    
    public void creerPersonne(String nom, String prenom,String adresse) {
        Z_PERSONNE pers = new Z_PERSONNE();
        pers.setNomPersonne(nom);
        pers.setPrenomPersonne(prenom);
        pers.setAdresse(adresse);
        getEntityManager().persist(pers);
    }
    

    public void mettreAJourPersonne(Z_PERSONNE pers) {
         getEntityManager().merge(pers);
    }

    public void supprimerPersonne(Long id) {
    try {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("ID invalide : L'ID ne peut pas être null ou égal à 0.");
        }
        Z_PERSONNE pers = em.find(Z_PERSONNE.class, id);
        if (pers != null) {
            em.remove(pers);
            System.out.println("La personne avec l'ID " + id + " a été supprimée.");
        } else {
            System.err.println("Aucune personne trouvée avec l'ID " + id);
        }
    } catch (IllegalArgumentException e) {
        System.err.println("Erreur : " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Une erreur s'est produite lors de la suppression de la personne : " + e.getMessage());
    }
}

    
    public Z_PERSONNE trouverPersonneParId(Long id) {
        Z_PERSONNE pers = null;
        String txt = "SELECT u from Z_PERSONNE as u where u.idpers=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable",id);
        List<Z_PERSONNE> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if (result.size()==1){
            pers = result.get(0);
            return pers;
        }
        return pers;
    }

    
    public List<Z_PERSONNE> trouverToutesLesPersonnes() {
        return em.createQuery("SELECT s FROM Z_PERSONNE s", Z_PERSONNE.class).getResultList();
    }

    
    public List<Z_MEDECIN> trouverTousLesMedecins() {
        return em.createQuery("SELECT s FROM Z_MEDECIN as s where s.idpers IS NOT NULL", Z_MEDECIN.class).getResultList();
    }
//pas besoin de préciser le rôle
//    c'est automatique dans la stratégie single_table
//    le champ propre à médecin/admin sera rempli automatiquement

    
//    @Override
//    public void creerMedecin(String login, String mdp, String specialite) {
//        System.out.println("appel de la méthode creerMedecin");
//        Z_MEDECIN pers = new Z_MEDECIN();
//        pers.setLogin(login);
//        pers.setMdp(mdp);
//        pers.setSpecialite(specialite);
//        getEntityManager().persist(pers);
//    }
public void creerMedecin(String nom, String prenom, String adresse, String specialite) {
    System.out.println("appel de la méthode creerMedecin");
    Z_MEDECIN pers = new Z_MEDECIN();
        pers.setNomPersonne(nom);
        pers.setPrenomPersonne(prenom);
        pers.setAdresse(adresse);
        pers.setSpecialite(specialite);

    try {
        getEntityManager().persist(pers);
        System.out.println("Personne médecin créé avec succès : " + pers.getNomPersonne());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de la personne médecin : " + e.getMessage());
    }
}

    public List<Z_PATIENT> trouverTousLesPatients() {
        return em.createQuery("SELECT s FROM Z_PATIENT as s where s.idpers IS NOT NULL", Z_PATIENT.class).getResultList();
    }

    public void creerPatient(String nom, String prenom,String adresse, String numSecuSoc,String nomMutuelle, String adresseMutuelle) {
          System.out.println("appel de la méthode creerPatient");
    Z_PATIENT pers = new Z_PATIENT();
        pers.setNomPersonne(nom);
        pers.setPrenomPersonne(prenom);
        pers.setAdresse(adresse);
        pers.setNumSecuSoc(numSecuSoc);
        pers.setNomMutuelle(nomMutuelle);
        pers.setAdresseMutuelle(adresseMutuelle);
        try {
        getEntityManager().persist(pers);
        System.out.println("Personne PATIENT créé avec succès : " + pers.getNomPersonne());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de la personne PATIENT : " + e.getMessage());
    }
    }

    public Z_PATIENT trouverPatientParNumSecu(String numSecu) {
        Z_PATIENT pers = null;
        String txt = "SELECT u from Z_PATIENT as u where u.numSecuSoc=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable",numSecu);
        List<Z_PATIENT> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if (result.size()==1){
            pers = result.get(0);
            return pers;
        }
        return pers;
    }
    
    
    public void creerPersonnel(String nom, String prenom, String adresse, Service service) {
          System.out.println("appel de la méthode creerPersonnel");
    Z_PERSONNEL pers = new Z_PERSONNEL();
        pers.setNomPersonne(nom);
        pers.setPrenomPersonne(prenom);
        pers.setAdresse(adresse);
        pers.setService(service);
        try {
        getEntityManager().persist(pers);
        System.out.println("Personne PERSONNEL créé avec succès : " + pers.getNomPersonne());
    } catch (Exception e) {
        System.err.println("Erreur lors de la création de lapersonne Personnel : " + e.getMessage());
    }
    }
    
    public List<Z_PERSONNEL> trouverTousLesPersonnels() {
        return em.createQuery("SELECT s FROM Z_PERSONNEL as p where p.id IS NOT NULL", Z_PERSONNEL.class).getResultList();
    }
}
