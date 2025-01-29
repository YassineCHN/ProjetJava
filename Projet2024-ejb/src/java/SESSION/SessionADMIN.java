/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
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
import FACADE.ActeFacadeLocal;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.ServiceFacadeLocal;
import FACADE.Z_PERSONNEFacadeLocal;
import FACADE.Z_USERFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ychen
 */
@Stateless
public class SessionADMIN implements SessionADMINLocal {

    @EJB
    private Z_PERSONNEFacadeLocal z_PERSONNEFacade;

    @EJB
    private Z_USERFacadeLocal z_USERFacade;
    
    @EJB
    private ServiceFacadeLocal serviceFacade;
    
    @EJB
    private ActeFacadeLocal acteFacade;
    
    @EJB
    private DossierHospitalisationFacadeLocal dossierHospitalisationFacade;
    
    
    @Override
    public List<Z_USER> trouverTousLesUtilisateurs() {
        return z_USERFacade.trouverTousLesUtilisateurs();
    }
    
    @Override
    public Z_USER trouverUserParLogin(String login){
        Z_USER user=z_USERFacade.trouverUserParLogin(login);
        return user;
    }
    
    @Override
    public Z_USER trouverUtilisateurParId(Long id) {
        Z_USER user = z_USERFacade.trouverUtilisateurParId(id);
        return user;
    }
    
    @Override
    public Z_PERSONNE trouverPersonneParId(Long id) {
        Z_PERSONNE pers = z_PERSONNEFacade.trouverPersonneParId(id);
        return pers;
    }
    
    @Override
    public Z_USER trouverUtilisateurParPers(Long id) {
        Z_USER user = z_USERFacade.trouverUtilisateurParPersonne(id);
        return user;
    }
    
    @Override
    public String creerUtilisateur(String login, String mdp,RoleUSER role, Long persId) {
        Z_PERSONNE pers=trouverPersonneParId(persId);
        if(pers!=null && personneHasUser(persId)){
            return"USER_ASSOCIE";
        }
        if (role!=RoleUSER.ADMIN && pers!=null){
            String typePersonne=pers.getTYPE();
            if ((role == RoleUSER.MEDECIN && !"MEDECIN".equals(typePersonne)) ||
                        (role == RoleUSER.PATIENT && !"PATIENT".equals(typePersonne)) ||
                        (role == RoleUSER.PERSONNEL && !"PERSONNEL".equals(typePersonne))) 
                    {return "MAUVAIS_TYPE";}
        }
        if (z_USERFacade.trouverUserParLogin(login)!=null){ 
                return "EXISTANT";
        }
       
        z_USERFacade.creerUtilisateur(login, mdp, role, pers);
        return "CREE";
    }

    
    @Override
    public void creerPersonne(String nom, String prenom,String adresse) {
        z_PERSONNEFacade.creerPersonne(nom, prenom, adresse);
    }
    
    @Override
    public void creerPatient(String nom, String prenom, String adresse, String numSS, String nomMut,String adresseMut) {
        z_PERSONNEFacade.creerPatient(nom, prenom, adresse, numSS, nomMut, adresseMut);
    }
    
    @Override
    public void creerPersonnel(String nom, String prenom, String adresse, Service service) {
        z_PERSONNEFacade.creerPersonnel(nom, prenom, prenom, service);
    }
    
        @Override
    public void creerMedecin(String nom, String prenom,String adresse, String specialite, Service service) {
        z_PERSONNEFacade.creerMedecin(nom, prenom, adresse, specialite,service);
    }
    
