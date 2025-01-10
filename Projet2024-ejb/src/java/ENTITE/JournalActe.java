/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author charl
 */
@Entity
public class JournalActe implements Serializable {

    @OneToMany(mappedBy = "id_journal")
    public List<LigneJournal> ligneJournals;
    
//    Visiblement ça ne permet pas de récupérer les lignes ????
    public List<LigneJournal> getLigneJournals() {
        return this.ligneJournals;
    }
    
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    
    @Enumerated(EnumType.STRING)
    private statutJournal Statut;

    public statutJournal getStatut() {
        return Statut;
    }

    public void setStatut(statutJournal Statut) {
        this.Statut = Statut;
    }

     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JournalActe)) {
            return false;
        }
        JournalActe other = (JournalActe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.JournalActe[ id=" + id + " ]";
    }
    
     @Enumerated(EnumType.STRING)
        private statutJournal string = Statut;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public statutJournal getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(statutJournal string) {
        this.string = string;
    }

        private String Commentaire;

    /**
     * Get the value of Commentaire
     *
     * @return the value of Commentaire
     */
    public String getCommentaire() {
        return Commentaire;
    }

    /**
     * Set the value of Commentaire
     *
     * @param Commentaire new value of Commentaire
     */
    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
        private Date DateCreation;

    /**
     * Get the value of DateCreation
     *
     * @return the value of DateCreation
     */
    public Date getDateCreation() {
        return DateCreation;
    }

    /**
     * Set the value of DateCreation
     *
     * @param DateCreation new value of DateCreation
     */
    public void setDateCreation(Date DateCreation) {
        this.DateCreation = DateCreation;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
        private Date DateValidation;

    /**
     * Get the value of DateValidation
     *
     * @return the value of DateValidation
     */
    public Date getDateValidation() {
        return DateValidation;
    }

    /**
     * Set the value of DateValidation
     *
     * @param DateValidation new value of DateValidation
     */
    public void setDateValidation(Date DateValidation) {
        this.DateValidation = DateValidation;
    }

    @ManyToOne
    private Z_USER utilisateurCreateur;

    /**
     * Get the value of utilisateurCreateur
     *
     * @return the value of utilisateurCreateur
     */
    public Z_USER getUtilisateurCreateur() {
        return utilisateurCreateur;
    }
    

    /**
     * Set the value of utilisateurCreateur
     *
     * @param utilisateurCreateur new value of utilisateurCreateur
     */
    public void setUtilisateurCreateur(Z_USER utilisateurCreateur) {
        this.utilisateurCreateur = utilisateurCreateur;
    }
    
    @ManyToOne
        private DossierHospitalisation Dossier;

    /**
     * Get the value of Dossier
     *
     * @return the value of Dossier
     */
    public DossierHospitalisation getDossier() {
        return Dossier;
    }

    /**
     * Set the value of Dossier
     *
     * @param Dossier new value of Dossier
     */
    public void setDossier(DossierHospitalisation Dossier) {
        this.Dossier = Dossier;
    }

}
