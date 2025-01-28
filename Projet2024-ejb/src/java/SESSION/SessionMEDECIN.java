/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
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
import FACADE.ActeFacadeLocal;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.JournalActeFacadeLocal;
import FACADE.LigneJournalFacadeLocal;
import FACADE.ServiceFacadeLocal;
import FACADE.Z_PERSONNEFacadeLocal;
import FACADE.Z_USERFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ychen
 */
@Stateless
public class SessionMEDECIN implements SessionMEDECINLocal {

    @EJB
    private JournalActeFacadeLocal journalActeFacade;
    
    @EJB
    private ActeFacadeLocal acteFacade;
    
    @EJB
    private ServiceFacadeLocal serviceFacade;
    
    @EJB
    private DossierHospitalisationFacadeLocal dossierHospitalisationFacade;
    
    @EJB
    private LigneJournalFacadeLocal ligneJournalFacade;
    
    @EJB
    private Z_PERSONNEFacadeLocal z_PERSONNEFacade;

    @EJB
    private Z_USERFacadeLocal z_USERFacade;

    @Override
    public void creerActe(String nom, String description, double prix,double coefSecu, double coefMutuelle) {
        acteFacade.creerActe(nom, description, prix, coefSecu,  coefMutuelle);
    }
    
    @Override
    public void modifierActe(Acte acte) {
        acteFacade.modifierActe(acte);
    }
    
    @Override
    public boolean supprimerActe(Long id) {
        Acte acte=acteFacade.trouverActeParId(id);
        if(acte!=null && acte.getLigneJournals().isEmpty()){
            acteFacade.supprimerActe(acte);
            return true;
        } else {
            return false;
        }
        
    }
    
    @Override
    public List<Acte> trouverTousLesActes() {
        List<Acte> result = acteFacade.trouverTousLesActes();
        return result;
    }
    
    @Override
    public Acte trouverActeParId(long id) {
        Acte result = acteFacade.trouverActeParId(id);
        return result;
    }
    
     @Override
    public List<DossierHospitalisation> afficherDossier() {
        List<DossierHospitalisation> result = dossierHospitalisationFacade.trouverTousLesDossiers();
        System.out.println(result.toString());
        return result;
    }

    @Override
    public DossierHospitalisation trouverDossierParId(Long id) {
        DossierHospitalisation dossier = dossierHospitalisationFacade.trouverDossierHospitalisationParId(id);
        return dossier;
    }
    
    @Override
    public void creerDossier(Z_PATIENT patient, Service service, Date dateHospitalisation, Date heureArrivee, Date heureDepart) {
        dossierHospitalisationFacade.creerDossierHospitalisation(patient, service ,dateHospitalisation,heureArrivee,heureDepart );
    }
    
    @Override
    public void modifierDossier(DossierHospitalisation dossier) {
        dossierHospitalisationFacade.modifierDossier(dossier);
    }
    
    @Override
    public void annulerDossierHospitalisation(Long id) {
        dossierHospitalisationFacade.annulerDossierHospitalisation(id);
    }
    
    @Override
    public List<DossierHospitalisation> trouverTousLesDossiersUnService(Service service){
      return dossierHospitalisationFacade.trouverTousLesDossiersUnService(service);
    }
    
    @Override
    public JournalActe creerJournal(DossierHospitalisation dossier) {
        JournalActe journal = journalActeFacade.creerJournal(dossier);
        return journal;
    }
    
    @Override
    public JournalActe trouverJournalParId(Long id) {
        return journalActeFacade.trouverJournalParId(id);
    }
    
    @Override
    public JournalActe trouverJournalParDossier(DossierHospitalisation dossier) {
        return journalActeFacade.trouverJournalParDossier(dossier);
    }

    @Override
    public void validerJournal(JournalActe journal) {
        journalActeFacade.validerJournal(journal);
    }
    
    @Override
    public void creerLigne(Date date_acte, int quantite, String commentaire, Acte acte, JournalActe journal, Z_MEDECIN leMedecin) {
        ligneJournalFacade.creerLigneJournal(date_acte, quantite, commentaire, acte, journal, leMedecin);
    }

    @Override
    public List<LigneJournal> trouverToutesLignes() {
      return  ligneJournalFacade.trouverToutesLignes();
    }

    @Override
    public void supprimerLigne(long id) {
        ligneJournalFacade.supprimerLigne(id);
    }
    
    @Override
    public List<LigneJournal> listerLignesParJournal(Long idJournal) {
        return ligneJournalFacade.listerLignesParJournal(idJournal);
    }

    @Override
    public LigneJournal trouverLigneParId(Long id) {
        return ligneJournalFacade.trouverLigneParId(id);
    }
    
    @Override
    public void mettreAJourLigne(LigneJournal ligne) {
        ligneJournalFacade.mettreAJourLigne(ligne);
    }
    
    @Override
    public List<Service> tousLesServices() {
        List<Service> result = serviceFacade.trouverTousLesServices();
        return result;
    }

    @Override
    public Service trouverServiceParID(Long id) {
        Service test = serviceFacade.trouverServiceParId(id);
        return test;
    }
    
    @Override
    public void modifierPersonne(Z_PERSONNE pers) {
        z_PERSONNEFacade.mettreAJourPersonne(pers);
    }
    
    
    @Override
    public Z_PATIENT creerPatientCheckBox(String nom, String prenom, String adresse, String numSS, String nomMut,String adresseMut) {
        Z_PATIENT patient=z_PERSONNEFacade.creerPatientCheckBox(nom, prenom, adresse, numSS, nomMut, adresseMut);
        return patient;
    }
    
    @Override
    public Z_USER trouverUtilisateurParPers(Long id) {
        Z_USER user = z_USERFacade.trouverUtilisateurParPersonne(id);
        return user;
    }
    
    @Override
    public Z_PERSONNE trouverPersonneParId(Long id) {
        Z_PERSONNE pers = z_PERSONNEFacade.trouverPersonneParId(id);
        return pers;
    }
    
    @Override
    public Z_USER trouverUtilisateurParId(Long id) {
        Z_USER user = z_USERFacade.trouverUtilisateurParId(id);
        return user;
    }
    
    @Override
    public List<Z_MEDECIN> trouverTousLesMedecins() {
        return z_PERSONNEFacade.trouverTousLesMedecins();
    }
    
    @Override
    public List<Z_PATIENT> trouverTousLesPatients() {
        return z_PERSONNEFacade.trouverTousLesPatients();
    }
}
