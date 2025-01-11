/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FACADE;

import ENTITE.DossierHospitalisation;
import ENTITE.JournalActe;
import ENTITE.Z_USER;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
/**
 *
 * @author charl
 */
@Stateless
public class JournalActeFacade extends AbstractFacade<JournalActe> implements JournalActeFacadeLocal {

    @PersistenceContext(unitName = "Projet2024-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JournalActeFacade() {
        super(JournalActe.class);
    }

    @Override
    public JournalActe creerJournal( DossierHospitalisation dossier, Z_USER user) {
        JournalActe journal = new JournalActe();
        journal.setDateCreation(new Date());
        journal.setDossier(dossier);
        journal.setUtilisateurCreateur(user);
        em.persist(journal);
        
        
        return journal;
    }

    @Override
    public JournalActe trouverJournalParId(Long id) {
        try {
            return em.createQuery("SELECT s FROM JournalActe s WHERE s.id = :variable", JournalActe.class).setParameter("variable", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<JournalActe> trouverTousLesJournaux() {
        return em.createQuery("SELECT d FROM JournalActe d", JournalActe.class).getResultList();
    }

    @Override
    public JournalActe trouverJournalParDossier(DossierHospitalisation dossier) {
    if (dossier == null) {
        return null;
    }
    try {

        JournalActe result = em.createQuery("SELECT j FROM JournalActe j WHERE j.Dossier = :dossier", JournalActe.class).setParameter("dossier", dossier).getSingleResult();

        
        return  result;
    } catch (NoResultException e) {
        return null; // aucune entité trouvée pour ce dossier
    } catch (Exception e) {
        e.printStackTrace();
        return null; // gestion basique des autres exceptions
    }
    
    }
}
    

