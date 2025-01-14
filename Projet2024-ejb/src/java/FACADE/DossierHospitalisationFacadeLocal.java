/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;

import ENTITE.Service;
import ENTITE.Z_PATIENT;
import ENTITE.Z_USER;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface DossierHospitalisationFacadeLocal {

    void create(DossierHospitalisation dossierHospitalisation);

    void edit(DossierHospitalisation dossierHospitalisation);

    void remove(DossierHospitalisation dossierHospitalisation);

    DossierHospitalisation find(Object id);

    List<DossierHospitalisation> findAll();

    List<DossierHospitalisation> findRange(int[] range);

    int count();

    void creerDossierHospitalisation(Z_PATIENT patient, Service service, Date dateHospitalisation, Date heureArrivee, Date heureDepart);

    void modifierDossierHospitalisation(Long id, Date heureArrivee, Date heureDepart);

    void annulerDossierHospitalisation(Long id);

    DossierHospitalisation trouverDossierHospitalisationParId(Long id);

    List<DossierHospitalisation> trouverTousLesDossiers();

    void mergeDossier(DossierHospitalisation dossier);

    DossierHospitalisation trouverDossierParPatient(Z_USER id);
}
