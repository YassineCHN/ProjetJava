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
import javax.persistence.OneToMany;

/**
 *
 * @author charl
 */
@Entity
@DiscriminatorValue("PATIENT")
public class Z_PATIENT extends Z_PERSONNE{

    @OneToMany(mappedBy = "lePatient")
    private List<DossierHospitalisation> dossierHospitalisations;
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
//        if (!(object instanceof Z_PATIENT)) {
//            return false;
//        }
//        Z_PATIENT other = (Z_PATIENT) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "ENTITE.Z_PATIENT[ id=" + id + " ]";
//    }
    
    private String numSecuSoc;

    public String getNumSecuSoc() {
        return numSecuSoc;
    }

    public void setNumSecuSoc(String numSecuSoc) {
        this.numSecuSoc = numSecuSoc;
    }

        private String nomMutuelle;

    public String getNomMutuelle() {
        return nomMutuelle;
    }

    public void setNomMutuelle(String nomMutuelle) {
        this.nomMutuelle = nomMutuelle;
    }

        private String adresseMutuelle;

    public String getAdresseMutuelle() {
        return adresseMutuelle;
    }

    public void setAdresseMutuelle(String adresseMutuelle) {
        this.adresseMutuelle = adresseMutuelle;
    }

}
