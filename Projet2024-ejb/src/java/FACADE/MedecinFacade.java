/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Medecin;
import ENTITE.RolesUtilisateurs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class MedecinFacade extends AbstractFacade<Medecin> implements MedecinFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedecinFacade() {
        super(Medecin.class);
    }

    @Override
    public void creerMedecin(String login, String motDePasse, RolesUtilisateurs role,String nom, String specialite) {
        Medecin medecin = new Medecin();
        medecin.setUtilisateurLogin(login);
        medecin.setUtilisateurMDP(specialite);
//        medecin.setUtilisateurRole(role);
        medecin.setMedecinNom(nom);
        medecin.setMedecinSpecialite(specialite);
        em.persist(medecin);
    }

    @Override
    public void modifierMedecin(String login, String motDePasse, RolesUtilisateurs role,Long id, String nom, String specialite) {
        Medecin medecin = em.find(Medecin.class, id);
        if (medecin != null) {
            medecin.setUtilisateurLogin(login);
            medecin.setUtilisateurMDP(specialite);
//            medecin.setUtilisateurRole(role);
            medecin.setMedecinNom(nom);
            medecin.setMedecinSpecialite(specialite);
            em.merge(medecin);
        }
    }

    @Override
    public void supprimerMedecin(Long id) {
        Medecin medecin = em.find(Medecin.class, id);
        if (medecin != null) {
            em.remove(medecin);
        }
    }

    @Override
    public Medecin trouverMedecinParNom(String nom) {
        try {
            return em.createQuery("SELECT m FROM Medecin m WHERE m.nom = :nom", Medecin.class).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Medecin> trouverTousLesMedecins() {
        return em.createQuery("SELECT m FROM Medecin m", Medecin.class).getResultList();
    }

    
}
