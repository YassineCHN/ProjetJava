/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import ENTITE.Facture;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface GestionPaiementLocal {

    Paiement enregistrerPaiement(Double montantPaiement, ModePaiement modePaiement, Facture laFacture);
    
}
