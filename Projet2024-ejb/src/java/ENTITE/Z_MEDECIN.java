/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
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
@DiscriminatorValue("MEDECIN")
public class Z_MEDECIN extends Z_PERSONNE {

    @OneToMany(mappedBy = "leMedecin")
    private List<LigneJournal> ligneJournals;

    @ManyToOne
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Z_MEDECIN)) {
//            return false;
//        }
//        Z_MEDECIN other = (Z_MEDECIN) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "ENTITE.Z_MEDECIN[ id=" + id + " ]";
//    }
    
    private String specialite;

    /**
     * Get the value of specialite
     *
     * @return the value of specialite
     */
    public String getSpecialite() {
        return specialite;
    }

    /**
     * Set the value of specialite
     *
     * @param specialite new value of specialite
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<LigneJournal> getLigneJournals() {
        return ligneJournals;
    }

    public void setLigneJournals(List<LigneJournal> ligneJournals) {
        this.ligneJournals = ligneJournals;
    }

}
