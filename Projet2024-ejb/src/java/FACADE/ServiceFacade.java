/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Service;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class ServiceFacade extends AbstractFacade<Service> implements ServiceFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceFacade() {
        super(Service.class);
    }
    
    @Override
    public void creerService(String nom, String localisation) {
        Service service = new Service();
        service.setServiceNom(nom);
        service.setServiceLocalisation(localisation);
        em.persist(service);
    }

    @Override
    public void modifierService(Long id, String nom, String localisation) {
        Service service = em.find(Service.class, id);
        if (service != null) {
            service.setServiceNom(nom);
            service.setServiceLocalisation(localisation);
            em.merge(service);
        }
    }

    @Override
    public void supprimerService(Long id) {
        Service service = em.find(Service.class, id);
        if (service != null) {
            em.remove(service);
        }
    }

    @Override
    public Service trouverServiceParNom(String nom) {
        try {
            return em.createQuery("SELECT s FROM Service s WHERE s.nom = :variable", Service.class).setParameter("variable", nom).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Service> trouverTousLesServices() {
        return em.createQuery("SELECT s FROM Service s", Service.class).getResultList();
    }
    
    
    
    
    
}
