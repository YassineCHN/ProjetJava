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
    public void creerActe(String nom, String description, double prix, double coefSecu, double coefMutuelle) {
        Acte acte = new Acte();
        acte.setActeNom(nom);
        acte.setActeDescription(description);
        acte.setActePrix(prix);
        acte.setCoefficient_SecuriteSociale(coefSecu);
        acte.setCoefficient_Mutuelle(coefMutuelle);
        em.persist(acte);
    }

    @Override
    public void modifierActe(Acte acte) {
        getEntityManager().merge(acte);
    }

    @Override
    public void supprimerActe(Long id) {
        try {
        if (id == null) {
            throw new IllegalArgumentException("L'identifiant du service ne peut pas être null.");
        }
        Acte acte = em.find(Acte.class, id);
        if (acte != null) {
            em.remove(acte);
        }
    } catch (IllegalArgumentException e) {
        // Gérer l'exception lorsque id est null
        System.err.println("Erreur : " + e.getMessage());
    } catch (Exception e) {
        // Gérer toute autre exception
        System.err.println("Une erreur s'est produite lors de la suppression de acte : " + e.getMessage());
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

    @Override
    public Acte trouverActeParId(long id) {
        try {
            return em.createQuery("SELECT a FROM Acte a WHERE a.id = :nom", Acte.class).setParameter("nom", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
