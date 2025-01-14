/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Service;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_PERSONNEL;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ychen
 */
@Local
public interface Z_PERSONNEFacadeLocal {

    void create(Z_PERSONNE z_PERSONNE);

    void edit(Z_PERSONNE z_PERSONNE);

    void remove(Z_PERSONNE z_PERSONNE);

    Z_PERSONNE find(Object id);

    List<Z_PERSONNE> findAll();

    List<Z_PERSONNE> findRange(int[] range);

    int count();
    
    public void creerPersonne(String nom, String prenom,String adresse);
    
    public void mettreAJourPersonne(Z_PERSONNE pers);
    
    public void supprimerPersonne(Long id) ;

    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public List<Z_PERSONNE> trouverToutesLesPersonnes();
    
    public List<Z_MEDECIN> trouverTousLesMedecins();
    
    public void creerMedecin(String nom, String prenom, String adresse, String specialite);

    public List<Z_PATIENT> trouverTousLesPatients() ;

    public void creerPatient(String nom, String prenom,String adresse, String numSecuSoc,String nomMutuelle, String adresseMutuelle);

    public Z_PATIENT trouverPatientParNumSecu(String numSecu);
    
    public void creerPersonnel(String nom, String prenom, String adresse, Service service) ;
    
    public List<Z_PERSONNEL> trouverTousLesPersonnels() ;
    
    
}
