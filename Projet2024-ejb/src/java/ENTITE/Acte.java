/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author charl
 */
@Entity
public class Acte implements Serializable {

    @OneToMany(mappedBy = "id_acte")
    private List<LigneJournal> ligneJournals;

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
        if (!(object instanceof Acte)) {
            return false;
        }
        Acte other = (Acte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Acte[ id=" + id + " ]";
    }
    @Column(nullable = false) 
    private String acteNom;

    public String getActeNom() {
        return acteNom;
    }

    public void setActeNom(String acteNom) {
        this.acteNom = acteNom;
    }
    @Column(nullable = false) 
    private String acteDescription;

    public String getActeDescription() {
        return acteDescription;
    }

    public void setActeDescription(String acteDescription) {
        this.acteDescription = acteDescription;
    }
    @Column(nullable = false) 
    private double actePrix;

    public double getActePrix() {
        return actePrix;
    }

    public void setActePrix(double actePrix) {
        this.actePrix = actePrix;
    }

//    @ManyToOne
//    private DossierHospitalisation leJournal;
//
//    public DossierHospitalisation getLeJournal() {
//        return leDossier;
//    }
//
//    public void setLeDossier(DossierHospitalisation leDossier) {
//        this.leDossier = leDossier;
//    }
    @Column(nullable = false) 
        private double coefficient_SecuriteSociale;

    public double getCoefficient_SecuriteSociale() {
        return coefficient_SecuriteSociale;
    }

    public void setCoefficient_SecuriteSociale(double coefficient_SecuriteSociale) {
        this.coefficient_SecuriteSociale = coefficient_SecuriteSociale;
    }
    @Column(nullable = false) 
    private double coefficient_Mutuelle;

    public double getCoefficient_Mutuelle() {
        return coefficient_Mutuelle;
    }

    public void setCoefficient_Mutuelle(double coefficient_Mutuelle) {
        this.coefficient_Mutuelle = coefficient_Mutuelle;
    }

    
}
