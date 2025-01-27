/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.Service;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import FACADE.ActeFacadeLocal;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.FactureFacadeLocal;
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
}
