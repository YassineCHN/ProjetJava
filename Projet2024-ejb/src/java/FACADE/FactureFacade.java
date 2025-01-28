/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.Z_PATIENT;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public void validerFacturePaiement(Facture facture) {
        if(facture!= null) {
            facture.setFacturePayee(true);
            em.merge(facture);
        }
        else {
            System.out.println("FactureFacade.java");
            System.out.println("modification de l'attribut FacturePayee a échoué");
            System.out.println("l'input facture est null");
        }
    }

    @Override
    public List<Facture> trouverFacturesPatient(Z_PATIENT patient) {
//        
        List<Facture> factures = null;

        if (patient==null ){
            return factures;
        } else {
            factures = em.createQuery("SELECT j FROM Facture j WHERE j.leDossier.lePatient = :patient", Facture.class).setParameter("patient", patient).getResultList();
        return factures;
        }

         
      
    }

    @Override
    public List<Facture> trouverFacturesNonPayeesAvecEmissionDepassee() {
        
        
        Calendar calendar = Calendar.getInstance();
//        1 mois de retard ou 30 jours, pas exactement 1 mois 
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        Date dateLimite = calendar.getTime();
        return em.createQuery("SELECT f FROM Facture f WHERE f.factureDateEmissions <= :dateLimite AND f.facturePayee = false",Facture.class).setParameter("dateLimite", dateLimite).getResultList();
    }
    
    
}
