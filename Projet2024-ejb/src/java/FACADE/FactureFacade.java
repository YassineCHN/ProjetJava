/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.Z_USER;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class FactureFacade extends AbstractFacade<Facture> implements FactureFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactureFacade() {
        super(Facture.class);
    }

    @Override
    public Facture creerFacturePourJournal(Date factureDateEmissions, Double montant, boolean statutFacture, DossierHospitalisation leDossier, JournalActe journal) {
        
        Facture facture = new Facture();
        facture.setFactureDateEmissions(factureDateEmissions);
        facture.setFactureMontant(montant);
        facture.setFacturePayee(statutFacture);
        facture.setLeDossier(leDossier);
        facture.setLeJournal(journal);
        em.persist(facture);
        return facture;
    }

    @Override
    public List<Facture> trouverToutesFactures() {
        return em.createQuery("SELECT a FROM Facture a", Facture.class).getResultList();
    }

    @Override
    public Facture trouverFactureParDossier(DossierHospitalisation dossier) {
        if (dossier == null) {
        return null;
    }
    try {

        Facture result = em.createQuery("SELECT j FROM Facture j WHERE j.leDossier = :dossier", Facture.class).setParameter("dossier", dossier).getSingleResult();
        
        
        return  result;
    } catch (NoResultException e) {
        return null; // aucune entité trouvée pour ce dossier
    } catch (Exception e) {
        e.printStackTrace();
        return null; // gestion basique des autres exceptions
    }
    
    }

    @Override
    public Facture trouverFactureParID(Long id) {
        try {
            return em.createQuery("SELECT s FROM Facture s WHERE s.id = :variable", Facture.class).setParameter("variable", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

//    @Override
//    public Facture trouverFactureParPatient(DossierHospitalisation dossier) {
//        try {
//            return em.createQuery("SELECT s FROM Facture s WHERE s.leDossier = :variable", Facture.class).setParameter("variable", dossier).getSingleResult();
//        } catch (Exception e) {
//            return null;
//        }
//    }
    
    
}
