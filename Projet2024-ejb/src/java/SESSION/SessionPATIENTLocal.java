/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import ENTITE.Service;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ychen
 */
@Local
public interface SessionPATIENTLocal {
    
    DossierHospitalisation trouverDossierParId(Long id);
    
    void annulerDossierHospitalisation(Long id) ;
    
    public List<DossierHospitalisation> trouverTousLesDossiersUnPatient(Z_PATIENT patient);
    
    void validerFacturePaiement(Facture facture);
    
    List<Facture> trouverFacturesPatient(Z_PATIENT patient);

    Facture trouverFactureParID(Long id);
    
    public void modifierPersonne(Z_PERSONNE pers);
    
    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public Z_USER trouverUtilisateurParPers(Long id);
    
    Z_USER trouverUtilisateurParId(Long id);
    Paiement enregistrerPaiement(Double montantPaiement, ModePaiement modePaiement, Facture laFacture);
    
    
}
