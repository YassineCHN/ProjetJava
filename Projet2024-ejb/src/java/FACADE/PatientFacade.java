/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Patient;
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
public class PatientFacade extends AbstractFacade<Patient> implements PatientFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientFacade() {
        super(Patient.class);
    }

    @Override
    public void creerPatient(String login, String motDePasse, RolesUtilisateurs role, String nom, String adresse, String numeroSS, String mutuelleNom, String mutuelleAdresse) {
        Patient patient = new Patient();
        patient.setUtilisateurLogin(login);
        patient.setUtilisateurMDP(mutuelleNom);
//        patient.setUtilisateurRole(role);
        patient.setPatientNom(nom);
        patient.setPatientAdresse(adresse);
        patient.setNumeroSSPatient(numeroSS);
//        patient.setMutuelleNom(mutuelleNom);
//        patient.setMutuelleAdresse(mutuelleAdresse);
//faudra modifier l'entite plus tard pour permettre ça
        em.persist(patient);
    }

    @Override
    public void modifierPatient(String login, String motDePasse, RolesUtilisateurs role, Long id, String nom, String adresse, String numeroSS, String mutuelleNom, String mutuelleAdresse) {
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            patient.setUtilisateurLogin(login);
            patient.setUtilisateurMDP(mutuelleNom);
//            patient.setUtilisateurRole(role);
            patient.setPatientNom(nom);
            patient.setPatientAdresse(adresse);
            patient.setNumeroSSPatient(numeroSS);
//            patient.setMutuelleNom(mutuelleNom);
//            patient.setMutuelleAdresse(mutuelleAdresse);
//faudra modifier l'entite plus tard pour permettre ça
            em.merge(patient);
        }
    }

    @Override
    public void supprimerPatient(Long id) {
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            em.remove(patient);
        }
    }

    @Override
    public Patient trouverPatientParNom(String nom) {
        try {
            return em.createQuery("SELECT p FROM Patient p WHERE p.nom = :nom", Patient.class).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Patient> trouverTousLesPatients() {
        return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }

}
