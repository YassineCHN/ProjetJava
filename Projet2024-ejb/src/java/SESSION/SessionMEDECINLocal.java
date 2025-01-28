/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.Service;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ychen
 */
@Local
public interface SessionMEDECINLocal {

    void creerActe(String nom, String description, double prix,double coefSecu, double coefMutuelle);
    
    void modifierActe(Acte acte);
    
    boolean supprimerActe(Long id);
    
    List<Acte> trouverTousLesActes();

    Acte trouverActeParId(long id);
    
    List<DossierHospitalisation> afficherDossier();

    DossierHospitalisation trouverDossierParId(Long id);

    void creerDossier(Z_PATIENT patient, Service service, Date dateHospitalisation, Date heureArrivee, Date heureDepart);
    
    public void modifierDossier(DossierHospitalisation dossier) ;
    
    void annulerDossierHospitalisation(Long id) ;
    
    public List<DossierHospitalisation> trouverTousLesDossiersUnService(Service service);
    
    JournalActe creerJournal(DossierHospitalisation dossier);
    
    JournalActe trouverJournalParId(Long id);
    
    JournalActe trouverJournalParDossier(DossierHospitalisation dossier);

    void validerJournal(JournalActe journal);
    
    void creerLigne(Date date_acte, int quantite, String commentaire, Acte acte, JournalActe journal, Z_MEDECIN leMedecin);

    List<LigneJournal> trouverToutesLignes();

    void supprimerLigne(long id);
    
    List<LigneJournal> listerLignesParJournal(Long idJournal);
    
    LigneJournal trouverLigneParId(Long id);

    void mettreAJourLigne(LigneJournal ligne);
    
    List<Service> tousLesServices();

    Service trouverServiceParID(Long id);
    
    public void modifierPersonne(Z_PERSONNE pers);
    
    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public Z_PATIENT creerPatientCheckBox(String nom, String prenom, String adresse, String numSecuSoc, String nomMutuelle, String adresseMutuelle);

    public Z_USER trouverUtilisateurParPers(Long id);
    
    Z_USER trouverUtilisateurParId(Long id);
    
    public List<Z_MEDECIN> trouverTousLesMedecins();
    
    public List<Z_PATIENT> trouverTousLesPatients() ;
}
