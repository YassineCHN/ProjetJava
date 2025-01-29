/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.DossierHospitalisation;
import ENTITE.Service;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import java.text.ParseException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ychen
 */
@Local
public interface SessionPERSONNELLocal {
    
    public List<DossierHospitalisation> trouverTousLesDossiersUnService(Service service);
    
//    public void modifierDossier(DossierHospitalisation dossier) ;
    
    void annulerDossierHospitalisation(Long id) ;
    
    DossierHospitalisation trouverDossierParId(Long id);

    Service trouverServiceParID(Long id);
    
    public void modifierPersonne(Z_PERSONNE pers);
    
    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public Z_USER trouverUtilisateurParPers(Long id);
    
    Z_USER trouverUtilisateurParId(Long id);
    
    String modifierDossierParPersonnel(String idDossierStr, String dateArriveeStr, String dateDepartStr) throws ParseException ;
}
