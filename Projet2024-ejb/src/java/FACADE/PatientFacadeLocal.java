/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Patient;
import ENTITE.RolesUtilisateurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface PatientFacadeLocal {

    void create(Patient patient);

    void edit(Patient patient);

    void remove(Patient patient);

    Patient find(Object id);

    List<Patient> findAll();

    List<Patient> findRange(int[] range);

    int count();

    void creerPatient(String login, String motDePasse, RolesUtilisateurs role,String nom, String adresse, String numeroSS, String mutuelleNom, String mutuelleAdresse) ;

    void modifierPatient(String login, String motDePasse, RolesUtilisateurs role,Long id, String nom, String adresse, String numeroSS, String mutuelleNom, String mutuelleAdresse) ;

    void supprimerPatient(Long id);

    Patient trouverPatientParNom(String nom);

    List<Patient> trouverTousLesPatients();
}
