/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;


import ENTITE.DossierHospitalisation;
import ENTITE.JournalActe;

import ENTITE.Service;
import ENTITE.Z_PATIENT;
import ENTITE.Z_USER;
import ENTITE.statutDossier;
import java.util.ArrayList;
import java.util.Date;
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
public class DossierHospitalisationFacade extends AbstractFacade<DossierHospitalisation> implements DossierHospitalisationFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DossierHospitalisationFacade() {
        super(DossierHospitalisation.class);
    }

    @Override
    public void creerDossierHospitalisation(Z_PATIENT patient, Service service, Date dateHospitalisation, Date heureArrivee, Date heureDepart) {
        DossierHospitalisation dossier = new DossierHospitalisation();
        dossier.setLePatient(patient);
        dossier.setLeService(service);
        dossier.setStatutD(statutDossier.Actif);
//        dossier.setLesActes(actes != null ? actes : new ArrayList<>()); 
        dossier.setDateHospitalisation(dateHospitalisation);
        dossier.setHeureArrivee(heureArrivee);
        dossier.setHeureDepart(heureDepart);
        em.persist(dossier);
    }

    @Override
    public void annulerDossierHospitalisation(Long id) {
        DossierHospitalisation dossier = em.find(DossierHospitalisation.class, id);
        if (dossier != null) {
            dossier.setStatutD(statutDossier.Inactif);
            em.merge(dossier);
        }
    }

    @Override
    public DossierHospitalisation trouverDossierHospitalisationParId(Long id) {
//        return em.find(DossierHospitalisation.class, id);
        DossierHospitalisation dossier = null;
        String txt = "SELECT d from DossierHospitalisation as d where d.id=:variable";
        Query req = em.createQuery(txt);
        req.setParameter("variable", id);
        List<DossierHospitalisation> result = req.getResultList();
        if (result.size()==0 || result==null) {
            return null;
        }
        else if (result.size()==1) {
            dossier = result.get(0);
            return dossier;
        }
        return dossier;

    }

    @Override
    public List<DossierHospitalisation> trouverTousLesDossiers() {
        return em.createQuery("SELECT d FROM DossierHospitalisation d", DossierHospitalisation.class).getResultList();
    }

    @Override
    public void modifierDossier(DossierHospitalisation dossier) {
        getEntityManager().merge(dossier);
    }

    @Override
    public DossierHospitalisation trouverDossierParPatient(Z_USER user) {
        try {
            return em.createQuery("SELECT s FROM DossierHospitalisation s WHERE s.lePatient = :variable", DossierHospitalisation.class).setParameter("variable", user).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override    
    public List<DossierHospitalisation> trouverTousLesDossiersUnPatient(Z_PATIENT patient){
        String txt = "SELECT d FROM DossierHospitalisation d WHERE d.lePatient=:patient";
        Query req= em.createQuery(txt);
        req.setParameter("patient", patient);
        List<DossierHospitalisation> dossiers = req.getResultList();
        return dossiers;
    }
    
    @Override
    public List<DossierHospitalisation> trouverTousLesDossiersUnService(Service service){
        String txt = "SELECT d FROM DossierHospitalisation d WHERE d.leService=:service";
        Query req= em.createQuery(txt);
        req.setParameter("service", service);
        List<DossierHospitalisation> dossiers = req.getResultList();
        return dossiers;
    }

    // bien que la méthode supprimer existe dans cette facade, on a décidé de ne pas permettre aux utilisateurs de supprimer un dossier
    @Override
    public void supprimerDossierHospitalisation(Long id) {
        DossierHospitalisation dossier=trouverDossierHospitalisationParId(id) ;            
            em.remove(dossier);
    }
    
    
    
    
}
