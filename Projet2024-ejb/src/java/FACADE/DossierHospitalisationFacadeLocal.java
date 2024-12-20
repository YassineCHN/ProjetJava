/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Patient;
import ENTITE.Service;
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

    void creerDossierHospitalisation(Patient patient, Service service, List<Acte> actes, Date dateHospitalisation);

    void modifierDossierHospitalisation(Long id, Date heureArrivee, Date heureDepart);

    void annulerDossierHospitalisation(Long id);

    DossierHospitalisation trouverDossierHospitalisationParId(Long id);

    List<DossierHospitalisation> trouverTousLesDossiers();
}
