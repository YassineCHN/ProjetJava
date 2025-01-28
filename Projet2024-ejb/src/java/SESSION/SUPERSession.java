/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import ENTITE.statutDossier;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.Z_PERSONNEFacadeLocal;
import FACADE.Z_USERFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

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
}
