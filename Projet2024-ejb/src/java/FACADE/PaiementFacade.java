/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Facture;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class PaiementFacade extends AbstractFacade<Paiement> implements PaiementFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaiementFacade() {
        super(Paiement.class);
    }

    @Override
    public Paiement enregistrerPaiement( Double montantPaiement, ModePaiement mode, Facture laFacture ) {
        Paiement paiement = new Paiement();
        paiement.setDatePaiement(new Date());
        paiement.setMontantPaiement(montantPaiement);
        paiement.setModePaiement(mode);
        paiement.setLaFacture(laFacture);
        em.persist(paiement);
        return paiement;
        
    }
    
    
    
}
