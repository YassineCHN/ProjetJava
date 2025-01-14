/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;

import ENTITE.Facture;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import FACADE.PaiementFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author charl
 */
@Stateless
public class GestionPaiement implements GestionPaiementLocal {

    @EJB
    private PaiementFacadeLocal paiementFacade;

    @Override
    public Paiement enregistrerPaiement(Double montantPaiement, ModePaiement modePaiement, Facture laFacture) {
        return paiementFacade.enregistrerPaiement(montantPaiement, modePaiement, laFacture);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
