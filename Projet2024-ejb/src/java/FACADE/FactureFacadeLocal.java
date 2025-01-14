/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.Z_USER;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface FactureFacadeLocal {

    void create(Facture facture);

    void edit(Facture facture);

    void remove(Facture facture);

    Facture find(Object id);

    List<Facture> findAll();

    List<Facture> findRange(int[] range);

    int count();

    Facture creerFacturePourJournal(Date factureDateEmissions, Double montant, boolean statutFacture, DossierHospitalisation leDossier,JournalActe journal);

    List<Facture> trouverToutesFactures();

    Facture trouverFactureParDossier(DossierHospitalisation dossier);

    Facture trouverFactureParID(Long id);

//    Facture trouverFactureParPatient(DossierHospitalisation dossier);

    
    
}
