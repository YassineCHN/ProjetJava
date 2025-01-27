/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.RoleUSER;
import ENTITE.Service;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_PERSONNEL;
import ENTITE.Z_USER;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ychen
 */
@Local
public interface SessionADMINLocal {
    
    List<Z_USER> trouverTousLesUtilisateurs();
    
    public Z_USER trouverUserParLogin(String login);
    
    Z_USER trouverUtilisateurParId(Long id);
    
    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public Z_USER trouverUtilisateurParPers(Long id);
    
    boolean creerUtilisateur(String login, String mdp,RoleUSER role, Z_PERSONNE pers);
    
    public void creerPersonne(String nom, String prenom,String adresse);
    
    public void creerPatient(String nom, String prenom,String adresse, String numSecuSoc,String nomMutuelle, String adresseMutuelle);

    public void creerPersonnel(String nom, String prenom, String adresse, Service service) ;
    
    public void creerMedecin(String nom, String prenom, String adresse, String specialite, Service service);
    
    public void modifierPersonne(Z_PERSONNE pers);
    
    public void modifierUtilisateur(Z_USER user);
    
    boolean supprimerPersonne(Long id_test) ;
    
    boolean supprimerUtilisateur(Long id);
    
    public boolean personneHasUser(Long idPersonne);
    
    public List<Z_PERSONNE> trouverPersonnesSansUtilisateur();
    
    public List<Z_PERSONNE> trouverToutesLesPersonnes();
    
    public List<Z_PERSONNEL> trouverTousLesPersonnels() ;
     
    public List<Z_MEDECIN> trouverTousLesMedecins(); 

    public List<Z_PATIENT> trouverTousLesPatients() ;
    
    void CreerService(String nomService, String localisationService) ;
    
    void modifierService(Service serv);

    boolean SupprimerService(Long idService);

    List<Service> tousLesServices();

    Service trouverServiceParID(Long id);
    
    List<DossierHospitalisation> afficherDossier();
    
    List<Acte> trouverTousLesActes();
    
    Acte trouverActeParId(long id);

    boolean supprimerActe(Long id);

    void creerActe(String nom, String description, double prix,double coefSecu, double coefMutuelle);
    
    void modifierActe(Acte acte);
    
    
}
