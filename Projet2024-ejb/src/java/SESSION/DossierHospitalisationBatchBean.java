/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.statutDossier;
import FACADE.DossierHospitalisationFacadeLocal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author charl
 */
@Stateless
public class DossierHospitalisationBatchBean implements DossierHospitalisationBatchBeanLocal {

    @EJB
    private DossierHospitalisationFacadeLocal dossierHospitalisationFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    @Schedule(hour="*", minute="30", second="0", persistent=false)
//    pourquoi false  : https://chatgpt.com/share/677efef0-8cbc-8001-a470-ec5c5c8fff77
//    exécution toutes les 30 minutes à la première seconde de la 30eme minutes
    public void mettreAJourDossiersInactifs() {
        // Récupération des dossiers
        List<DossierHospitalisation> dossiers = dossierHospitalisationFacade.trouverTousLesDossiers();
        
        // Parcourir et vérifier la condition : 
        // si la date/heure actuelle est > dateHospitalisation + 1h
        // alors on passe le statut à "inactif"
        for(DossierHospitalisation dossier : dossiers) {
            if (conditionPourDevenirInactif(dossier)) {
//                if true alors en dessous, if false alors rien
                dossier.setStatutD(statutDossier.Inactif);
                dossierHospitalisationFacade.modifierDossier(dossier);
            }
        }
    }

    private boolean conditionPourDevenirInactif(DossierHospitalisation dossier) {
//        // Implémenter la comparaison de date
//        LocalDateTime now = LocalDateTime.now();
//        // Exemple : vérifie si now est au moins 1h après la date d’hospitalisation
//        LocalDateTime dateHospitalisation = dossier.getDateHospitalisation();
//        return now.isAfter(dateHospitalisation.plusHours(1));
//          
//          au lieu de faire comme dans les lignes ci-dessus, on calcul le temps en millisecondes
//          en utilisant les propriétés/méthodes du type Date

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
