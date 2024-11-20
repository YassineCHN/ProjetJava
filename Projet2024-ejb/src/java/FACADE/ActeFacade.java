/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Acte;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class ActeFacade extends AbstractFacade<Acte> implements ActeFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActeFacade() {
        super(Acte.class);
    }
    
    @Override
    public void creerActe(String nom, String description, double prix) {
        Acte acte = new Acte();
        acte.setActeNom(nom);
        acte.setActeDescription(description);
        acte.setActePrix(prix);
        em.persist(acte);
    }

    @Override
    public void modifierActe(Long id, String nom, String description, double prix) {
        Acte acte = em.find(Acte.class, id);
        if (acte != null) {
            acte.setActeNom(nom);
            acte.setActeDescription(description);
            acte.setActePrix(prix);
            em.merge(acte);
        }
    }

    @Override
    public void supprimerActe(Long id) {
        Acte acte = em.find(Acte.class, id);
        if (acte != null) {
            em.remove(acte);
        }
    }

    @Override
    public Acte trouverActeParNom(String nom) {
        try {
            return em.createQuery("SELECT a FROM Acte a WHERE a.nom = :nom", Acte.class).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Acte> trouverTousLesActes() {
        return em.createQuery("SELECT a FROM Acte a", Acte.class).getResultList();
    }
    
}
