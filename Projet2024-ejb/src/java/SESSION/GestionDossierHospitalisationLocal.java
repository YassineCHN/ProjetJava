/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import java.util.List;
import ENTITE.DossierHospitalisation;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionDossierHospitalisationLocal {

    List<DossierHospitalisation> afficherDossier();

    

    
}
