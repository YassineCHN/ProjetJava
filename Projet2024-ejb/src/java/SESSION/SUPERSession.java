/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.RoleUSER;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_PERSONNEL;
import ENTITE.Z_USER;
import ENTITE.statutDossier;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.Z_PERSONNEFacade;
import FACADE.Z_PERSONNEFacadeLocal;
import FACADE.Z_USERFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ychen
 */
@Stateless
public class SUPERSession implements SUPERSessionLocal {

    
     @EJB
    private Z_PERSONNEFacadeLocal z_PERSONNEFacade;

    @EJB
    private Z_USERFacadeLocal z_USERFacade;
    
    @EJB
    private DossierHospitalisationFacadeLocal dossierHospitalisationFacade;

    
    @Override
    public Z_USER Z_authentificationUtilisateurAdmin(String login, String mdp) {
        String test = "ADMIN";
        Z_USER user = z_USERFacade.authentification(login, mdp);
        if (user != null && user.getRole().equals(test.toUpperCase())) {
            return user;
        } else {
            return null;
        }
    }
    

        @Override
    public Z_USER Z_authentificationUtilisateur(String login, String mdp) {
        Z_USER user = z_USERFacade.authentification(login, mdp);
        if (user != null) {
            
            return user;

        } else {
            
            return null;
        }

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
    public void modifierPersonne(Z_PERSONNE pers) {
        z_PERSONNEFacade.mettreAJourPersonne(pers);
    }
    
    
    
    
    @Schedule(hour="*", minute="30", second="0", persistent=false)
//    pourquoi false  : https://chatgpt.com/share/677efef0-8cbc-8001-a470-ec5c5c8fff77
//    exécution toutes les 30 minutes à la première seconde de la 30eme minutes
    public void mettreAJourDossiersInactifs() {
        // Récupération des dossiers
        List<DossierHospitalisation> dossiers = dossierHospitalisationFacade.trouverTousLesDossiers();
        
        // Parcourir et vérifier la condition : si la date/heure actuelle est > dateHospitalisation + 1h, alors on passe le statut à "inactif"
        for(DossierHospitalisation dossier : dossiers) {
            if (conditionPourDevenirInactif(dossier)) {
//                if true alors en dessous, if false alors rien
                dossier.setStatutD(statutDossier.Inactif);
                dossierHospitalisationFacade.modifierDossier(dossier);
            }
        }
    }

    private boolean conditionPourDevenirInactif(DossierHospitalisation dossier) {

        Date dateHosp = dossier.getDateHospitalisation();
        if(dateHosp == null) {
            return false;
        } else if (dossier.getHeureArrivee()==null){
            long nowMillis = new Date().getTime();
        long hospMillis = dateHosp.getTime();
        
        return (nowMillis-hospMillis) > 3_600_000; 
        }else {
            return false;
        }
        

    }
    
     @Override
    public String authentifierUtilisateur(String login, String password, HttpSession session, HttpServletRequest request) {
        // Vérification des champs vides
        if (login == null || password == null || login.trim().isEmpty() || password.trim().isEmpty()) {
            return "L'un des champs du formulaire est vide, veuillez réessayer !";
        }

        // Authentification de l'utilisateur
        Z_USER user = Z_authentificationUtilisateur(login, password);
        if (user == null) {
            return "Erreur d'authentification. Veuillez vérifier vos identifiants.";
        }

        // Récupération des informations de l'utilisateur
        String user_identifie = user.getLogin();
        RoleUSER role_identifie = user.getRole();
        String id_user = String.valueOf(user.getId());

        // Stocker les informations de l'utilisateur dans la session
        session.setAttribute("utilisateur2", user_identifie);
        session.setAttribute("role2", role_identifie.name());
        session.setAttribute("id_user", id_user);

        // Vérification si l'utilisateur fait partie du service financier
        if (role_identifie == RoleUSER.PERSONNEL) {
            Z_PERSONNE personne = user.getPersonne();
            if (personne instanceof Z_PERSONNEL) {
                Z_PERSONNEL personnel = (Z_PERSONNEL) personne;
                if ("Financier".equalsIgnoreCase(personnel.getService().getServiceNom())) {
                    session.setAttribute("ServiceFinancier", "ServiceFinancier");
                }
            }
        }

        return "Bienvenue, " + role_identifie.name() + "!";
    }
    
    
    
    @Override
public String modifierPersonneParID(String idPersonneStr, String nom, String prenom, String adresse, 
                                    String typePersonne, String specialite, String serviceId, 
                                    String numSecuSoc, String mutuelle, String adresseMutuelle) {
    if (idPersonneStr == null || idPersonneStr.trim().isEmpty()) {
        return "ID de la personne manquant.";
    }

    try {
        Long idPersonne = Long.parseLong(idPersonneStr);
        return z_PERSONNEFacade.modifierPersonne(idPersonne, nom, prenom, adresse, 
                                               typePersonne, specialite, serviceId, 
                                               numSecuSoc, mutuelle, adresseMutuelle);
    } catch (NumberFormatException e) {
        return "ID de la personne invalide.";
    }
}


}
