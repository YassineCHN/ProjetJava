/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import ENTITE.RoleUSER;
import ENTITE.Service;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import FACADE.ActeFacadeLocal;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.FactureFacadeLocal;
import FACADE.PaiementFacadeLocal;
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
public class SessionPATIENT implements SessionPATIENTLocal {

    @EJB
    private PaiementFacadeLocal paiementFacade;
    
    @EJB
    private ServiceFacadeLocal serviceFacade;
    
    @EJB
    private DossierHospitalisationFacadeLocal dossierHospitalisationFacade;
    
    @EJB
    private FactureFacadeLocal factureFacade;
    
    @EJB
    private Z_PERSONNEFacadeLocal z_PERSONNEFacade;

    @EJB
    private Z_USERFacadeLocal z_USERFacade;
    

    
    @Override
    public DossierHospitalisation trouverDossierParId(Long id) {
        DossierHospitalisation dossier = dossierHospitalisationFacade.trouverDossierHospitalisationParId(id);
        return dossier;
    }
    
    @Override
    public List<DossierHospitalisation> trouverTousLesDossiersUnPatient(Z_PATIENT patient){
        return dossierHospitalisationFacade.trouverTousLesDossiersUnPatient(patient);
    }
    
    @Override
    public void annulerDossierHospitalisation(Long id) {
        dossierHospitalisationFacade.annulerDossierHospitalisation(id);
    }
    
    @Override
    public void validerFacturePaiement(Facture facture) {
        factureFacade.validerFacturePaiement(facture);
    }
    
    @Override
    public Facture trouverFactureParID(Long id) {
        return factureFacade.trouverFactureParID(id);
    }
    
    @Override
    public List<Facture> trouverFacturesPatient(Z_PATIENT patient) {
       return factureFacade.trouverFacturesPatient(patient);   
    }
    
    @Override
    public void modifierPersonne(Z_PERSONNE pers) {
        z_PERSONNEFacade.mettreAJourPersonne(pers);
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
    public Paiement enregistrerPaiement(Double montantPaiement, ModePaiement modePaiement, Facture laFacture) {
        return paiementFacade.enregistrerPaiement(montantPaiement, modePaiement, laFacture);
    }
       @Override
public String payerFacture(Long idFacture, String modePaiementStr, Z_USER user) {
    // Vérification du rôle de l'utilisateur
    RoleUSER role = user.getRole();
    Facture laFacture = null;

    if (role == RoleUSER.PERSONNEL) {
        laFacture = factureFacade.trouverFactureParID(idFacture);
    } else if (role == RoleUSER.PATIENT) {
        laFacture = factureFacade.trouverFactureParID(idFacture); // Cette méthode peut être adaptée si nécessaire
    }

    if (laFacture == null) {
        return "Facture introuvable.";
    }

    if (laFacture.isFacturePayee()) {
        return "Facture déjà payée.";
    }

    try {
        // Conversion du mode de paiement en Enum
        ModePaiement modePaiement = ModePaiement.valueOf(modePaiementStr.trim().toUpperCase());

        // Enregistrer le paiement
        Paiement paiement = paiementFacade.enregistrerPaiement(laFacture.getFactureMontant(), modePaiement, laFacture);

        // Valider la facture
        factureFacade.validerFacturePaiement(laFacture);

        return "La facture a été payée avec succès en mode : " + modePaiement;
    } catch (IllegalArgumentException e) {
        // Si le mode de paiement est invalide
        return "Le mode de paiement '" + modePaiementStr + "' est invalide.";
    }
}
}