    @Override
    public void modifierPersonne(Z_PERSONNE pers) {
        z_PERSONNEFacade.mettreAJourPersonne(pers);
    }
        @Override
    public boolean modifierUtilisateur(Long iduser, String login, String mdp, Long idpersonne) {
        Z_USER user=z_USERFacade.trouverUtilisateurParId(iduser);
        Z_PERSONNE pers=z_PERSONNEFacade.trouverPersonneParId(idpersonne);
        if (user!=null){
            z_USERFacade.mettreAJourUtilisateur(user,login,mdp,pers);
            return true;
        }
        else {
            return false;
        }
    }

    
    @Override
    public boolean supprimerUtilisateur(Long id) {
        Z_USER userSupp=z_USERFacade.trouverUtilisateurParId(id);
        if(userSupp!=null){
            z_USERFacade.supprimerUtilisateur(userSupp);
            return true;
        } else {
            return false;

        }
    }
    @Override
    public boolean supprimerPersonne(Long id) {
        Z_PERSONNE persSupp=z_PERSONNEFacade.trouverPersonneParId(id);
        if (persSupp==null){
            return false;
        }
        if(persSupp instanceof Z_MEDECIN) {
            Z_MEDECIN medecin = (Z_MEDECIN) persSupp; 
            if (medecin.getService()==null && medecin.getLigneJournals().isEmpty()){
                z_PERSONNEFacade.supprimerPersonne(persSupp);
                return true;
            }
        } else if (persSupp instanceof Z_PATIENT){
            Z_PATIENT patient = (Z_PATIENT) persSupp; 
            if (patient.getDossierHospitalisations().isEmpty()){
                z_PERSONNEFacade.supprimerPersonne(persSupp);
                return true;
            }  
        }else if (persSupp instanceof Z_PERSONNEL){
            Z_PERSONNEL personnel = (Z_PERSONNEL) persSupp; 
            if (personnel.getService()==null){
                z_PERSONNEFacade.supprimerPersonne(persSupp);
                return true;
            }
        }
        return false; 
    }
    
    @Override
    public boolean personneHasUser(Long idPersonne) {
        return z_PERSONNEFacade.personneHasUser(idPersonne);
    }
    
    
        @Override
    public List<Z_PERSONNE> trouverPersonnesSansUtilisateur() {
        return z_PERSONNEFacade.trouverPersonnesSansUtilisateur();
    }
        
    @Override
    public List<Z_PERSONNE> trouverToutesLesPersonnes() {
        return z_PERSONNEFacade.trouverToutesLesPersonnes();
    }
    
    @Override
    public List<Z_PERSONNEL> trouverTousLesPersonnels() {
        return z_PERSONNEFacade.trouverTousLesPersonnels();
    }
    
        @Override
    public List<Z_MEDECIN> trouverTousLesMedecins() {
        return z_PERSONNEFacade.trouverTousLesMedecins();
    }
    
    @Override
    public List<Z_PATIENT> trouverTousLesPatients() {
        return z_PERSONNEFacade.trouverTousLesPatients();
    }
    
    @Override
    public void CreerService(String nomService, String localisationService) {
       
            serviceFacade.creerService(nomService, localisationService);
       
    }
//    a supprimer
//    @Override
//    public void modifierService(Service serv){
//        serviceFacade.modifierService(serv);
//    }

    @Override
    public boolean SupprimerService(Long idService) {
    Service serv=serviceFacade.trouverServiceParId(idService);
        if (serv != null && 
            serv.getDossierHospitalisations().isEmpty() &&
            serv.getLesMedecins().isEmpty() &&
            serv.getLesPersonnels().isEmpty() )
        {
            serviceFacade.supprimerService(serv);
            return true;
        } else {
            return false;
        }
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
    public List<DossierHospitalisation> afficherDossier() {
        List<DossierHospitalisation> result = dossierHospitalisationFacade.trouverTousLesDossiers();
        System.out.println(result.toString());
        return result;
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
    public void creerActe(String nom, String description, double prix,double coefSecu, double coefMutuelle) {
        acteFacade.creerActe(nom, description, prix, coefSecu,  coefMutuelle);
    }
    
    @Override
    public boolean modifierActe(Long idActe, String nomActe, String descriptionActe,String prixActe,String coefSecu,String coefMutuelle) {
        Acte acte=acteFacade.trouverActeParId(idActe);
        if (acte!=null) {
        Double prix = Double.parseDouble(prixActe);
        Double coeffSS = Double.parseDouble(coefSecu);
        Double coeffMutuelle = Double.parseDouble(coefMutuelle);
        acteFacade.modifierActe(acte, nomActe,descriptionActe,prix,coeffSS,coeffMutuelle);
        return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String modifierServiceParID(String idServiceStr, String localisation, String nomService) {
        if (idServiceStr == null || idServiceStr.trim().isEmpty()) {
            return "ID du service manquant.";
        }

        try {
            Long idService = Long.parseLong(idServiceStr);
            // Récupération du service
            Service service = serviceFacade.trouverServiceParId(idService);
            if (service == null) {
                return "Service non trouvé.";
            }
            // Persistance de la modification
            serviceFacade.modifierService(service,localisation,nomService);

            return "Service modifié avec succès.";
        } catch (NumberFormatException e) {
            return "ID du service invalide.";
        }
    }

     
}
