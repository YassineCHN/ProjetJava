/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author charl
 */
@Entity
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        if (!(object instanceof Paiement)) {
            return false;
        }
        Paiement other = (Paiement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Paiement[ id=" + id + " ]";
    }
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date datePaiement;

    /**
     * Get the value of datePaiement
     *
     * @return the value of datePaiement
     */
    public Date getDatePaiement() {
        return datePaiement;
    }

    /**
     * Set the value of datePaiement
     *
     * @param datePaiement new value of datePaiement
     */
    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

        private Double montantPaiement;

    /**
     * Get the value of montantPaiement
     *
     * @return the value of montantPaiement
     */
    public Double getMontantPaiement() {
        return montantPaiement;
    }

    /**
     * Set the value of montantPaiement
     *
     * @param montantPaiement new value of montantPaiement
     */
    public void setMontantPaiement(Double montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

        private ModePaiement modePaiement;

    /**
     * Get the value of modePaiement
     *
     * @return the value of modePaiement
     */
    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    /**
     * Set the value of modePaiement
     *
     * @param modePaiement new value of modePaiement
     */
    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    @OneToOne
        private Facture laFacture;

    public Facture getLaFacture() {
        return laFacture;
    }

    public void setLaFacture(Facture laFacture) {
        this.laFacture = laFacture;
    }

}
