/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.Service;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import FACADE.ActeFacadeLocal;
import FACADE.DossierHospitalisationFacadeLocal;
import FACADE.JournalActeFacadeLocal;
import FACADE.LigneJournalFacadeLocal;
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
public class SessionPERSONNEL implements SessionPERSONNELLocal {

    @EJB
    private JournalActeFacadeLocal journalActeFacade;
    
    
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
    public List<DossierHospitalisation> trouverTousLesDossiersUnService(Service service){
      return dossierHospitalisationFacade.trouverTousLesDossiersUnService(service);
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
    public DossierHospitalisation trouverDossierParId(Long id) {
        DossierHospitalisation dossier = dossierHospitalisationFacade.trouverDossierHospitalisationParId(id);
        return dossier;
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
