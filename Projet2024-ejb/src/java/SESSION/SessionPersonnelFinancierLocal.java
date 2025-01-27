/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import ENTITE.Service;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_USER;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ychen
 */
@Local
public interface SessionPersonnelFinancierLocal {
    
    Acte trouverActeParId(long id);
    
    DossierHospitalisation trouverDossierParId(Long id);
    
    Facture creerFacturePourJournal(Long idJournal);
    
    JournalActe trouverJournalParId(Long id);

    List<Facture> trouverToutesFactures();

    Facture trouverFactureParDossier(DossierHospitalisation dossier);

    Facture trouverFactureParID(Long id);

    void validerFacturePaiement(Facture facture);

    List<Facture> trouverFacturesPatient(Z_PATIENT patient);

    List<Facture> trouverFacturesNonPayeesAvecEmissionDepassee();
    
    void creerLigne(Date date_acte, int quantite, String commentaire, Acte acte, JournalActe journal, Z_MEDECIN leMedecin);

    List<LigneJournal> trouverToutesLignes();

    void supprimerLigne(long id);

    List<LigneJournal> listerLignesParJournal(Long idJournal);

    LigneJournal trouverLigneParId(Long id);

    void mettreAJourLigne(LigneJournal ligne);
    
    Paiement enregistrerPaiement(Double montantPaiement, ModePaiement modePaiement, Facture laFacture);

    Service trouverServiceParID(Long id);
    
    public void modifierPersonne(Z_PERSONNE pers);
    
    public Z_PERSONNE trouverPersonneParId(Long id) ;
    
    public Z_USER trouverUtilisateurParPers(Long id);
    
    Z_USER trouverUtilisateurParId(Long id);
}
