/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author charl
 */
@Entity
public class Facture implements Serializable {

    
//    OneToOne à cause du sujet
//    en entreprise, selon les cas, une facture peut avoir plusieurs paiements
//    Ca devient un problème comptable : le lettrage 
    @OneToOne(mappedBy = "laFacture")
    private Paiement paiement;

    

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
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Facture[ id=" + id + " ]";
    }
    @Column(nullable = false) 
    private Double factureMontant;

    public Double getFactureMontant() {
        return factureMontant;
    }

    public void setFactureMontant(Double factureMontant) {
        this.factureMontant = factureMontant;
    }
    @Column(nullable = false) 
    private boolean facturePayee;

    public boolean isFacturePayee() {
        return facturePayee;
    }

    public void setFacturePayee(boolean facturePayee) {
        this.facturePayee = facturePayee;
    }
    @Column(nullable = false) 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date factureDateEmissions;

    public Date getFactureDateEmissions() {
        return factureDateEmissions;
    }

    public void setFactureDateEmissions(Date factureDateEmissions) {
        this.factureDateEmissions = factureDateEmissions;
    }

    
    
//    Pour ces deux champs j'assume de pouvoir les laisser null pour les raisons suivantes
//    Pour l'instant l'application ne le permet pas mais l'idée serait de
//    pouvoir facturer un client/patient en ne se basant ni sur un journal ni sur un dossier
//    
    
    @ManyToOne
        private DossierHospitalisation leDossier;

    public DossierHospitalisation getLeDossier() {
        return leDossier;
    }

    public void setLeDossier(DossierHospitalisation leDossier) {
        this.leDossier = leDossier;
    }

    
    @OneToOne
        private JournalActe leJournal;

    public JournalActe getLeJournal() {
        return leJournal;
    }

    public void setLeJournal(JournalActe leJournal) {
        this.leJournal = leJournal;
    }
    
    

}
