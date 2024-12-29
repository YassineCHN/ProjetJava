/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;

import ENTITE.Service;
import ENTITE.Z_PATIENT;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void creerDossierHospitalisation(Z_PATIENT patient, Service service, List<Acte> actes, Date dateHospitalisation) {
        DossierHospitalisation dossier = new DossierHospitalisation();
        dossier.setLePatient(patient);
        dossier.setLeService(service);
        dossier.setLesActes(actes);
        dossier.setDateHospitalisation(dateHospitalisation);
        em.persist(dossier);
    }

    @Override
    public void modifierDossierHospitalisation(Long id, Date heureArrivee, Date heureDepart) {
        DossierHospitalisation dossier = em.find(DossierHospitalisation.class, id);
        if (dossier != null) {
            dossier.setHeureArrivee(heureArrivee);
            dossier.setHeureDepart(heureDepart);
            em.merge(dossier);
        }
    }

    @Override
    public void annulerDossierHospitalisation(Long id) {
        DossierHospitalisation dossier = em.find(DossierHospitalisation.class, id);
        if (dossier != null) {
            em.remove(dossier);
        }
    }

    @Override
    public DossierHospitalisation trouverDossierHospitalisationParId(Long id) {
        return em.find(DossierHospitalisation.class, id);
    }

    @Override
    public List<DossierHospitalisation> trouverTousLesDossiers() {
        return em.createQuery("SELECT d FROM DossierHospitalisation d", DossierHospitalisation.class).getResultList();
    }
}
