/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.RoleUSER;
import ENTITE.Service;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_PERSONNEL;
import ENTITE.Z_USER;
import javax.ejb.Local;
import java.util.List;
/**
 *
 * @author charl
 */
@Local
public interface Z_USER_BEANLocal {


    Z_USER Z_authentificationUtilisateurAdmin(String login, String mdp);

    Z_USER Z_authentificationUtilisateur(String login, String mdp);

    List<Z_USER> trouverTousLesUtilisateurs();
    
    public Z_USER trouverUserParLogin(String login);

    void creerAdmin(String login, String mdp, String adminStatus);

    Z_USER trouverUtilisateurParId(Long id);

    void supprimerUtilisateur(Long id);

    Z_PATIENT trouverPatientParNumSecu(String numSecu);
    
    boolean creerUtilisateur(String login, String mdp,RoleUSER role, Z_PERSONNE pers);

    public void creerPersonne(String nom, String prenom,String adresse);
    
    public void supprimerPersonne(Long id_test) ;
    
    public void modifierPersonne(Z_PERSONNE pers);
    
    public void modifierUtilisateur(Z_USER user);

    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public Z_USER trouverUtilisateurParPers(Long id);
    
    public List<Z_PERSONNE> trouverToutesLesPersonnes();
    
    public List<Z_PERSONNE> trouverPersonnesSansUtilisateur();
    
    public boolean personneHasUser(Long idPersonne);
    
    public List<Z_MEDECIN> trouverTousLesMedecins();
    
    public void creerMedecin(String nom, String prenom, String adresse, String specialite);

    public List<Z_PATIENT> trouverTousLesPatients() ;

    public void creerPatient(String nom, String prenom,String adresse, String numSecuSoc,String nomMutuelle, String adresseMutuelle);

    public void creerPersonnel(String nom, String prenom, String adresse, Service service) ;
    
    public List<Z_PERSONNEL> trouverTousLesPersonnels() ;
}
