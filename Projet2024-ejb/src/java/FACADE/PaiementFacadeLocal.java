/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Facture;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface PaiementFacadeLocal {

    void create(Paiement paiement);

    void edit(Paiement paiement);

    void remove(Paiement paiement);

    Paiement find(Object id);

    List<Paiement> findAll();

    List<Paiement> findRange(int[] range);

    int count();

    Paiement enregistrerPaiement( Double montantPaiement, ModePaiement mode, Facture laFacture);
    
}
