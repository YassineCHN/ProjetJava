/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.Acte;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.Z_MEDECIN;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author charl
 */
@Stateless
public class LigneJournalFacade extends AbstractFacade<LigneJournal> implements LigneJournalFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneJournalFacade() {
        super(LigneJournal.class);
    }

    @Override
    public void creerLigneJournal(Date date_acte, int quantite, String commentaire, Acte acte, JournalActe journal, Z_MEDECIN leMedecin) {
        LigneJournal ligne = new LigneJournal();
        ligne.setDate_acte(date_acte);
        ligne.setQuantité_Acte(quantite);
        ligne.setCommentaire(commentaire);
        ligne.setId_acte(acte);
        ligne.setId_journal(journal);
        ligne.setLeMedecin(leMedecin);
        em.persist(ligne);
        System.out.println("CREATION LIGNE DANS FACADE");
    }

    @Override
    public void supprimerLigne(long id) {
        try {
        if (id == 0) {
            throw new IllegalArgumentException("L'identifiant du service ne peut pas être null.");
        }
        LigneJournal ligne = em.find(LigneJournal.class, id);
        if (ligne != null) {
            em.remove(ligne);
        }
    } catch (IllegalArgumentException e) {
        // Gérer l'exception lorsque id est null
        System.err.println("Erreur : " + e.getMessage());
    } catch (Exception e) {
        // Gérer toute autre exception
        System.err.println("Une erreur s'est produite lors de la suppression de la ligne : " + e.getMessage());
    }
    }

    @Override
    public LigneJournal trouverLigneParId(long id) {
        try {
            return em.createQuery("SELECT a FROM LigneJournal a WHERE a.id = :variable", LigneJournal.class).setParameter("variable", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<LigneJournal> trouverToutesLignes() {
         return em.createQuery("SELECT a FROM LigneJournal a", LigneJournal.class).getResultList();
    }

    @Override
    public List<LigneJournal> listerLignesParJournal(Long idJournal) {
        try {
            return (List<LigneJournal>) em.createQuery("SELECT a FROM LigneJournal a WHERE a.id_journal.id = :variable", LigneJournal.class).setParameter("variable", idJournal).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void mettreAJourLigne(LigneJournal ligne) {
        em.merge(ligne);
    }
    
    
    
    
    
}
