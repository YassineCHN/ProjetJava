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
    public void modifierService(Service serv) {
        getEntityManager().merge(serv);
    }

    @Override
    public void supprimerService(Service servSupp) {
            em.remove(servSupp);
    }


    @Override
    public Service trouverServiceParId(Long id) {
        try {
            return em.createQuery("SELECT s FROM Service s WHERE s.id = :variable", Service.class).setParameter("variable", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Service> trouverTousLesServices() {
        return em.createQuery("SELECT s FROM Service s", Service.class).getResultList();
    }
    



    
     
}
